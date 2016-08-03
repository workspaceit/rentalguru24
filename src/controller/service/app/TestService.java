package controller.service.app;


import model.AttributesModel;
import model.CategoryModel;
import model.TempFileModel;
import model.IdentityTypeModel;
import model.entity.app.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by omar on 8/1/16.
 */
@RestController
@RequestMapping("/api/app")
public class TestService {
    @Autowired
    AttributesModel attributesModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    IdentityTypeModel identityTypeModel;

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

        Category category = new Category();
        category.setName("parent_category");
        category.setParent(null);
        category.setSortedOrder(2);
        category.setCreatedBy(1);

        Category subCategory1 = new Category();
        Category subCategory2 = new Category();

        subCategory1.setName("child1");
        subCategory1.setSortedOrder(3);
        subCategory1.setCreatedBy(1);
        subCategory1.setParent(category);

        subCategory2.setName("child2");
        subCategory2.setSortedOrder(4);
        subCategory2.setCreatedBy(1);
        subCategory2.setParent(category);

        Set<Category> subCategories = new HashSet<Category>();
        subCategories.add(subCategory1);
        subCategories.add(subCategory2);
//        category.setSubcategory(subCategories);
        categoryModel.insert(category);
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

}
