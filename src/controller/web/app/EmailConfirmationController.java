package controller.web.app;

import model.AppLoginCredentialModel;
import model.EmailConfirmationModel;
import model.entity.app.AuthCredential;
import model.entity.app.EmailConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi on 10/19/16.
 */
@Controller
@RequestMapping("/email-confirmation")
public class EmailConfirmationController {
        @Autowired
        EmailConfirmationModel emailConfirmationModel;
        @Autowired
        AppLoginCredentialModel appLoginCredentialModel;

        @RequestMapping(method = RequestMethod.GET,value = "/confirm/{email}/{token}")
        public ModelAndView confirmEmail(@PathVariable String  email,@PathVariable String  token){
            ModelAndView modelAndView = new ModelAndView("email_confirmation/emailConfirmed");
            EmailConfirmation emailConfirmation = emailConfirmationModel.getByEmailAndToken(email, token);
            String statusMsg = "";
            if(emailConfirmation!=null){
                if(emailConfirmation.getAlreadyUsed()){
                    statusMsg = "Confirmation token is ready been used";
                }
                emailConfirmation.setAlreadyUsed(true);
                emailConfirmationModel.update(emailConfirmation);

                AuthCredential authCredential = appLoginCredentialModel.getById(emailConfirmation.getAppCredential().getId());
                authCredential.setEmailConfirmed(true);

                appLoginCredentialModel.update(authCredential);

                statusMsg = "Email is confirmed";

            }
            modelAndView.addObject("statusMsg",statusMsg);
            return modelAndView;
        }
        @RequestMapping(method = RequestMethod.GET,value = "/deny/{email}/{token}")
        public ModelAndView denyEmail(@PathVariable String  email,@PathVariable String  token){


            ModelAndView modelAndView = new ModelAndView("email_confirmation/emailDeny");
            EmailConfirmation emailConfirmation = emailConfirmationModel.getByEmailAndToken(email, token);
            String statusMsg = "";
            if(emailConfirmation!=null){
                emailConfirmation.setAlreadyUsed(true);
                emailConfirmationModel.delete(emailConfirmation);

                AuthCredential authCredential = appLoginCredentialModel.getById(emailConfirmation.getAppCredential().getId());
                authCredential.setEmailConfirmed(true);

                appLoginCredentialModel.delete(authCredential);

                statusMsg = "You email is un subscribed ..";

            }
            modelAndView.addObject("statusMsg",statusMsg);
            return modelAndView;
        }

}
