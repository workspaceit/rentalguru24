package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.ProductModel;
import model.entity.app.AppCredential;
import model.entity.app.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.form.ProductUploadFormValidator;
import validator.form.class_file.ProductUploadForm;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/product")
@Scope("request")
public class ProductService extends BaseService{
    @Autowired
    ProductModel productModel;

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ServiceResponse uploadProduct(@RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){


        Product product = new Product();

        String name = allRequestParameter.get("name");
        String description = allRequestParameter.get("description");
        String profileImage = allRequestParameter.get("profileimage");
        String otherImages = allRequestParameter.get("otherimages");
        String currentValue = allRequestParameter.get("currentvalue");
        String rentFee = allRequestParameter.get("rentfee");
        String active = allRequestParameter.get("active");
        String currentlyAvailable = allRequestParameter.get("currentlyavailable");
        String availableFrom =  allRequestParameter.get("availablefrom");
        String availableTill = allRequestParameter.get("availabletill");
        String reviewStatus = allRequestParameter.get("reviewstatus");

        java.sql.Timestamp availableFromDate = java.sql.Timestamp.valueOf(availableFrom) ;
        java.sql.Timestamp availableTillDate = java.sql.Timestamp.valueOf(availableTill) ;


        product.setName(name);
        product.setDescription(description);
        product.setProfileImage(profileImage);
        product.setOtherImages(otherImages);
        product.setCurrentValue(Double.parseDouble(currentValue));
        product.setRentFee(Double.parseDouble(rentFee));
        product.setActive(Boolean.parseBoolean(active));
        product.setCurrentValue(Double.parseDouble(currentlyAvailable));
        product.setAvailableFrom(availableFromDate);
        product.setAvailableTill(availableTillDate);
        product.setReviewStatus(Boolean.parseBoolean(reviewStatus));

        product.setOwner(appLoginCredentialModel.getAppCredentialById(1));

        productModel.insert(product);


        return this.serviceResponse;
    }
}
