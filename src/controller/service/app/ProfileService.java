package controller.service.app;

import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.form.ProfileValidator;
import validator.form.class_file.ProductUploadForm;
import validator.form.class_file.ProfileForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by mi on 8/25/16.
 */
@RestController
@RequestMapping("/api/auth/profile")
public class ProfileService {
    @Autowired
    AppLoginCredentialModel appCredentialModel;
    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public ServiceResponse editProfile(HttpServletRequest request,
                                       @RequestParam Map<String,String> allRequestParameter,
                                       @Valid ProfileForm profileForm,
                                       BindingResult result
                                        ){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");



        new ProfileValidator().validate(profileForm, result);
        serviceResponse.setError(result,true,false);

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        AuthCredential authCredential = appCredentialModel.getById(appCredential.getId());

        System.out.println("getFirstName " + profileForm.getFirstName());
        System.out.println("getLastName "+ profileForm.getLastName());
        System.out.println("getEmail "+ profileForm.getEmail());
        System.out.println("getNewPassword " + profileForm.getNewPassword());
        System.out.println("getOldPassword "+ profileForm.getOldPassword());



        if(!profileForm.getEmail().isEmpty()) {
            authCredential.setEmail(profileForm.getEmail());
        }
        if(!profileForm.getFirstName().isEmpty()) {
            authCredential.getUserInf().setFirstName(profileForm.getFirstName());
        }
        if(!profileForm.getLastName().isEmpty()){
            authCredential.getUserInf().setFirstName(profileForm.getLastName());
        }

        if(!profileForm.getNewPassword().isEmpty()){
            boolean isOldPassword = authCredential.getPassword().equals(appCredentialModel.getPasswordAsMd5DigestAsHex(profileForm.getOldPassword()));
            if(!isOldPassword){
                serviceResponse.setRequestError("oldPassword","Password miss matched");
            }
            authCredential.setPassword(profileForm.getNewPassword());
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        return  serviceResponse;
    }
}
