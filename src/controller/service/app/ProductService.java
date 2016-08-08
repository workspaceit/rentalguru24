package controller.service.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.service.BaseService;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import jdk.nashorn.internal.parser.JSONParser;
import model.AppLoginCredentialModel;
import model.ProductModel;
import model.TempFileModel;
import model.entity.app.AppCredential;
import model.entity.app.Product;
import model.entity.app.TempFile;
import model.nonentity.photo.Picture;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    TempFileModel tempFileModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ServiceResponse uploadProduct(@RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){


        Product product = new Product();

        productUploadForm.setName(allRequestParameter.get("name"));
        productUploadForm.setDescription(allRequestParameter.get("description"));
        productUploadForm.setOtherImages(allRequestParameter.get("otherImagesTokens"));
        try{
            productUploadForm.setProfileImage(Long.parseLong(allRequestParameter.get("profileImageToken")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("profileImageToken","Profile image token value required");
        }
        try{
            productUploadForm.setCurrentValue(Double.parseDouble(allRequestParameter.get("currentValue")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("currentValue","Current value required");
        }
        try{
            productUploadForm.setRentFee(Double.parseDouble(allRequestParameter.get("rentFee")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("rentFee","Rent fee required");
        }

        productUploadForm.setAvailableFrom(allRequestParameter.get("availableFrom"));
        productUploadForm.setAvailableTill(allRequestParameter.get("availableTill"));






        new ProductUploadFormValidator().validate(productUploadForm, result);

        this.serviceResponse.setError(result, true, false);
        if( this.serviceResponse.hasErrors()){
            return this.serviceResponse;
        }


        java.sql.Timestamp availableFromDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableFrom(), "dd-MM-yyyy") ;
        java.sql.Timestamp availableTillDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableFrom(), "dd-MM-yyyy") ;



        product.setOwner(appLoginCredentialModel.getAppCredentialById(32));

        /*----- Move identity doc form temp to original ---- */



        TempFile tempFile = this.tempFileModel.getByToken(productUploadForm.getProfileImage());
        if(tempFile ==null){
            this.serviceResponse.setRequestError("profileImage", "Profile Image doc token is not valid");
            return serviceResponse;
        }

        if(!ImageHelper.isFileExist(tempFile.getPath())){
            this.serviceResponse.setRequestError("profileImage", "No file found associated with the token");
            return serviceResponse;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Picture profileImageJsonObject = new Picture();
        try {
            profileImageJsonObject = ImageHelper.moveProductImage(product.getOwner().getId(), tempFile.getPath());
        } catch (Exception e) {
            //e.printStackTrace();
            this.serviceResponse.setRequestError("profileImage", "Unable to save profile image");
            return serviceResponse;
        }


        product.setName(productUploadForm.getName());
        product.setDescription(productUploadForm.getDescription());
        product.setProfileImage(profileImageJsonObject);
        //product.setOtherImages();
        product.setCurrentValue(productUploadForm.getCurrentValue());
        product.setRentFee(productUploadForm.getRentFee());
        product.setActive(true);
        product.setCurrentValue(productUploadForm.getCurrentValue());
        product.setAvailableFrom(availableFromDate);
        product.setAvailableTill(availableTillDate);
        product.setReviewStatus(false);



        productModel.insert(product);

        this.serviceResponse.setResponseData(product);
        return this.serviceResponse;
    }
}
