package controller.service.admin;

import controller.BaseHttp;
import helper.ServiceResponse;
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
 * Created by omar on 8/23/16.
 */
@RestController
@RequestMapping("/admin-signin")
@Scope("request")
public class AdminAuthService extends BaseHttp{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @RequestMapping(value = "/by-email-password", method = RequestMethod.POST)
    public ServiceResponse adminAuthenticateByEmailPassword( @RequestParam String email,@RequestParam String password){

        if(email.isEmpty() || password.isEmpty()){
            this.serviceResponse.getResponseStat().setErrorMsg("Email or password is worng");
        }
        AuthCredential authCredential = appLoginCredentialModel.adminAuthenticationByEmailPassword(email, password);
        if(authCredential==null){
            this.serviceResponse.getResponseStat().setErrorMsg("Invalid email or password");
            return serviceResponse;
        }else{
            this.serviceResponse.setResponseData(authCredential);
        }
        serviceResponse.getResponseStat().setMsg("Login success");
        this.setAppcredentialInSession(appLoginCredentialModel.getAppCredentialById(authCredential.getId()));

        return this.serviceResponse;
    }
}
