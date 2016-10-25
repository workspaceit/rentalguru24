package controller.service.app;


import controller.service.BaseService;
import library.RentGuruMail;
import model.*;

import model.entity.app.*;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

/**
 * Created by omar on 8/1/16.
 */
@RestController
@RequestMapping("/api/app")
@Scope("request")
public class TestService extends BaseService{
    @Autowired
    AttributesModel attributesModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    IdentityTypeModel identityTypeModel;

    @Autowired
    RentRequestModel rentRequestModel;

    @Autowired
    RentInfModel rentInfModel;

    @Autowired
    RentGuruMail rentGuruMail;

    @Autowired
    ProductModel productModel;

    @Autowired
    AdminGlobalNotificationModel adminGlobalNotificationModel;

    @Autowired
    AdminGlobalNotificationTemplateModel adminGlobalNotificationTemplateModel;

    @Autowired
    ProductRatingModel productRatingModel;
    @RequestMapping(value = "/test/radius", method = RequestMethod.POST)
    public List<RentalProduct> getByRadius( @RequestParam double centralLatitude,
                                              @RequestParam double centralLongitude,
                                              @RequestParam float radius,
                                              @RequestParam int limit,
                                              @RequestParam int offset){
        return productModel.getRentalProductByDistance(centralLatitude,centralLongitude,radius,offset,limit);
    }
    @RequestMapping(value = "/test/att", method = RequestMethod.POST)
    public void postAttribute(@RequestParam Map<String, String> allRequestParams){

//        String attName = allRequestParams.get("att_name");
//        String attCreatedBy = allRequestParams.get("att_created_by");

        Attributes attributes = new Attributes();
        attributes.setName("hhy");
        attributes.setCreatedBy(1);

        AttributeValues attributeValues1 = new AttributeValues();
        attributeValues1.setName("ooo");
        attributeValues1.setCreatedBy(1);

        AttributeValues attributeValues2 = new AttributeValues();
        attributeValues2.setName("ppp");
        attributeValues2.setCreatedBy(1);

        Set<AttributeValues> attributeValuesarr = new HashSet<AttributeValues>();
        attributeValuesarr.add(attributeValues1);
        attributeValuesarr.add(attributeValues2);

        attributes.setAttributeValuesById(attributeValuesarr);
        attributesModel.insert(attributes);
    }
    @RequestMapping(value = "/test/getatt", method = RequestMethod.GET)
    public Attributes getAttribute(){
       return attributesModel.getById();
    }

    @RequestMapping(value = "/test/category", method = RequestMethod.POST)
    public void postCategory(){

//        Category category = new Category();
//        category.setName("parent_category");
//        category.setParent(null);
//        category.setSortedOrder(2);
//        category.setCreatedBy(1);
//
//        Category subCategory1 = new Category();
//        Category subCategory2 = new Category();
//
//        subCategory1.setName("child1");
//        subCategory1.setSortedOrder(3);
//        subCategory1.setCreatedBy(1);
//        subCategory1.setParent(category);
//
//        subCategory2.setName("child2");
//        subCategory2.setSortedOrder(4);
//        subCategory2.setCreatedBy(1);
//        subCategory2.setParent(category);
//
//        Set<Category> subCategories = new HashSet<Category>();
//        subCategories.add(subCategory1);
//        subCategories.add(subCategory2);
////        category.setSubcategory(subCategories);
//        categoryModel.insert(category);
    }
    @RequestMapping(value = "/test/iddoc", method = RequestMethod.POST)
    public void postIdentityDoc(){
        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();

        tempFile.setToken(15845648);
        tempFile.setPath("pqrst");

        this.tempFileModel.insert(tempFile);
    }

    @RequestMapping(value = "/test/idtype", method = RequestMethod.POST)
    public void postIdentityType(){
        IdentityType identityType = new IdentityType();
        identityType.setName("pqrst");
        identityTypeModel.insert(identityType);
    }

    @RequestMapping(value = "/test/getbytoken", method = RequestMethod.GET)
    public model.entity.app.TempFile getToken(){
        return tempFileModel.getByToken(15845648);
    }

    @RequestMapping(value = "/test/idtyp/getbyid", method = RequestMethod.GET)
    public IdentityType getIdeTypGetById(){
        return identityTypeModel.getById(1);
    }

    @RequestMapping(value = "/tset/idtyp/getall", method = RequestMethod.GET)
    public List<IdentityType> getAllIdTyp(){
        return identityTypeModel.getAll();
    }
    @RequestMapping(value = "/test/rentrequest", method = RequestMethod.POST)
    public void postRentRequsest(){

        RentRequest rentRequest = new RentRequest();
//        rentRequest.setProductId(1);
//        rentRequest.setRequestedBy(1);
        rentRequest.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
        rentRequest.setEndDate(new Date(Calendar.getInstance().getTime().getTime()));

        rentRequestModel.insert(rentRequest);
    }
    @RequestMapping(value = "/test/rentproduct", method = RequestMethod.POST)
    public void postRentProduct(){
        RentInf rentInf = new RentInf();

       // rentProduct.setRentalProduct(rentProduct);
        rentInf.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
        rentInf.setEndsDate(new Date(Calendar.getInstance().getTime().getTime()));
        rentInf.setExpired(false);

//        RentRequest rentRequest1 = new RentRequest();
//        rentRequest1.setProductId(1);
//        rentRequest1.setRequestedBy(1);
//        rentRequest1.setRequestId(null);
//        rentRequest1.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
//        rentRequest1.setEndDate(new Date(Calendar.getInstance().getTime().getTime()));
//
//        RentRequest rentRequest2 = new RentRequest();
//        rentRequest2.setProductId(1);
//        rentRequest2.setRequestedBy(1);
//        rentRequest2.setRequestId(null);
//        rentRequest2.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
//        rentRequest2.setEndDate(new Date(Calendar.getInstance().getTime().getTime()));
//
//        Set<RentRequest> rentRequestsarr = new HashSet<RentRequest>();
//        rentRequestsarr.add(rentRequest1);
//        rentRequestsarr.add(rentRequest2);
//
//        rentProduct.setRentRequestsById(rentRequestsarr);

        rentInfModel.insert(rentInf);
    }


    @RequestMapping(value = "/partial-rendering/category/{category_id}", method = RequestMethod.GET)
    public List<RentalProduct> getCategory(@PathVariable("category_id") int category_id){
        List rentalProduct = productModel.getRentalProductByCategoryId(category_id);

        return rentalProduct;
    }

//    @RequestMapping(value = "/date/{productId}/{rentInfId}", method = RequestMethod.GET)
//    public List<Object> getDatesRemaining(@PathVariable("productId") int productId, @PathVariable("rentInfId") int rentInfId){
//        RentalProduct rentalProduct = productModel.getById(productId);
//        RentInf rentInf = rentInfModel.getById(rentInfId);
//        List<Objects>
//        objects.add(rentalProduct);
//        objects.add(rentInf);
//        return objects;
//    }
//    @RequestMapping(value = "/global-notification-add", method = RequestMethod.POST)
//    public ServiceResponse postglobalNotification(HttpServletRequest request, @RequestParam Map<String, String> allRequestParams){
//        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
//        String details = allRequestParams.get("details");
//        String type = allRequestParams.get("type");
//        Integer notificationTemplateId = Integer.parseInt(allRequestParams.get("templateId"));
//
//        if(details.isEmpty() && details == null){
//            serviceResponse.setRequestError("details", "Details field required");
//            return serviceResponse;
//        }
//        if(type.isEmpty() && type == null){
//            serviceResponse.setRequestError("type", "Type field required");
//            return serviceResponse;
//        }
//
//        if(notificationTemplateId == null && notificationTemplateId < 0){
//            serviceResponse.setRequestError("notificationTemplateId", "notification template field required");
//            return serviceResponse;
//        }
//
//        AdminGlobalNotificationTemplate adminGlobalNotificationTemplate = adminGlobalNotificationTemplateModel.getById(notificationTemplateId);
//
//        if(adminGlobalNotificationTemplate == null){
//            serviceResponse.setRequestError("notificationTemplateId", "No template found");
//            return serviceResponse;
//        }
//
//        AdminGlobalNotification adminGlobalNotification =  new AdminGlobalNotification();
//
//        adminGlobalNotification.setDetails(details);
//        adminGlobalNotification.setType(type);
//        adminGlobalNotification.setNotificationTemplate(adminGlobalNotificationTemplate);
//        serviceResponse.getResponseStat().setMsg("Notification send successfully");
//        return serviceResponse;
//    }
//
//    @RequestMapping(value = "/global-notification/template-add", method = RequestMethod.POST)
//    public ServiceResponse potGlobalNotificationtemplate(HttpServletRequest request){
//
//    }
//    @RequestMapping(value = "/fiend-product-rating", method = RequestMethod.POST)
//    public ServiceResponse getProductRating(HttpServletRequest request, @RequestParam int productId){
//        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
//        List<ProductRating> productRatings = productRatingModel.getByProductId(productId);
//        serviceResponse.setResponseData(productRatings);
//        return serviceResponse;
//    }

}
