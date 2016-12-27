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
@RequestMapping("/api/signin")
public class AuthService  {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @RequestMapping(value = "/by-accesstoken", method = RequestMethod.POST)
    public ServiceResponse authenticateByAccessToken(HttpServletRequest request ,@RequestParam String accessToken){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(accessToken.isEmpty()){
            serviceResponse.setRequestError("accessToken", "Access token required");
        }
        AuthCredential authCredential = appLoginCredentialModel.authenticationByAccessToken(accessToken);


        if(authCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("Invalid access token !! Ha ha ha ....");
            return serviceResponse;
        }else if(!authCredential.getEmailConfirmed()){
            serviceResponse.getResponseStat().setErrorMsg("Email is not confirmed");
            return serviceResponse;
        } else if(!authCredential.isVerified()){
            serviceResponse.getResponseStat().setErrorMsg("This account is not verified by system admin");
            return serviceResponse;
        } else if(authCredential.isBlocked()){
            serviceResponse.getResponseStat().setErrorMsg("This account is blocked by system admin");
            return serviceResponse;
        }else{
            serviceResponse.setResponseData(authCredential);
        }

        serviceResponse.getResponseStat().setMsg("Login success, Redirecting.....");
        /************** Session Creation *******/
        SessionManagement.setAppCredentialInSession(request, serviceResponse, appLoginCredentialModel.getAppCredentialById(authCredential.getId()));
        SessionManagement.setVerificationSession(request, authCredential.isVerified());

        serviceResponse.setResponseData(authCredential);
        return serviceResponse;
    }
    @RequestMapping(value = "/by-email-password", method = RequestMethod.POST)
    public ServiceResponse authenticateByEmailPassword(HttpServletRequest request,@RequestParam String email,@RequestParam String password){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(email.isEmpty() || password.isEmpty()){
            serviceResponse.getResponseStat().setErrorMsg("Email or password is wrong");
        }

        AuthCredential authCredential = appLoginCredentialModel.authenticationByEmailPassword(email,password);

        if(authCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("Invalid email or password");
            return serviceResponse;
        }else if(!authCredential.getEmailConfirmed()){
            serviceResponse.getResponseStat().setErrorMsg("Email is not confirmed");
            return serviceResponse;
        } else if(!authCredential.isVerified()){
            serviceResponse.getResponseStat().setErrorMsg("This account is not verified by system admin");
            return serviceResponse;
        }else if(authCredential.isBlocked()){
            serviceResponse.getResponseStat().setErrorMsg("This account is blocked by system admin");
            return serviceResponse;
        }else{
            serviceResponse.setResponseData(authCredential);
        }

        serviceResponse.getResponseStat().setMsg("Login success, Redirecting.....");
        /************** Session Creation *******/
        SessionManagement.setAppCredentialInSession(request, serviceResponse, appLoginCredentialModel.getAppCredentialById(authCredential.getId()));
        SessionManagement.setVerificationSession(request, authCredential.isVerified());

        serviceResponse.setResponseData(authCredential);
        return serviceResponse;
    }

}