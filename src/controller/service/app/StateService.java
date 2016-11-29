package controller.service.app;

import com.sun.tools.internal.ws.processor.model.Service;
import helper.ServiceResponse;
import model.StateModel;
import model.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 11/29/16.
 */
@RestController
@RequestMapping("/api/state")
public class StateService {

    @Autowired
    StateModel stateModel;

    @RequestMapping(value = "/get-all-state", method = RequestMethod.GET)
    public ServiceResponse getAllState(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        serviceResponse.setResponseData(stateModel.getAll());
        return  serviceResponse;
    }

    @RequestMapping(value = "/get-by-id/{state_id}", method = RequestMethod.GET)
    public ServiceResponse getById(HttpServletRequest request, @PathVariable("state_id") int stateId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        State state = stateModel.getById(stateId);
        if(state == null){
            serviceResponse.setRequestError("stateId", "Request state not found");
            return serviceResponse;
        }

        serviceResponse.setResponseData(state);
        return  serviceResponse;
    }

    @RequestMapping(value = "/get-by-code/{state_code}", method = RequestMethod.GET)
    public ServiceResponse getByCode(HttpServletRequest request, @PathVariable("state_code") String stateCode){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        State state = stateModel.getByCode(stateCode);
        if(state == null){
            serviceResponse.setRequestError("stateCode", "Request state not found");
            return serviceResponse;
        }

        serviceResponse.setResponseData(state);
        return  serviceResponse;
    }

}
