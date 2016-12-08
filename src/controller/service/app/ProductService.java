package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
import model.*;
import model.entity.State;
import model.entity.app.*;
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
    ProductCategoryModel productCategoryModel;

    @Autowired
    StateModel stateModel;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @JsonView(ProductView.RentalProductView.class)
    public ServiceResponse uploadProduct(HttpServletRequest request,
                                         @RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductUploadForm productUploadForm,
                                         BindingResult result){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        System.out.println(productUploadForm);
        /*
         * Response Error Parameter alias
         * */
        serviceResponse.setParameterAlias("otherImagesTokenArray", "otherImageTokens");
        serviceResponse.setParameterAlias("categoryIdArray", "categoryIds");

        /*
         * User Account verification and block check
         * */
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


        RentalProduct rentalProduct = new RentalProductEntity();

        productUploadForm.setName(allRequestParameter.get("name"));
        productUploadForm.setDescription(allRequestParameter.get("description"));


        try{
            ObjectMapper objectMapper = new ObjectMapper();

            if(!allRequestParameter.get("categoryIds").isEmpty()){
                Integer[] categoryIdArray =  objectMapper.readValue(allRequestParameter.get("categoryIds"), Integer[].class);
                productUploadForm.setCategoryIdArray(categoryIdArray);
            } else{
                serviceResponse.setRequestError("categoryIds","Category Id required");
                productUploadForm.setCategoryIdArray(new Integer[0]);
            }

        } catch (Exception ex){
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

        State usState = null;
        if(productUploadForm.getStateId()!=null){
            usState = stateModel.getById(productUploadForm.getStateId());
            if(usState==null){
                serviceResponse.setRequestError("stateId","State not found");
            }
        }

        /*
         * Validation check
         * productUploadForm is containing all the value of request parameter
         * ProductUploadFormValidator validate productUploadForm
         * */
        new ProductUploadFormValidator(categoryModel,tempFileModel,rentTypeModel).validate(productUploadForm, result);

        serviceResponse.setError(result, true, false);
        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        /*
         * If User does not sent lat lng
         * server detects the request ip and gets lat lng
         * */
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        System.out.println("request.getRemoteAddr() " +ipAddress);

        if(productUploadForm.getLat()==null && productUploadForm.getLng()==null){
             /*
             * Fetching lat lng by ip from Remote Service (3rd party service )
             * */
                GeoIpManager geoIpManager = new GeoIpManager();
                GeoIp geoIp = geoIpManager.getGeoIp(request);
                if(geoIpManager.isSuccess()){
                    productUploadForm.setLat(geoIp.getLatitude());
                    productUploadForm.setLng(geoIp.getLongitude());
                }
        }

        Timestamp availableFromDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableFrom(), "dd-MM-yyyy") ;
        Timestamp availableTillDate = DateHelper.getStringToTimeStamp(productUploadForm.getAvailableTill(), "dd-MM-yyyy") ;

        Timestamp utcTimeStamp = DateHelper.getUtcTimeStamp();


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


        /*~~~~~~~~~~~~~ Removing token after moving ~~~~~~*/
        this.tempFileModel.delete(tempFile);
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

            /*~~~~~~~~~~~~~ Removing token after moving ~~~~~~~~~~*/
            this.tempFileModel.delete(tempOtherFile);
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


        productModel.insert(rentalProduct);

        for(int categoryId : categoryIds){
            Category category = categoryModel.getById(categoryId);
            int count = category.getProductCount();
            category.setProductCount(count+1);
            categoryModel.insert(category);
        }


        rentalProduct.setProductCategories(productCategoryList);

        ProductLocation productLocation = new ProductLocation();
        productLocation.setCity(productUploadForm.getCity());
        productLocation.setState(usState);
        productLocation.setLat(productUploadForm.getLat());
        productLocation.setLng(productUploadForm.getLng());
        productLocation.setZip(productUploadForm.getZip());
        productLocation.setFormattedAddress(productUploadForm.getFormattedAddress());

        productLocation.setRentalProduct(rentalProduct);

        productLocationModel.insert(productLocation);

        serviceResponse.setResponseData(productModel.getById(rentalProduct.getId()));
        return serviceResponse;
    }
    @RequestMapping(value = "/update-product/{product_id}", method = RequestMethod.POST)
    public ServiceResponse updateProduct(HttpServletRequest request,
                                         @PathVariable("product_id") int productId,
                                         @RequestParam Map<String,String> allRequestParameter,
                                         @Valid ProductEditFrom productEditFrom,
                                         BindingResult result
    ){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(!appLoginCredentialModel.isVerified(appCredential.getId())){
            serviceResponse.getResponseStat().setErrorMsg("You account is not verified yet");
            SessionManagement.destroySession(request);
            return serviceResponse;
        }

        if(appLoginCredentialModel.isBlocked(appCredential.getId())){
            serviceResponse.getResponseStat().setErrorMsg("You account is blocked");
            SessionManagement.destroySession(request);
            return serviceResponse;
        }
        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct == null){
            serviceResponse.setRequestError("product", "Product Not found");
            return serviceResponse;
        }

        if(rentalProduct.getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("product", "You are not the owner of this product");
            return serviceResponse;
        }

        serviceResponse.setParameterAlias("categoryIdArray", "categoryIds");

        if(allRequestParameter.get("categoryIds")!=null && !allRequestParameter.get("categoryIds").isEmpty()){
            ObjectMapper objectMapper =  new ObjectMapper();
            try{
                Integer[] categoryIdArray =  objectMapper.readValue(allRequestParameter.get("categoryIds"), Integer[].class);
                productEditFrom.setCategoryIdArray(categoryIdArray);
            } catch (Exception e) {
                serviceResponse.setRequestError("categoryIds","Type miss matched json array required");
                productEditFrom.setCategoryIdArray(new Integer[0]);
            }

        }

        if(allRequestParameter.get("productCurrentPrice")!=null && !allRequestParameter.get("productCurrentPrice").isEmpty()){
            try{
                Float tmpCurrentPrice = Float.parseFloat(allRequestParameter.get("productCurrentPrice"));

                if(tmpCurrentPrice<=0){
                    serviceResponse.setRequestError("productCurrentPrice","Must be greater then Zero");
                }else{
                    productEditFrom.setCurrentValue(tmpCurrentPrice);
                }

            }catch (NumberFormatException ex){
                serviceResponse.setRequestError("productCurrentPrice", "Type miss matched float required");
            }
        }
        if(allRequestParameter.get("rentPrice")!=null && !allRequestParameter.get("rentPrice").isEmpty()){
            try{
                Float tmpRentPrice = Float.parseFloat(allRequestParameter.get("rentPrice"));

                if(tmpRentPrice<=0){
                    serviceResponse.setRequestError("rentPrice","Must be greater then Zero");
                }else{
                    productEditFrom.setRentFee(tmpRentPrice);
                }

            }catch (NumberFormatException ex){
                serviceResponse.setRequestError("rentPrice","Type miss matched float required");
            }
        }

        State usState = null;
        if(productEditFrom.getStateId()!=null){
            usState = stateModel.getById(productEditFrom.getStateId());
            if(usState==null){
                serviceResponse.setRequestError("stateId","State not found");
            }
        }

        new ProductEditFromValidator(categoryModel,tempFileModel,rentTypeModel).validate(productEditFrom, result);

        serviceResponse.setError(result, true, false);

        ProductLocation productLocation =  rentalProduct.getProductLocation();
        productLocation = (productLocation==null)?new ProductLocation():productLocation;

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        if(!productEditFrom.getName().isEmpty()){
            rentalProduct.setName(productEditFrom.getName());
        }
        if(!productEditFrom.getDescription().isEmpty()){
            rentalProduct.setDescription(productEditFrom.getDescription());
        }
        if(!productEditFrom.getAvailableFrom().isEmpty()) {
            rentalProduct.setAvailableFrom(DateHelper.getStringToTimeStamp(productEditFrom.getAvailableFrom(), "dd-MM-yyyy"));
        }
        if(!productEditFrom.getAvailableTill().isEmpty()){
            rentalProduct.setAvailableTill(DateHelper.getStringToTimeStamp(productEditFrom.getAvailableTill(), "dd-MM-yyyy"));
        }

        if(productEditFrom.getRentTypeId()!=null && productEditFrom.getRentTypeId()>0){
            rentalProduct.setRentType(rentTypeModel.getById(productEditFrom.getRentTypeId()));
        }

        /* LOCATION */
        if(!productEditFrom.getFormattedAddress().isEmpty()){
            productLocation.setFormattedAddress(productEditFrom.getFormattedAddress());
        }
        if(!productEditFrom.getCity().isEmpty()){
            productLocation.setCity(productEditFrom.getCity());
        }
        if(productEditFrom.getStateId()!=null){
            productLocation.setState(usState);
        }
        if(!productEditFrom.getZip().isEmpty()){
            productLocation.setZip(productEditFrom.getZip());
        }
        if(productEditFrom.getLat()!=null){
            productLocation.setLat(productEditFrom.getLat());
        }
        if(productEditFrom.getLng()!=null){
            productLocation.setLng(productEditFrom.getLng());
        }

        if(productEditFrom.getCurrentValue()!=null && productEditFrom.getCurrentValue()>0){
            rentalProduct.setCurrentValue(productEditFrom.getCurrentValue());
        }
        if(productEditFrom.getRentFee()!=null && productEditFrom.getRentFee()>0){
            rentalProduct.setRentFee(productEditFrom.getRentFee());
        }
        System.out.println(productEditFrom);






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
        /* In-case product does not had a Location */
        if(rentalProduct.getProductLocation() != productLocation){
            // productLocation.setRentalProduct(rentalProduct);
            //  productLocationModel.insert(productLocation);

            productLocation.setRentalProduct(rentalProduct);


        }

        if(productEditFrom.getCategoryIdArray()!=null && productEditFrom.getCategoryIdArray().length>0){
            List<ProductCategory> productCategoryList = rentalProduct.getProductCategories();
            for(ProductCategory productCategory : productCategoryList ){
                productCategoryModel.delete(productCategory);
            }
            productCategoryList = new ArrayList<>();
            for(Integer productCategoryId : productEditFrom.getCategoryIdArray()){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setCategory(categoryModel.getById(productCategoryId));
                productCategoryList.add(productCategory);
            }
            rentalProduct.setProductCategories(productCategoryList);
        }

        rentalProduct.setProductLocation(productLocation);
        productModel.update(rentalProduct);
        serviceResponse.setResponseData(productModel.getById(rentalProduct.getId()));

        return serviceResponse;
    }

    @RequestMapping(value = "/delete-product/{product_id}", method = RequestMethod.POST)
    public ServiceResponse deleteProduct(HttpServletRequest request,
                                         @PathVariable("product_id") int productId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct.getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("productId", "You are not the owner of this product ");
            return serviceResponse;
        }

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

        if(rentalProduct.getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("productId", "You are not the owner of this product ");
            return serviceResponse;
        }

        if(path == null ){
            serviceResponse.setRequestError("path", "Product image path required");
            return serviceResponse;
        }

        path = path.trim();
        if(path.isEmpty() ){
            serviceResponse.setRequestError("path", "Product image path required");
            return serviceResponse;
        }

        if(rentalProduct == null){
            serviceResponse.setRequestError("product", "Product Not found");
            return serviceResponse;
        }

        if(rentalProduct.getOtherImages() == null){
            serviceResponse.setRequestError("product", "product has no other images");
            return  serviceResponse;
        }

        path = path.trim();

        List<Picture> pictureList = rentalProduct.getOtherImages();
        boolean findfile = false;
        for(int i= 0; i<pictureList.size(); i++){
            if(pictureList.get(i).getOriginal().getPath().equals(path)){
                pictureList.remove(i);
                findfile = true;
                break;
            }
        }

        if(!findfile){
            serviceResponse.setRequestError("path", "product image path not found");
            return  serviceResponse;
        }

        rentalProduct.setOtherImages(pictureList);

        productModel.update(rentalProduct);

        serviceResponse.setResponseData(rentalProduct.getOtherImages());

        serviceResponse.getResponseStat().setMsg("Picture Delete Successful");
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
        product.setAverageRating((float) averageRate);
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
            serviceResponse.setRequestError("alreadyRated", "You have already review this product");
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

