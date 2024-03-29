package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import model.CategoryModel;
import model.IdentityTypeModel;
import model.RentTypeModel;
import model.StateModel;
import model.entity.State;
import model.entity.app.Category;
import model.entity.app.IdentityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by omar on 8/2/16.
 */
@RestController
@RequestMapping("/api/utility")
public class UtilityServices {

    @Autowired
    IdentityTypeModel identityTypeModel;
    @Autowired
    CategoryModel categoryModel;
    @Autowired
    RentTypeModel rentTypeModel;
    @Autowired
    StateModel stateModel;

    @RequestMapping(value = "/get-identity", method = RequestMethod.GET)
    public ServiceResponse getAllIdentityType(HttpServletRequest request){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        serviceResponse.setResponseData(identityTypeModel.getAll());
        return  serviceResponse;
    }

    @RequestMapping(value = "/get-identity/{id}", method = RequestMethod.GET)
    public ServiceResponse getById(HttpServletRequest request,@PathVariable("id")int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(identityTypeModel.getById(id));
        return  serviceResponse;
    }
    @RequestMapping(value = "/get-category", method = RequestMethod.GET)
    public ServiceResponse getAllCategory(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getAll(),"No category found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-parent-category/", method = RequestMethod.GET)
    public ServiceResponse getAllParentCategoryById(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getAllCategoryParent(),"No category found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-category/{id}", method = RequestMethod.GET)
    public ServiceResponse getAllCategoryById(HttpServletRequest request,@PathVariable("id")int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getById(id),"No category found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-subcategory/{parentId}", method = RequestMethod.GET)
    public ServiceResponse getSubCategoryByPrentId(HttpServletRequest request,@PathVariable("parentId")int parentId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getByParentId(parentId),"No subcategory found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-rent-type/{id}", method = RequestMethod.GET)
    public ServiceResponse getRentTypeById(HttpServletRequest request,@PathVariable("id")int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(rentTypeModel.getById(id),"No rent type found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-rent-type", method = RequestMethod.GET)
    public ServiceResponse getRentType(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(rentTypeModel.getAll(),"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-all-parent-subcategory-id/{id}", method = RequestMethod.GET)
    public ServiceResponse getRentType(HttpServletRequest request,@PathVariable int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getAllParentBySubcategoryId(id),"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-parent-by-subcategory-id/{id}", method = RequestMethod.GET)
    public ServiceResponse getParentBySubcategoryId(HttpServletRequest request, @PathVariable int id){
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        serviceResponse.setResponseData(categoryModel.getParentCategory(id), "NO record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-city-by-state-id/{stateId}", method = RequestMethod.GET)
    public ServiceResponse getByCode(HttpServletRequest request, @PathVariable("stateId") int  stateId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        State state = stateModel.getById(stateId);
        if(state == null){
            serviceResponse.setRequestError("stateId", "No city found");
            return serviceResponse;
        }

        serviceResponse.setResponseData(state.getCities());
        return  serviceResponse;
    }
    @RequestMapping(value = "/get-city-by-code/{stateCode}", method = RequestMethod.GET)
    public ServiceResponse getByCode(HttpServletRequest request, @PathVariable("stateCode") String  stateCode){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        State state = stateModel.getByCode(stateCode);
        if(state == null){
            serviceResponse.setRequestError("stateId", "No city found");
            return serviceResponse;
        }

        serviceResponse.setResponseData(state.getCities());
        return  serviceResponse;
    }
}
