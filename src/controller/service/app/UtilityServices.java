package controller.service.app;

import helper.ServiceResponse;
import model.IdentityTypeModel;
import model.entity.app.IdentityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by omar on 8/2/16.
 */
@RestController
@RequestMapping("/api/utility")
@Scope("request")
public class UtilityServices {
    ServiceResponse serviceResponse;
    public UtilityServices(){
        this.serviceResponse = new ServiceResponse();
    }
    @Autowired
    IdentityTypeModel identityTypeModel;

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
}