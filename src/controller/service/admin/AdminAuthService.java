package controller.service.admin;

import controller.BaseHttp;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.UserInf;
import model.nonentity.photo.Picture;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by omar on 8/23/16.
 */
@RestController
@RequestMapping("/admin-signin")
public class AdminAuthService{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @RequestMapping(value = "/by-email-password", method = RequestMethod.POST)
    public ServiceResponse adminAuthenticateByEmailPassword(HttpServletRequest request,@RequestParam String email,@RequestParam String password){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        if(email.isEmpty() || password.isEmpty()){
            serviceResponse.getResponseStat().setErrorMsg("Email or password is worng");
        }
        AuthCredential authCredential = appLoginCredentialModel.adminAuthenticationByEmailPassword(email, password);
        if(authCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("Invalid email or password");
            return serviceResponse;
        }else{
            if(!authCredential.isVerified()){
                serviceResponse.getResponseStat().setErrorMsg("Your account is deactivated");
                return serviceResponse;
            }else if(authCredential.isBlocked()){
                serviceResponse.getResponseStat().setErrorMsg("Your account is blocked");
                return serviceResponse;
            }
            serviceResponse.setResponseData(authCredential);
        }
        serviceResponse.getResponseStat().setMsg("Login success");
        SessionManagement.setAdminCredentialInSession(request,serviceResponse,appLoginCredentialModel.getAppCredentialById(authCredential.getId()));

        return serviceResponse;
    }



}
