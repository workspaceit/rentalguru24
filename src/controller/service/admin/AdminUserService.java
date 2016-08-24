package controller.service.admin;


<<<<<<< HEAD
=======
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
>>>>>>> 005e7e06340ec0db489547718f1d5916414f9970

/**
 * Created by omar on 8/24/16.
 */
<<<<<<< HEAD

public class AdminUserService {

=======
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
>>>>>>> 005e7e06340ec0db489547718f1d5916414f9970
}
