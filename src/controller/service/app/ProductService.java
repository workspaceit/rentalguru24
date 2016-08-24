package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import model.*;
import model.entity.app.AppCredential;
import model.entity.app.ProductRating;
import model.entity.app.RentType;
import model.entity.app.TempFile;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.view.ProductView;
import model.entity.app.product.rentable.ProductLocation;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validator.form.ProductUploadFormValidator;
import validator.form.class_file.ProductUploadForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/auth/product")
public class ProductService{
    @Autowired
    ProductModel productModel;

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    RentTypeModel rentTypeModel;

    @Autowired
    ProductRatingModel productRatingModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @JsonView(ProductView.RentalProductView.class)
    public ServiceResponse uploadProduct(HttpServletRequest request,
                                         @RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setParameterAlias("otherImagesTokenArray", "otherImageTokens");


        RentalProduct rentalProduct = new RentalProductEntity();

        productUploadForm.setName(allRequestParameter.get("name"));
        productUploadForm.setDescription(allRequestParameter.get("description"));


        try{
            ObjectMapper objectMapper = new ObjectMapper();

            if(!allRequestParameter.get("categoryIds").isEmpty()){
                Integer[] categoryIdArray =  objectMapper.readValue(allRequestParameter.get("categoryIds"), Integer[].class);
                productUploadForm.setCategoryIdArray(categoryIdArray);
            }else{
                serviceResponse.setRequestError("categoryIds","Category Id required");
                productUploadForm.setCategoryIdArray(new Integer[0]);
            }

        }catch(Exception ex){
            serviceResponse.setRequestError("categoryIds","Category Id required");
            productUploadForm.setCategoryIdArray(new Integer[0]);
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            if(allRequestParameter.get("otherImagesToken")!=null && !allRequestParameter.get("otherImagesToken").isEmpty()) {
                Long[] otherImagesToken = objectMapper.readValue(allRequestParameter.get("otherImagesToken"), Long[].class);
                productUploadForm.setOtherImagesTokenArray(otherImagesToken);
            }else{
                productUploadForm.setOtherImagesTokenArray(new Long[0]);
            }
        }catch(Exception ex){
            serviceResponse.setRequestError("otherImagesToken","Other images token is not in valid format");
            productUploadForm.setOtherImagesTokenArray(new Long[0]);
        }

        try{
            productUploadForm.setProfileImageToken(Long.parseLong(allRequestParameter.get("profileImageToken")));
        }catch(Exception ex){
            serviceResponse.setRequestError("profileImageToken", "Profile image token value required");
        }

        try{
            productUploadForm.setCurrentValue(Double.parseDouble(allRequestParameter.get("currentValue")));
        }catch(Exception ex){
            serviceResponse.setRequestError("currentValue","Current value required");
        }

        try{
            if(allRequestParameter.get("lat")!=null){
                productUploadForm.setLat(Float.parseFloat(allRequestParameter.get("lat")));
            }
        }catch(Exception ex){
            serviceResponse.setRequestError("lat","Latitude is not valid format, float  required");
        }

        try{
            if(allRequestParameter.get("lng")!=null) {
                productUploadForm.setLng(Float.parseFloat(allRequestParameter.get("lng")));
            }
        }catch(Exception ex){
            serviceResponse.setRequestError("lng","Longitude is not valid format, float  required");
        }

        try{
            productUploadForm.setRentFee(Double.parseDouble(allRequestParameter.get("rentFee")));
        }catch(Exception ex){
            serviceResponse.setRequestError("rentFee","Rent fee integer required");
        }

        try{
            if(allRequestParameter.get("rentTypeId")!=null) {
                productUploadForm.setRentTypeId(Integer.parseInt(allRequestParameter.get("rentTypeId")));
            }else{
                serviceResponse.setRequestError("rentTypeId","Rent type  required");
            }
        }catch(Exception ex){
            serviceResponse.setRequestError("rentTypeId","Rent type  required");
        }


        productUploadForm.setCity(allRequestParameter.get("city"));
        productUploadForm.setState(allRequestParameter.get("state"));
        productUploadForm.setZip(allRequestParameter.get("zip"));
        productUploadForm.setFormattedAddress(allRequestParameter.get("formattedAddress"));

        productUploadForm.setAvailableFrom(allRequestParameter.get("availableFrom"));
        productUploadForm.setAvailableTill(allRequestParameter.get("availableTill"));


        new ProductUploadFormValidator(categoryModel,tempFileModel,rentTypeModel).validate(productUploadForm, result);


        serviceResponse.setError(result, true, false);
        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        Timestamp availableFromDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableFrom(), "dd-MM-yyyy") ;
        Timestamp availableTillDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableTill(), "dd-MM-yyyy") ;

        Timestamp utcTimeStamp = DateHelper.getUtcTimeStamp();

//        utcTimeStamp. Validation
        if(utcTimeStamp.after(availableFromDate)){
            serviceResponse.setRequestError("availableFrom", "Available from is past then current time");
        }else{
            if(availableFromDate.after(availableTillDate)){
                serviceResponse.setRequestError("availableTill", "Available from is past to available till time");
            }
        }

        if(utcTimeStamp.after(availableTillDate)){
            serviceResponse.setRequestError("availableTill", "Available till is past then current time");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        rentalProduct.setOwner(appCredential);

        /*----- Move Product image form temp to original ---- */



        TempFile tempFile = this.tempFileModel.getByToken(productUploadForm.getProfileImageToken());
        if(tempFile ==null){
            serviceResponse.setRequestError("profileImageToken", "Profile Image token is not valid");
            return serviceResponse;
        }

        if(!ImageHelper.isFileExist(tempFile.getPath())){
            serviceResponse.setRequestError("profileImageToken", "No file found associated with the token");
            return serviceResponse;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Picture profileImage = new Picture();
        try {
            profileImage = ImageHelper.moveProductImage(rentalProduct.getOwner().getId(), tempFile.getPath());
        } catch (Exception e) {
            //e.printStackTrace();
            serviceResponse.setRequestError("profileImageToken", "Unable to save profile image");
            return serviceResponse;
        }

        /*----- Move Product other images form temp to original ---- */

        List<Picture> otherImages = new ArrayList<>();
        for(long otherImageToken : productUploadForm.getOtherImagesTokenArray()){
            TempFile tempOtherFile = this.tempFileModel.getByToken(otherImageToken);
            Picture picture = new Picture();
            try {
                picture = ImageHelper.moveProductImage(rentalProduct.getOwner().getId(), tempOtherFile.getPath());

                if(picture.getOriginal().getPath().isEmpty()) continue;

                otherImages.add(picture);
            } catch (Exception e) {
                //e.printStackTrace();
                serviceResponse.setRequestError("profileImageToken", "Unable to save profile image");
                return serviceResponse;
            }
        }

        RentType rentType = rentTypeModel.getById(productUploadForm.getRentTypeId());



        rentalProduct.setName(productUploadForm.getName());
        rentalProduct.setDescription(productUploadForm.getDescription());
        rentalProduct.setAverageRating(0);
        rentalProduct.setProfileImage(profileImage);
        rentalProduct.setOtherImages(otherImages);
        rentalProduct.setCurrentValue(productUploadForm.getCurrentValue());
        rentalProduct.setRentFee(productUploadForm.getRentFee());
        rentalProduct.setActive(true);
        rentalProduct.setCurrentlyAvailable(true);
        rentalProduct.setRentType(rentType);
        rentalProduct.setCurrentValue(productUploadForm.getCurrentValue());
        rentalProduct.setAvailableFrom(availableFromDate);
        rentalProduct.setAvailableTill(availableTillDate);
        rentalProduct.setReviewStatus(false);




        Integer[] categoryIds = productUploadForm.getCategoryIdArray();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for(int categoryId : categoryIds){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategory(categoryModel.getById(categoryId));
            productCategoryList.add(productCategory);
        }
        rentalProduct.setProductCategories(productCategoryList);


        productModel.insert(rentalProduct);

        rentalProduct.setProductLocation(new ProductLocation());
        rentalProduct.getProductLocation().setCity(productUploadForm.getCity());
        rentalProduct.getProductLocation().setState(productUploadForm.getState());
        rentalProduct.getProductLocation().setLat(productUploadForm.getLat());
        rentalProduct.getProductLocation().setLng(productUploadForm.getLng());
        rentalProduct.getProductLocation().setZip(productUploadForm.getZip());
        rentalProduct.getProductLocation().setFormattedAddress(productUploadForm.getFormattedAddress());
        rentalProduct.getProductLocation().setProductId(rentalProduct.getId());

        productModel.update(rentalProduct);

        serviceResponse.setResponseData(rentalProduct);
        return serviceResponse;
    }

    @RequestMapping(value = "/rate-product/{product_id}/{rating_value}", method = RequestMethod.GET)
    public ServiceResponse postProductRating(HttpServletRequest request,
                                             @PathVariable("product_id") int productId,
                                             @PathVariable("rating_value") int ratingValue){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(productRatingModel.getAuthorization(appCredential.getId(), productId) == true){
            serviceResponse.getResponseStat().setErrorMsg("You have already rated this product !!");
            return serviceResponse;
        }

        RentalProductEntity product = productModel.getEntityById(productId);
        ProductRating productRating = new ProductRating();

        if(product == null){
            serviceResponse.getResponseStat().setErrorMsg("No Product Found !!");
            return serviceResponse;
        }


        productRating.setAppCredential(appCredential);
        productRating.setProduct(product);
        productRating.setRateValue(ratingValue);

        productRatingModel.insert(productRating);

        double averageRate = productRatingModel.averageRating(productId);
        product.setAverageRating((float)averageRate);
        productModel.update(product);

        serviceResponse.setResponseData(productRating);

        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-rental-product/{product_id}", method = RequestMethod.GET)
    @JsonView(ProductView.MyRentalProductView.class)
    public ServiceResponse getMyRentalProduct(HttpServletRequest request,
                                              @PathVariable("product_id") int productId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentalProduct mrp = productModel.getMyRentalProductById(productId, appCredential.getId());
//        System.out.println("mrp : "+mrp.getClass());
//        System.out.println("mrp : "+mrp.getRentProduct().getEndsDate());
        serviceResponse.setResponseData(mrp, "No record found");
        return serviceResponse;
    }


    @RequestMapping(value = "/get-my-rental-product", method = RequestMethod.POST)
    @JsonView(ProductView.MyRentalProductView.class)
    public ServiceResponse getMyRentalProductList(HttpServletRequest request,
                                                @RequestParam ("limit") int limit,
                                                @RequestParam ("offset") int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(productModel.getMyRentalProductList(appCredential.getId(), limit, offset), "No record found");
        return serviceResponse;
    }



}

