package controller.service.app;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import helper.EmailHelper;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.EmailConfirmationModel;
import model.IdentityTypeModel;
import model.TempFileModel;
import model.entity.app.*;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.form.SignUpFormValidator;
import validator.form.class_file.SignUpForm;

import java.util.*;

/**
 * Created by omar on 7/21/16.
 */
@RestController

@RequestMapping("api/signup")
public class SignupService{
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;
    @Autowired
    TempFileModel tempFileModel;
    @Autowired
    IdentityTypeModel identityTypeModel;
    @Autowired
    EmailConfirmationModel emailConfirmationModel;

    @RequestMapping(value="/user",method = RequestMethod.POST)
    public ServiceResponse userSignUp(HttpServletRequest request,
                                      @RequestParam Map<String, String> allRequestParams,
                                      @Valid SignUpForm signUpForm,
                                      BindingResult result){


        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");


//        String city = allRequestParams.get("city");
//        String zip = allRequestParams.get("zip");
//        String state = allRequestParams.get("state");
//        String address = allRequestParams.get("address");

//        UserAddress userAddress = new UserAddress();
//        userAddress.setAddress(address);
//        userAddress.setZip(zip);
//        userAddress.setState(state);
//        userAddress.setCity(city);

        UserInf userInf = new UserInf();
        userInf.setFirstName(signUpForm.getFirstName());
        userInf.setLastName(signUpForm.getLastName());
        userInf.setProfilePicture(new Picture());
//        user.setUserAddress(userAddress);


        AuthCredential authCredential = new AuthCredential();
        authCredential.setUserInf(userInf);
        authCredential.setRole(-1);
        authCredential.setEmail(signUpForm.getEmail());
        authCredential.setPassword(signUpForm.getPassword());

        IdentityType identityType =  identityTypeModel.getFirst();

        authCredential.getUserInf().setIdentityType(identityType);


        /*------------------- Entity Validation ---------------------*/
        new SignUpFormValidator(appLoginCredentialModel,identityTypeModel).validate(signUpForm,result);


        serviceResponse.setError(result,true,false);

        if(serviceResponse.hasErrors()){

            return serviceResponse;
        }




        /*------------------- Uploaded Document checking By Token -----*/


    //    Long identityDocToken = signUpForm.getIdentityDocToken();
//        TempFile tempFile = this.tempFileModel.getByToken(identityDocToken);
//        if(tempFile ==null){
//            serviceResponse.setRequestError("identityDocToken", "Identity doc token is not valid");
//            return serviceResponse;
//        }
//
//        if(!ImageHelper.isFileExist(tempFile.getPath())){
//            serviceResponse.setRequestError("identityDocToken", "No file found associated with the token");
//            return serviceResponse;
//        }

        authCredential.setVerified(false);
        authCredential.setEmailConfirmed(false); // Need to be set false before lunch
        authCredential.setBlocked(false);
        //authCredential.getUserInf().setIdentityDocUrl(tempFile.getPath());


        appLoginCredentialModel.insert(authCredential);

        /*----- Move identity doc form temp to original ---- */

        //String filePath = ImageHelper.moveFile(authCredential.getId(), tempFile.getPath());



        /*----- Update Information ---- */
//        authCredential.getUserInf().setIdentityDocUrl(filePath);
        authCredential.getUserInf().setIdentityDocUrl("");

        appLoginCredentialModel.update(authCredential);

        /*Deleting temp entry from database */

        //this.tempFileModel.delete(tempFile);

        authCredential = appLoginCredentialModel.getById(authCredential.getId());
        AppCredential appCredential = appLoginCredentialModel.getAppCredentialById(authCredential.getId());
        /*~~~~ Send  Email confirmation mail*/

        EmailConfirmation emailConfirmation = new EmailConfirmation();
        emailConfirmation.setAppCredential(appCredential);
        emailConfirmation.setAlreadyUsed(false);
        emailConfirmationModel.insert(emailConfirmation);
        System.out.println("Before async ");


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("Email sending init");
                EmailHelper.signUpConfirmationMail(emailConfirmation.getAppCredential().getEmail(),
                        emailConfirmation.getToken(),
                        baseUrl + "/email-confirmation/confirm/",
                        baseUrl + "/email-confirmation/deny/");
                System.out.print("Email sent");
            }
        }).start();



        System.out.println("After async ");

        serviceResponse.getResponseStat().setMsg("Signup successful");
        serviceResponse.setResponseData(authCredential);
        return serviceResponse;
    }

    @RequestMapping(value = "/email-check", method = RequestMethod.POST)
    public ServiceResponse isEmailAlreadyExist(HttpServletRequest request, @RequestParam("email") String email){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean isEmailExist = appLoginCredentialModel.isEmailExist(email);
        if(isEmailExist == true){
            serviceResponse.setRequestError("email", "email already taken");
        }
        serviceResponse.setResponseData(isEmailExist);
        return  serviceResponse;
    }

}
