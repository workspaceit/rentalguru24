package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/signout")
public class SignoutService{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @RequestMapping( method = RequestMethod.GET)
    public ServiceResponse signOut(HttpServletRequest request){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        SessionManagement.destroySession(request);

        serviceResponse.getResponseStat().setIsLogin(false);

        return serviceResponse;
    }

}