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

}
