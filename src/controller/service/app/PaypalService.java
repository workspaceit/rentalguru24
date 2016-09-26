package controller.service.app;

import helper.ServiceResponse;
import model.UserPaypalCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.UserPaypalCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 9/26/16.
 */
@RestController
@RequestMapping("/api/auth/paypal")
public class PaypalService {
    @Autowired
    UserPaypalCredentialModel userPaypalCredentialModel;

    @RequestMapping(value = "/paypal-email-account", method = RequestMethod.POST)
    public ServiceResponse getMyPaypalAccountEmail(HttpServletRequest request, @RequestParam String email){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(email.isEmpty()){
            serviceResponse.setRequestError("email", "Email require");
            return serviceResponse;
        }

        UserPaypalCredential userPaypalCredential = userPaypalCredentialModel.getByAppCredentialId(appCredential.getId());

        if(userPaypalCredential != null){
            if(!userPaypalCredential.getEmail().equals(email)){
                userPaypalCredential.setEmail(email);
                userPaypalCredentialModel.update(userPaypalCredential);
                serviceResponse.getResponseStat().setMsg("Email Successfully Updated");
                return serviceResponse;
            }else {
                serviceResponse.setRequestError("email", "No change in email");
                return  serviceResponse;
            }

        }else {
            UserPaypalCredential userPaypalCredentialNew = new UserPaypalCredential();
            userPaypalCredentialNew.setEmail(email);
            userPaypalCredentialNew.setAppCredential(appCredential);
            userPaypalCredentialModel.insert(userPaypalCredentialNew);
            serviceResponse.getResponseStat().setMsg("Email Successfully Save");
            return serviceResponse;
        }
    }
}
