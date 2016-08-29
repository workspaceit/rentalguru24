package controller.service.app;


import controller.service.BaseService;
import library.RentGuruMail;
import model.*;

import model.entity.app.*;
import model.entity.app.product.rentable.RentInf;
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





}
