package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/signin")
@Scope("request")
public class AuthService extends BaseService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @RequestMapping(value = "/by-accesstoken", method = RequestMethod.POST)
    public ServiceResponse authenticateByAccessToken(@RequestParam String accessToken){
        if(accessToken.isEmpty()){
            this.serviceResponse.setRequestError("accessToken", "Access token required");
        }
        AuthCredential authCredential = appLoginCredentialModel.authenticationByAccessToken(accessToken);

        if(authCredential==null){
            this.serviceResponse.getResponseStat().setErrorMsg("Invalid access token !! Ha ha ha ....");
            return this.serviceResponse;
        }

        this.serviceResponse.getResponseStat().setMsg("Login success");
        this.setAppcredentialInSession(appLoginCredentialModel.getAppCredentialById(authCredential.getId()));
        this.serviceResponse.setResponseData(authCredential);
        return this.serviceResponse;
    }
    @RequestMapping(value = "/by-email-password", method = RequestMethod.POST)
    public ServiceResponse authenticateByEmailPassword(@RequestParam String email,@RequestParam String password){
        if(email.isEmpty() || password.isEmpty()){
            this.serviceResponse.getResponseStat().setErrorMsg("Email or password is worng");
        }

        AuthCredential authCredential = appLoginCredentialModel.authenticationByEmailPassword(email,password);

        if(authCredential==null){
            this.serviceResponse.getResponseStat().setErrorMsg("Invalid email or password");
            return this.serviceResponse;
        }else{
            this.serviceResponse.setResponseData(authCredential);
        }

        this.serviceResponse.getResponseStat().setMsg("Login success");
        this.setAppcredentialInSession(appLoginCredentialModel.getAppCredentialById(authCredential.getId()));
        return this.serviceResponse;
    }

}