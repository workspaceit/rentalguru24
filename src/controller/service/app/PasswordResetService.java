package controller.service.app;


import helper.MailHelper;
import helper.ServiceResponse;
import helper.UtilituHelper;
import model.AppLoginCredentialModel;
import model.PasswordResetModel;
import model.entity.app.AppCredential;

import model.entity.app.AuthCredential;
import model.entity.app.PasswordResetsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by omar on 9/3/16.
 */
@RestController
@RequestMapping("/api/reset-password")
public class PasswordResetService{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    PasswordResetModel passwordResetModel;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ServiceResponse setPasswordResetRequest(HttpServletRequest request, @RequestParam String email){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(email);
        AuthCredential authCredential = appLoginCredentialModel.getByEmail(email);
        String baseUrl = (String) request.getAttribute("baseURL");

        if(email==null || email==""){
            serviceResponse.setRequestError("email","Email required");
            return serviceResponse;
        }

        email = email.trim();

        if(appCredential == null){
            serviceResponse.setRequestError("email","Given email doesn't exist in system");
            return serviceResponse;
        }

        Boolean isExist = passwordResetModel.isExist(appCredential.getId());
        String token = appLoginCredentialModel.getPasswordAsMd5DigestAsHex(email + UtilituHelper.getRandomNumber());
        if(isExist){
            PasswordResetsEntity passwordResetsEntity = passwordResetModel.getByAppCredentialId(appCredential.getId());
            passwordResetModel.delete(passwordResetsEntity);
            PasswordResetsEntity passwordResetsEntityNew = new PasswordResetsEntity();
            passwordResetsEntityNew.setToken(token);
            passwordResetsEntityNew.setAuthCredential(authCredential);
            serviceResponse.setResponseData(passwordResetModel.insert(passwordResetsEntityNew));
        }else {
            PasswordResetsEntity passwordResetsEntity = new PasswordResetsEntity();
            passwordResetsEntity.setToken(token);
            passwordResetsEntity.setAuthCredential(authCredential);
            serviceResponse.setResponseData(passwordResetModel.insert(passwordResetsEntity));
        }

        serviceResponse.getResponseStat().setMsg("An email is sent please reset it from that link");
        MailHelper.sendPasswordRestMail(email.trim(),token,baseUrl+"/reset-password/change-password/");
        return serviceResponse;
    }

    @RequestMapping(value = "/change-password/{token}", method = RequestMethod.POST)
    public ServiceResponse setChangePassword(HttpServletRequest request, @RequestParam Map<String, String> allRequestParams, @PathVariable("token") String token){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        String conPassword = allRequestParams.get("conPassword");

        AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(email);

        if(appCredential == null){
            serviceResponse.setRequestError("email","given email doesn't exist in system");
            return serviceResponse;
        }

        PasswordResetsEntity passwordResetsEntity = passwordResetModel.getByAppCredentialId(appCredential.getId());
        if(passwordResetsEntity==null){
            serviceResponse.setRequestError("token","Token is not valid");
            return serviceResponse;
        }
        String validToken = passwordResetsEntity.getToken();
        if(token.equals(validToken)){
            if(password.equals(conPassword)){
                if(password.length() >= 6){
                    AuthCredential authCredential = appLoginCredentialModel.getByEmail(email);
                    authCredential.setPassword(password);
                    appLoginCredentialModel.updateWithNewPassword(authCredential);
                    serviceResponse.getResponseStat().setMsg("Password reset successful");
                }else{
                    serviceResponse.setRequestError("password","Password can't be less then 6 character");
                    return serviceResponse;
                }
            }else {
                serviceResponse.setRequestError("conPassword","Password mismatch");
                return serviceResponse;
            }
        }else {
            serviceResponse.setRequestError("token","Password reset token mismatch ");
            return serviceResponse;
        }
        return serviceResponse;
    }
}
