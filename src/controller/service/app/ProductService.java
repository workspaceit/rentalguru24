package controller.service.app;

import antlr.collections.*;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.*;
import model.entity.app.AppCredential;
import model.entity.app.ProductRating;
import model.entity.app.RentType;
import model.entity.app.TempFile;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.rentable.iface.MyRentedProduct;
import model.entity.app.product.view.ProductView;
import model.entity.app.product.rentable.ProductLocation;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validator.form.ProductEditFromValidator;
import validator.form.ProductUploadFormValidator;
import validator.form.class_file.ProductEditFrom;
import validator.form.class_file.ProductUploadForm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/auth/product")
public class ProductService{
    @Autowired
    ProductModel productModel;

    @Autowired
    ProductLocationModel productLocationModel;

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

    @Autowired
    RentRequestModel rentRequestModel;

    @Autowired
    ProductRatingModel getProductRatingModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @JsonView(ProductView.RentalProductView.class)
    public ServiceResponse uploadProduct(HttpServletRequest request,
                                         @RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(!appLoginCredentialModel.isVerified(appCredential.getId())){
            serviceResponse.getResponseStat().setErrorMsg("You account is not verified");
            SessionManagement.destroySession(request);
            return serviceResponse;
        }

        if(appLoginCredentialModel.isBlocked(appCredential.getId())){
            serviceResponse.getResponseStat().setErrorMsg("You account is blocked");
            SessionManagement.destroySession(request);
            return serviceResponse;
        }
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
                serviceResponse.setRequestError("otherImagesToken", "Unable to save profile image");
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
        rentalProduct.setReviewStatus(true);




        Integer[] categoryIds = productUploadForm.getCategoryIdArray();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for(int categoryId : categoryIds){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategory(categoryModel.getById(categoryId));
            productCategoryList.add(productCategory);
        }


        productModel.insert(rentalProduct);

        rentalProduct.setProductCategories(productCategoryList);

        ProductLocation productLocation = new ProductLocation();
        productLocation.setCity(productUploadForm.getCity());
        productLocation.setState(productUploadForm.getState());
        productLocation.setLat(productUploadForm.getLat());
        productLocation.setLng(productUploadForm.getLng());
        productLocation.setZip(productUploadForm.getZip());
        productLocation.setFormattedAddress(productUploadForm.getFormattedAddress());

        productLocation.setRentalProduct(rentalProduct);

        productLocationModel.insert(productLocation);

        serviceResponse.setResponseData(productModel.getById(rentalProduct.getId()));
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
        productRating.setProductId(product.getId());
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
    @RequestMapping(value = "/get-my-rented-product", method = RequestMethod.POST)
    @JsonView(ProductView.MyRentalProductView.class)
    public ServiceResponse getMyRentedProductList(HttpServletRequest request,
                                                  @RequestParam ("limit") int limit,
                                                  @RequestParam ("offset") int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        List<MyRentedProduct> myRentedProducts = productModel.getMyCurrentRentedProduct(appCredential.getId(), limit, offset);
        Set<MyRentedProduct> myRentedProducts1 =  new HashSet<MyRentedProduct>(productModel.getMyCurrentRentedProduct(appCredential.getId()));
        System.out.println(limit);
        System.out.println(offset);
        System.out.println("myRentedProducts "+myRentedProducts.size());
        System.out.println("getMyCurrentRentedProduct " + myRentedProducts1.size());

                serviceResponse.setResponseData(myRentedProducts, "No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-products-on-rent", method = RequestMethod.POST)
    @JsonView(ProductView.MyRentalProductView.class)
    public ServiceResponse getMyCurrentProductOnRent(HttpServletRequest request,
                                                  @RequestParam ("limit") int limit,
                                                  @RequestParam ("offset") int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        System.out.println(limit);
        System.out.println(offset);

        serviceResponse.setResponseData(productModel.getMyCurrentProductOnRent(appCredential.getId(), limit, offset), "No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/update-Product/{product_id}", method = RequestMethod.POST)
    public ServiceResponse updateProduct(HttpServletRequest request,
                                         @PathVariable("product_id") int productId,
                                         @RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductEditFrom productEditFrom,
                                         BindingResult result
                                         ){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(appLoginCredentialModel.isBlocked(appCredential.getId())){
            serviceResponse.getResponseStat().setErrorMsg("You account is blocked");
            SessionManagement.destroySession(request);
            return serviceResponse;
        }

        productEditFrom.setName(allRequestParameter.get("name"));
        productEditFrom.setDescription(allRequestParameter.get("description"));
        productEditFrom.setAvailableFrom(allRequestParameter.get("availableFrom"));
        productEditFrom.setAvailableTill(allRequestParameter.get("availableTill"));
        productEditFrom.setFormattedAddress(allRequestParameter.get("formattedAddress"));
        productEditFrom.setRentTypeId(Integer.parseInt(allRequestParameter.get("rentTypeId")));
        productEditFrom.setCity(allRequestParameter.get("city"));
        productEditFrom.setState(allRequestParameter.get("state"));
        productEditFrom.setZip(allRequestParameter.get("zip"));
        productEditFrom.setCurrentValue(Double.parseDouble(allRequestParameter.get("productCurrentPrice").trim()));
        productEditFrom.setRentFee(Double.parseDouble(allRequestParameter.get("rentPrice").trim()));
        productEditFrom.setCategoryIdArray(allRequestParameter.get("categoryId"));

        new ProductEditFromValidator(categoryModel,tempFileModel,rentTypeModel).validate(productEditFrom, result);

        serviceResponse.setError(result, true, false);
        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct == null){
            serviceResponse.setRequestError("product", "Product Not found");
            return serviceResponse;
        }

        List<ProductCategory> productCategories = rentalProduct.getProductCategories();
        if(productCategories.get(0).getCategory().getId() != Integer.parseInt(allRequestParameter.get("categoryId"))){
            productCategories.get(0).getCategory().setId(Integer.parseInt(allRequestParameter.get("categoryId")));
        }

        if(!rentalProduct.getName().equals(allRequestParameter.get("name"))){
            rentalProduct.setName(allRequestParameter.get("name"));
        }
        if(!rentalProduct.getDescription().equals(allRequestParameter.get("description"))){
            rentalProduct.setDescription(allRequestParameter.get("description"));
        }

        if(!rentalProduct.getAvailableFrom().equals(DateHelper.getStringToTimeStamp(allRequestParameter.get("availableFrom"), "MM/dd/yyyy"))){
            System.out.println("av from now ="+rentalProduct.getAvailableFrom());
            System.out.println("av from edit ="+DateHelper.getStringToTimeStamp(allRequestParameter.get("availableFrom"), "MM/dd/yyyy"));
            rentalProduct.setAvailableFrom(DateHelper.getStringToTimeStamp(allRequestParameter.get("availableFrom"), "MM/dd/yyyy"));
        }

        if(!rentalProduct.getAvailableTill().equals(DateHelper.getStringToTimeStamp(allRequestParameter.get("availableTill"), "MM/dd/yyyy"))){
            System.out.println("till from now ="+rentalProduct.getAvailableTill());
            System.out.println("till from edit ="+DateHelper.getStringToTimeStamp(allRequestParameter.get("availableTill"), "MM/dd/yyyy"));
            rentalProduct.setAvailableTill(DateHelper.getStringToTimeStamp(allRequestParameter.get("availableTill"), "MM/dd/yyyy"));
        }

        if(!rentalProduct.getProductLocation().getFormattedAddress().equals(allRequestParameter.get("formattedAddress"))){
            rentalProduct.getProductLocation().setFormattedAddress(allRequestParameter.get("formattedAddress"));
        }

        if(rentalProduct.getRentType().getId() != Integer.parseInt(allRequestParameter.get("rentTypeId"))){
            RentType rentType = rentTypeModel.getById(Integer.parseInt(allRequestParameter.get("rentTypeId")));
            rentalProduct.setRentType(rentType);
        }

        if(!rentalProduct.getProductLocation().getCity().equals(allRequestParameter.get("city"))){
            rentalProduct.getProductLocation().setCity(allRequestParameter.get("city"));
        }
        if(!rentalProduct.getProductLocation().getZip().equals(allRequestParameter.get("zip"))){
            rentalProduct.getProductLocation().setZip(allRequestParameter.get("zip"));
        }
        if(rentalProduct.getCurrentValue() != Double.parseDouble(allRequestParameter.get("productCurrentPrice"))){
            rentalProduct.setCurrentValue(Double.parseDouble(allRequestParameter.get("productCurrentPrice")));
        }
        if(rentalProduct.getRentFee() != Double.parseDouble(allRequestParameter.get("rentPrice"))){
            rentalProduct.setRentFee(Double.parseDouble(allRequestParameter.get("rentPrice")));
        }

        if(allRequestParameter.get("profileImageToken")!=null && !allRequestParameter.get("profileImageToken").isEmpty()){

            TempFile tempFile = this.tempFileModel.getByToken(Long.parseLong(allRequestParameter.get("profileImageToken")));
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

            rentalProduct.setProfileImage(profileImage);

        }

        if(allRequestParameter.get("otherImagesToken")!=null && !allRequestParameter.get("otherImagesToken").isEmpty()){
            ObjectMapper objectMapper = new ObjectMapper();

            Long[] otherImagesToken = new Long[0];

            try{
                otherImagesToken = objectMapper.readValue(allRequestParameter.get("otherImagesToken"), Long[].class);
            }catch(Exception ex){
                serviceResponse.setRequestError("otherImagesToken","Other images token is not in valid format");

            }

            List<Picture> otherImages = rentalProduct.getOtherImages();

            for(long otherImageToken : otherImagesToken){
                TempFile tempOtherFile = this.tempFileModel.getByToken(otherImageToken);
                Picture picture = new Picture();
                try {
                    picture = ImageHelper.moveProductImage(rentalProduct.getOwner().getId(), tempOtherFile.getPath());

                    if(picture.getOriginal().getPath().isEmpty()) continue;

                    otherImages.add(picture);
                } catch (Exception e) {
                    //e.printStackTrace();
                    serviceResponse.setRequestError("otherImagesToken", "Unable to save profile image");
                    return serviceResponse;
                }
            }

            rentalProduct.setOtherImages(otherImages);
        }


        productModel.update(rentalProduct);
        serviceResponse.setResponseData(productModel.getById(rentalProduct.getId()));

        return serviceResponse;
    }

    @RequestMapping(value = "/delete-Product/{product_id}", method = RequestMethod.POST)
    public ServiceResponse deleteProduct(HttpServletRequest request,
                                         @PathVariable("product_id") int productId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct == null){
            serviceResponse.setRequestError("product", "Product Not found");
            return serviceResponse;
        }

        boolean isProductOnRent = rentRequestModel.productOnrent(productId);

        if(isProductOnRent == true){
            serviceResponse.setRequestError("product", "Can't delete a product on rent");
            return serviceResponse;
        }

        List<ProductRating> productRatings = productRatingModel.getByProductId(productId);
        if(productRatings != null || !productRatings.isEmpty()){
            for(int i = 0; i<productRatings.size(); i++){
                productRatingModel.delete(productRatings.get(i));
            }
        }
        productModel.delete(rentalProduct);

        serviceResponse.getResponseStat().setMsg("product has been deleted");
        return serviceResponse;

    }

    @RequestMapping(value = "/delete-product/other-image", method = RequestMethod.POST)
    public ServiceResponse deleteOtherProductImages(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        Integer productId = Integer.parseInt(allRequestParameter.get("productId"));
        String path = allRequestParameter.get("path");
        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct == null){
            serviceResponse.setRequestError("product", "Product Not found");
            return serviceResponse;
        }

        if(rentalProduct.getOtherImages() == null){
            serviceResponse.setRequestError("product", "product has no other images");
            return  serviceResponse;
        }

        List<Picture> pictureList = rentalProduct.getOtherImages();
        for(int i= 0; i<pictureList.size(); i++){
            if(pictureList.get(i).getOriginal().getPath().equals(path)){
                pictureList.remove(i);
                break;
            }
        }

        rentalProduct.setOtherImages(pictureList);

        productModel.update(rentalProduct);

        serviceResponse.setResponseData(rentalProduct.getOtherImages());

        serviceResponse.getResponseStat().setMsg("Picture Delete Successful");
        return serviceResponse;

    }

    @RequestMapping(value = "/product/review/{product_id}", method = RequestMethod.GET)
    public ServiceResponse getProductReview(HttpServletRequest request, @PathVariable("product_id") int productId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<ProductRating> productRatingList = productRatingModel.getByProductId(productId);
        if(productRatingList == null ||productRatingList.isEmpty()){
            serviceResponse.setRequestError("rating", "Not rated yet");
            return serviceResponse;
        }
        serviceResponse.setResponseData(productRatingList);
        return serviceResponse;
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ServiceResponse setProductReview(HttpServletRequest request, @RequestParam Map<String , String> allRequestParam){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");



        if(allRequestParam.get("productId") == null ||allRequestParam.get("productId").isEmpty()){
            serviceResponse.setRequestError("productId", "Product id required");
        }

        if(allRequestParam.get("rateValue") == null || allRequestParam.get("rateValue").isEmpty()){
            serviceResponse.setRequestError("rateValue", "Product rating required");
        }
        if(allRequestParam.get("reviewText") == null || allRequestParam.get("reviewText").isEmpty()){
            serviceResponse.setRequestError("reviewText", "Product review text required");
        }

        if(allRequestParam.get("rentInfId") == null || allRequestParam.get("rentInfId").isEmpty()){
            serviceResponse.setRequestError("rentInfId", "RentInf Id  required");
        }

        if(allRequestParam.get("rentRequestId") == null || allRequestParam.get("rentRequestId").isEmpty()){
            serviceResponse.setRequestError("rentRequestId", "Rent Request Id  required");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        int productId = Integer.parseInt(allRequestParam.get("productId").trim());
        int rateValue = Integer.parseInt(allRequestParam.get("rateValue").trim());
        int rentInfId = Integer.parseInt(allRequestParam.get("rentInfId").trim());
        int rentRequestId = Integer.parseInt(allRequestParam.get("rentRequestId").trim());
        String reviewText = allRequestParam.get("reviewText");

        boolean isAlreadyRatedByUser = productRatingModel.isAlreadyRatedByUser(productId, rentInfId, rentRequestId, appCredential.getId());

        if(isAlreadyRatedByUser){
            serviceResponse.getResponseStat().setMsg("You have already review this product");
            return serviceResponse;
        }

        RentalProductEntity rentalProductEntity = productModel.getEntityById(productId);

        if(rentalProductEntity == null){
            serviceResponse.setRequestError("product", "Product not found");
            return serviceResponse;
        }

        System.out.println(rentalProductEntity.getId() + " " + rentInfId + " " + rentRequestId);

        ProductRating productRating = new ProductRating();
        productRating.setProductId(rentalProductEntity.getId());
        productRating.setRentInfId(rentInfId);
        productRating.setRentRequestId(rentRequestId);
        productRating.setAppCredential(appCredential);
        productRating.setRateValue(rateValue);
        productRating.setReviewText(reviewText);
        productRating.setCreatedDate(DateHelper.getCurrentUtcDateTimeStamp());

        productRatingModel.insert(productRating);

        double averageRate = productRatingModel.averageRating(productId);
        rentalProductEntity.setAverageRating((float)averageRate);
        productModel.update(rentalProductEntity);

        serviceResponse.getResponseStat().setMsg("Product rated successful");
        return serviceResponse;
    }
}

