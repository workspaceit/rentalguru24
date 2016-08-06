package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import model.CategoryModel;
import model.IdentityTypeModel;
import model.entity.app.Category;
import model.entity.app.IdentityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by omar on 8/2/16.
 */
@RestController
@RequestMapping("/api/utility")
@Scope("request")
public class UtilityServices extends BaseService{

    @Autowired
    IdentityTypeModel identityTypeModel;
    @Autowired
    CategoryModel categoryModel;

    @RequestMapping(value = "/get-identity", method = RequestMethod.GET)
    public ServiceResponse getAllIdentityType(){
        this.serviceResponse.setResponseData(identityTypeModel.getAll());
        return  this.serviceResponse;
    }

    @RequestMapping(value = "/get-identity/{id}", method = RequestMethod.GET)
    public ServiceResponse getById(@PathVariable("id")int id){

        this.serviceResponse.setResponseData(identityTypeModel.getById(id));
        return  this.serviceResponse;
    }
    @RequestMapping(value = "/get-category", method = RequestMethod.GET)
    public ServiceResponse getAllCategory(){

        this.serviceResponse.setResponseData(categoryModel.getAll(),"No category found");
        return this.serviceResponse;
    }
    @RequestMapping(value = "/get-category/{id}", method = RequestMethod.GET)
    public ServiceResponse getAllCategoryById(@PathVariable("id")int id){

        this.serviceResponse.setResponseData(categoryModel.getById(id),"No category found");
        return this.serviceResponse;
    }
    @RequestMapping(value = "/get-subcategory/{parentId}", method = RequestMethod.GET)
    public ServiceResponse getSubCategoryByPrentId(@PathVariable("parentId")int parentId){

        this.serviceResponse.setResponseData(categoryModel.getByParentId(parentId),"No subcategory found");
        return this.serviceResponse;
    }

}
