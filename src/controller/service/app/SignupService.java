package controller.service.app;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.TempFileModel;
import model.entity.app.*;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.entity.AuthCredentialValidator;

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


    @RequestMapping(value="/user",method = RequestMethod.POST)
    public ServiceResponse userSignup(HttpServletRequest request,
                                      @RequestParam Map<String, String> allRequestParams,
                                      @Valid AuthCredential authCredential,
                                      BindingResult result){


        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        /*------------ Parameter Alias---------------*/
        serviceResponse.setParameterAlias("id","identityTypeId");

        String firstName = allRequestParams.get("firstName");
        String lastName = allRequestParams.get("lastName");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        Long identityDocToken = null;
        try{
            if(allRequestParams.get("identityDocToken")==null || allRequestParams.get("identityDocToken")==""){
                serviceResponse.setRequestError("identityDocToken", "Identity doc token required");
            }else{
                identityDocToken = Long.parseLong(allRequestParams.get("identityDocToken"));
            }
        }catch (Exception ex){
            serviceResponse.setRequestError("identityDocToken", "Identity doc token integer required");
            identityDocToken = 0L;
        }


        IdentityType identityType = new IdentityType();
        try{

            identityType.setId(Integer.parseInt(allRequestParams.get("identityTypeId")));
        }catch (Exception ex){
            serviceResponse.setRequestError("identityTypeId", "Identity type id required");
            identityType.setId(0);
        }

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
//        user.setUserAddress(userAddress);
        userInf.setFirstName(firstName);
        userInf.setLastName(lastName);
        userInf.setProfilePicture(new Picture());

        authCredential.setUserInf(userInf);
        authCredential.setRole(-1);
        authCredential.setEmail(email);
        authCredential.setPassword(password);



        authCredential.getUserInf().setIdentityType(identityType);


        /*------------------- Entity Validation ---------------------*/
        new AuthCredentialValidator(appLoginCredentialModel,identityTypeModel,true).validate(authCredential,result);

        // new IdentityTypeValidator(identityTypeModel).validate(identityType, result);

        serviceResponse.setError(result,true,false);

        if(serviceResponse.hasErrors()){

            return serviceResponse;
        }




        /*------------------- Uploaded Document checking By Token -----*/



        TempFile tempFile = this.tempFileModel.getByToken(identityDocToken);
        if(tempFile ==null){
            serviceResponse.setRequestError("identityDocToken", "Identity doc token is not valid");
            return serviceResponse;
        }

        if(!ImageHelper.isFileExist(tempFile.getPath())){
            serviceResponse.setRequestError("identityDocToken", "No file found associated with the token");
            return serviceResponse;
        }

        authCredential.setVerified(true);
        authCredential.setBlocked(false);
        authCredential.getUserInf().setIdentityDocUrl(tempFile.getPath());


        appLoginCredentialModel.insert(authCredential);

        /*----- Move identity doc form temp to original ---- */

        String filePath = ImageHelper.moveFile(authCredential.getId(), tempFile.getPath());



        /*----- Update Information ---- */
        authCredential.getUserInf().setIdentityDocUrl(filePath);

        appLoginCredentialModel.update(authCredential);
        this.tempFileModel.delete(tempFile);

        authCredential = appLoginCredentialModel.getById(authCredential.getId());

        serviceResponse.getResponseStat().setMsg("Signup successful");
        serviceResponse.setResponseData(authCredential);
        return serviceResponse;
    }

}
