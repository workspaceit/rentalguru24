package controller.service.app;

import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
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



        new ProfileValidator().validate(profileForm,result);
        serviceResponse.setError(result,true,false);

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        System.out.println("getFirstName " + profileForm.getFirstName());
        System.out.println("getLastName "+ profileForm.getLastName());

        return  serviceResponse;
    }
}
