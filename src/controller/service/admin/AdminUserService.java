package controller.service.admin;


import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 8/24/16.
 */


@RestController
@RequestMapping("/api-admin")
public class AdminUserService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;
    @RequestMapping(value = "/app-user/active-app-user/{app_user_id}/{varified}", method = RequestMethod.POST)
    public ServiceResponse setActiveAppUser(HttpServletRequest request, @PathVariable("app_user_id") int appUserId, @PathVariable("varified") int varified){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }
        appLoginCredentialModel.appUserStatusUpdate(appUserId,varified);
        return serviceResponse;
    }
}
