package controller.service.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.AppLoginCredentialModel;
import model.TempFileModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.TempFile;
import model.nonentity.photo.Picture;
import org.hibernate.Session;
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
    @Autowired
    TempFileModel tempFileModel;


    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public ServiceResponse editProfile(HttpServletRequest request,
                                       @RequestParam Map<String,String> allRequestParameter,
                                       @Valid ProfileForm profileForm,
                                       BindingResult result
                                        ){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


            /* Basic Validation */
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
        System.out.println("getProfileImageToken "+ profileForm.getProfileImageToken());


       /* Other Validation */
        boolean isPasswordChanged = false;
        if(!profileForm.getEmail().isEmpty()) {
            if(appCredentialModel.isEmailUsedByOtherButMe(authCredential.getId(),profileForm.getEmail())){
                serviceResponse.setRequestError("email","Email is used but other user");
            }else{
                authCredential.setEmail(profileForm.getEmail());
            }
        }

        if(!profileForm.getFirstName().isEmpty()) {
            authCredential.getUserInf().setFirstName(profileForm.getFirstName());
        }

        if(!profileForm.getLastName().isEmpty()){
            authCredential.getUserInf().setLastName(profileForm.getLastName());
        }

        if(!profileForm.getNewPassword().isEmpty()){
            isPasswordChanged = true;
            boolean isOldPassword = appCredentialModel.isPasswordMatched(authCredential,profileForm.getOldPassword());
            if(!isOldPassword){
                serviceResponse.setRequestError("oldPassword","Password miss matched");
            }else{
                authCredential.setPassword(profileForm.getNewPassword());
            }
        }

        if(profileForm.getProfileImageToken()>0) {
            TempFile tempFile = this.tempFileModel.getByToken(profileForm.getProfileImageToken());
            if (tempFile == null) {
                serviceResponse.setRequestError("profileImageToken", "Profile Image token is not valid");
                return serviceResponse;
            }
             /*----- Move Product image form temp to original ---- */

            if(!ImageHelper.isFileExist(tempFile.getPath())){
                serviceResponse.setRequestError("profileImageToken", "No file found associated with the token");
                return serviceResponse;
            }

            Picture profileImage = null;
            try {
                profileImage = ImageHelper.moveProfileImage(authCredential.getId(), tempFile.getPath());
            } catch (Exception e) {
                //e.printStackTrace();
                serviceResponse.setRequestError("profileImageToken", "Unable to save profile image");
                return serviceResponse;
            }
            if(profileImage==null){
                serviceResponse.setRequestError("profileImageToken", "Unable to save profile image");
                return serviceResponse;
            }

            authCredential.getUserInf().setProfilePicture(profileImage);
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        /* Update section */
        if(profileForm.isInUpdateState()){
            if(isPasswordChanged){
                appCredentialModel.updateWithNewPassword(authCredential);
                serviceResponse.setResponseData(appCredentialModel.getById(authCredential.getId()));
            }else{
                appCredentialModel.update(authCredential);
                serviceResponse.setResponseData(appCredentialModel.getAppCredentialById(authCredential.getId()));
            }
            SessionManagement.setAppCredentialInSession(request,serviceResponse,appCredentialModel.getAppCredentialById(authCredential.getId()));

        }




        return  serviceResponse;
    }
}
