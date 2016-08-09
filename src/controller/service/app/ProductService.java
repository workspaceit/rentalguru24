package controller.service.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.service.BaseService;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.CategoryModel;
import model.ProductModel;
import model.TempFileModel;
import model.entity.app.*;
import model.entity.app.product.Product;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.ProductLocation;
import model.nonentity.photo.Picture;
import org.hibernate.Session;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    CategoryModel categoryModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ServiceResponse uploadProduct(@RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){

        this.serviceResponse.setParameterAlias("otherImagesTokenArray","otherImageTokens");

        if(!this.serviceResponse.getResponseStat().getIsLogin()){
            this.serviceResponse.getResponseStat().setErrorMsg("Session expired !! , please login ");
            return this.serviceResponse;
        }
        Product product = new Product();

        productUploadForm.setName(allRequestParameter.get("name"));
        productUploadForm.setDescription(allRequestParameter.get("description"));


        try{
            ObjectMapper objectMapper = new ObjectMapper();

            if(!allRequestParameter.get("categoryIds").isEmpty()){
                Integer[] categoryIdArray =  objectMapper.readValue(allRequestParameter.get("categoryIds"), Integer[].class);
                productUploadForm.setCategoryIdArray(categoryIdArray);
            }else{
                this.serviceResponse.setRequestError("categoryIds","Category Id required");
                productUploadForm.setCategoryIdArray(new Integer[0]);
            }

        }catch(Exception ex){
            this.serviceResponse.setRequestError("categoryIds","Category Id required");
            productUploadForm.setCategoryIdArray(new Integer[0]);
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            if(allRequestParameter.get("otherImagesToken")!=null && !allRequestParameter.get("otherImagesToken").isEmpty()) {
                Long[] otherImagesToken = objectMapper.readValue(allRequestParameter.get("otherImagesToken"), Long[].class);
                productUploadForm.setOtherImagesTokenArray(otherImagesToken);
            }
        }catch(Exception ex){
            this.serviceResponse.setRequestError("otherImagesToken","Other images token is not in valid format");
        }

        try{
            productUploadForm.setProfileImageToken(Long.parseLong(allRequestParameter.get("profileImageToken")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("profileImageToken","Profile image token value required");
        }

        try{
            productUploadForm.setCurrentValue(Double.parseDouble(allRequestParameter.get("currentValue")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("currentValue","Current value required");
        }

        try{
            if(allRequestParameter.get("lat")!=null){
                productUploadForm.setLat(Float.parseFloat(allRequestParameter.get("lat")));
            }
        }catch(Exception ex){
            this.serviceResponse.setRequestError("lat","Latitude is not valid format, float  required");
        }

        try{
            if(allRequestParameter.get("lng")!=null) {
                productUploadForm.setLng(Float.parseFloat(allRequestParameter.get("lng")));
            }
        }catch(Exception ex){
            this.serviceResponse.setRequestError("lng","Longitude is not valid format, float  required");
        }

        try{
            productUploadForm.setRentFee(Double.parseDouble(allRequestParameter.get("rentFee")));
        }catch(Exception ex){
            this.serviceResponse.setRequestError("rentFee","Rent fee required");
        }



        productUploadForm.setCity(allRequestParameter.get("city"));
        productUploadForm.setState(allRequestParameter.get("state"));
        productUploadForm.setZip(allRequestParameter.get("zip"));
        productUploadForm.setFormattedAddress(allRequestParameter.get("formattedAddress"));

        productUploadForm.setAvailableFrom(allRequestParameter.get("availableFrom"));
        productUploadForm.setAvailableTill(allRequestParameter.get("availableTill"));






        new ProductUploadFormValidator(categoryModel,tempFileModel).validate(productUploadForm, result);




        java.sql.Timestamp availableFromDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableFrom(), "dd-MM-yyyy") ;
        java.sql.Timestamp availableTillDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableTill(), "dd-MM-yyyy") ;

        Timestamp utcTimeStamp = DateHelper.getUtcTimeStamp();

//        utcTimeStamp. Validation
        if(utcTimeStamp.after(availableFromDate)){
            this.serviceResponse.setRequestError("availableFrom", "Available from is past then current time");
        }else{
            if(availableFromDate.after(availableTillDate)){
                this.serviceResponse.setRequestError("availableTill", "Available from is past to available till time");
            }
        }

        if(utcTimeStamp.after(availableTillDate)){
            this.serviceResponse.setRequestError("availableTill", "Available till is past then current time");
        }

        this.serviceResponse.setError(result, true, false);
        if(this.serviceResponse.hasErrors()){
            return this.serviceResponse;
        }


        product.setOwner(this.appCredential);

        /*----- Move Product image form temp to original ---- */



        TempFile tempFile = this.tempFileModel.getByToken(productUploadForm.getProfileImageToken());
        if(tempFile ==null){
            this.serviceResponse.setRequestError("profileImage", "Profile Image token is not valid");
            return serviceResponse;
        }

        if(!ImageHelper.isFileExist(tempFile.getPath())){
            this.serviceResponse.setRequestError("profileImage", "No file found associated with the token");
            return serviceResponse;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Picture profileImage = new Picture();
        try {
            profileImage = ImageHelper.moveProductImage(product.getOwner().getId(), tempFile.getPath());
        } catch (Exception e) {
            //e.printStackTrace();
            this.serviceResponse.setRequestError("profileImage", "Unable to save profile image");
            return serviceResponse;
        }

        /*----- Move Product other images form temp to original ---- */

        List<Picture> otherImages = new ArrayList<>();
        for(long otherImageToken : productUploadForm.getOtherImagesTokenArray()){
            TempFile tempOtherFile = this.tempFileModel.getByToken(otherImageToken);
            Picture picture = new Picture();
            try {
                picture = ImageHelper.moveProductImage(product.getOwner().getId(), tempOtherFile.getPath());
                otherImages.add(picture);
            } catch (Exception e) {
                //e.printStackTrace();
                this.serviceResponse.setRequestError("profileImage", "Unable to save profile image");
                return serviceResponse;
            }
        }




        product.setName(productUploadForm.getName());
        product.setDescription(productUploadForm.getDescription());
        product.setProfileImage(profileImage);
        product.setOtherImages(otherImages);
        product.setCurrentValue(productUploadForm.getCurrentValue());
        product.setRentFee(productUploadForm.getRentFee());
        product.setActive(true);
        product.setCurrentValue(productUploadForm.getCurrentValue());
        product.setAvailableFrom(availableFromDate);
        product.setAvailableTill(availableTillDate);
        product.setReviewStatus(false);




        Integer[] categoryIds = productUploadForm.getCategoryIdArray();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for(int categoryId : categoryIds){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategory(categoryModel.getById(categoryId));
            productCategoryList.add(productCategory);
        }
        product.setProductCategories(productCategoryList);


        productModel.insert(product);

        product.setProductLocation(new ProductLocation());
        product.getProductLocation().setCity(productUploadForm.getCity());
        product.getProductLocation().setState(productUploadForm.getState());
        product.getProductLocation().setLat(productUploadForm.getLat());
        product.getProductLocation().setLng(productUploadForm.getLng());
        product.getProductLocation().setZip(productUploadForm.getZip());
        product.getProductLocation().setFormattedAddress(productUploadForm.getFormattedAddress());
        product.getProductLocation().setProductId(product.getId());

        productModel.update(product);

        this.serviceResponse.setResponseData(product);
        return this.serviceResponse;
    }








































    @RequestMapping(value = "/get-searched-product", method = RequestMethod.GET)
    public List<Product> getProductSearchedProduct(@RequestParam ("limit") int limit, @RequestParam ("offset") int offset){
        List<Product> products = productModel.getProductSearch(limit, offset);
        return products;
    }
}

