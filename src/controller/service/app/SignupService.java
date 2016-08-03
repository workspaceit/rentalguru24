package controller.service.app;


import javax.validation.Valid;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.AppCredentialModel;
import model.IdentityTypeModel;
import model.TempFileModel;
import model.entity.app.AppCredential;
import model.entity.app.IdentityType;
import model.entity.app.TempFile;
import model.entity.app.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.AppCredentialValidator;
import validator.IdentityTypeValidator;

import java.util.*;

/**
 * Created by omar on 7/21/16.
 */
@RestController

@RequestMapping("api/signup")
@Scope("request")
public class SignupService{

    private ServiceResponse serviceResponse;

    @Autowired
    AppCredentialModel appLoginCredentialModel;
    @Autowired
    TempFileModel tempFileModel;
    @Autowired
    IdentityTypeModel identityTypeModel;


    public SignupService() {
        System.out.println("SignupService() is Called");
        serviceResponse = new ServiceResponse();
    }

    @RequestMapping(value="/user",method = RequestMethod.POST)
    public ServiceResponse postSignup(@RequestParam Map<String, String> allRequestParams,
                                      @Valid AppCredential appCredential,
                                      BindingResult result){



        /*------------ Parameter Alias---------------*/
        this.serviceResponse.setParameterAlias("id","identityTypeId");

        String firstName = allRequestParams.get("firstName");
        String lastName = allRequestParams.get("lastName");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        Long identityDocToken = null;
        try{
            if(allRequestParams.get("identityDocToken")==null || allRequestParams.get("identityDocToken")==""){
                this.serviceResponse.setErrorMsg("identityDocToken", "Identity doc token required");
            }else{
                identityDocToken = Long.parseLong(allRequestParams.get("identityDocToken"));
            }
        }catch (Exception ex){
            this.serviceResponse.setErrorMsg("identityDocToken", "Identity doc token integer required");
            identityDocToken = 0L;
        }


        IdentityType identityType = new IdentityType();
        try{

            identityType.setId(Integer.parseInt(allRequestParams.get("identityTypeId")));
        }catch (Exception ex){
            this.serviceResponse.setErrorMsg("identityTypeId","Identity type id required");
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


        appCredential.setUserInf(userInf);
        appCredential.setRole(-1);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        appCredential.setEmail(email);
        appCredential.setPassword(password);

      //  identityType = identityTypeModel.getById(identityType.getId());

        appCredential.getUserInf().setIdentityType(identityType);


        /*------------------- Entity Validation ---------------------*/
        new AppCredentialValidator(appLoginCredentialModel,identityTypeModel,true).validate(appCredential,result);


        // new IdentityTypeValidator(identityTypeModel).validate(identityType, result);

        this.serviceResponse.setError(result,true,false);

        if(this.serviceResponse.hasErrors()){

            return this.serviceResponse;
        }




        /*------------------- Uploaded Document checking By Token -----*/



        TempFile tempFile = this.tempFileModel.getByToken(identityDocToken);
        if(tempFile ==null){
            this.serviceResponse.setErrorMsg("identityDocToken","Identity doc token is not valid");
            return serviceResponse;
        }

        if(!ImageHelper.isFileExist(tempFile.getPath())){
            this.serviceResponse.setErrorMsg("identityDocToken","No file found associated with the token");
            return serviceResponse;
        }

        appCredential.getUserInf().setIdentityDocUrl(tempFile.getPath());
        appCredential.setPassword(bCryptPasswordEncoder.encode(password));
        appCredential.setAccesstoken(DigestUtils.md5DigestAsHex((email + password).getBytes()));

        appLoginCredentialModel.insert(appCredential);

        /*----- Move identity doc form temp to original ---- */

        String filePath = ImageHelper.moveFile(appCredential.getId(), tempFile.getPath());



        /*----- Update Information ---- */
        appCredential.getUserInf().setIdentityDocUrl(filePath);

        appLoginCredentialModel.update(appCredential);
        this.tempFileModel.delete(tempFile);

        appCredential = appLoginCredentialModel.getById(appCredential.getId());

        this.serviceResponse.responseStat.setMsg("Signup successful");
        this.serviceResponse.setResponseData(appCredential);
        return serviceResponse;
    }

}
