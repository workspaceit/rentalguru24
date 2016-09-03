package controller.service.app;


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
public class PasswordResetService extends UtilituHelper{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    PasswordResetModel passwordResetModel;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ServiceResponse setPasswordResetRequest(HttpServletRequest request, @RequestParam String email){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(email);
        AuthCredential authCredential = appLoginCredentialModel.getByEmail(email);

        if(appCredential == null){
            serviceResponse.setRequestError("email","given email doesn't exist in system");
            return serviceResponse;
        } else {
            Boolean isExist = passwordResetModel.isExist(appCredential.getId());
            String token = appLoginCredentialModel.getPasswordAsMd5DigestAsHex(email+getRandomNumber());
            if(isExist){
                PasswordResetsEntity passwordResetsEntity = passwordResetModel.getByAppCredentialId(appCredential.getId());
                passwordResetModel.delete(passwordResetsEntity);
                PasswordResetsEntity passwordResetsEntityNew = new PasswordResetsEntity();
                passwordResetsEntityNew.setToken(token);
                passwordResetsEntityNew.setAuthCredential(authCredential);
                serviceResponse.setResponseData(passwordResetModel.insert(passwordResetsEntityNew));
                serviceResponse.getResponseStat().setMsg("Password reset successful");
            }else {
                PasswordResetsEntity passwordResetsEntity = new PasswordResetsEntity();
                passwordResetsEntity.setToken(token);
                passwordResetsEntity.setAuthCredential(authCredential);
                serviceResponse.setResponseData(passwordResetModel.insert(passwordResetsEntity));
                serviceResponse.getResponseStat().setMsg("Password reset successful");
            }
            return serviceResponse;
        }
    }

    @RequestMapping(value = "/change-password/{token}", method = RequestMethod.POST)
    public ServiceResponse setChangePassword(HttpServletRequest request, @RequestParam Map<String, String> allRequestParams, @PathVariable("token") String token){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        String conPassword = allRequestParams.get("conPassword");

        AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(email);
        PasswordResetsEntity passwordResetsEntity = passwordResetModel.getByAppCredentialId(appCredential.getId());

        String validToken = passwordResetsEntity.getToken();
        System.out.println("valid = "+validToken);
        System.out.println("token = " + token);

        if(appCredential == null){
            serviceResponse.setRequestError("email","given email doesn't exist in system");
            return serviceResponse;
        }else{
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
                    serviceResponse.setRequestError("password","Password mismatch");
                    return serviceResponse;
                }
            }else {
                serviceResponse.setRequestError("token","Password reset token mismatch ");
                return serviceResponse;
            }

        }
        return serviceResponse;
    }
}
