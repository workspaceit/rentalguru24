package controller.service.app;


import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.ImageHelper;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.User;
import model.entity.app.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import validator.UserValidator;

import java.io.IOException;
import java.util.*;

/**
 * Created by omar on 7/21/16.
 */
@RestController

@RequestMapping("/signup")
@Scope("request")
public class SignupService{
    //ServiceResponse serviceResponse;

    private ServiceResponse serviceResponse;
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    public SignupService() {
        System.out.println("SignupService() is Called");
        serviceResponse = new ServiceResponse();
    }


    @RequestMapping(value = "/upload/document-identity", method = RequestMethod.GET)
    public ServiceResponse uploadDocumentIdentity(@RequestParam("documentIdentity") MultipartFile file){


        try {
            ImageHelper.saveAsPdf(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setErrorMsg("documentIdentity","No file attached");
        }
        return serviceResponse;
    }
    @RequestMapping(value = "/postsignup",  headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public ServiceResponse postSignup(@RequestParam Map<String, String> allRequestParams,

                                      @RequestParam("file") MultipartFile file,
                                      @Valid UserAddress userAddress,
                                      @Valid User user,
                                      BindingResult result){
      //  serviceResponse = new ServiceResponse();
        System.out.println(file.getName());
        try {
            ImageHelper.saveAsPdf(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String firstName = allRequestParams.get("firstName");
        String lastName = allRequestParams.get("lastName");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");

        String city = allRequestParams.get("city");
        String zip = allRequestParams.get("zip");
        String state = allRequestParams.get("state");
        String address = allRequestParams.get("address");


        userAddress.setAddress(address);
        userAddress.setZip(zip);
        userAddress.setState(state);
        userAddress.setCity(city);


        user.setUserAddress(userAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        AppCredential appCredential = new AppCredential();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        appCredential.setEmail(email);
        appCredential.setPassword(password);
        appCredential.setUser(user);

       // new UserAddressValidator().validate(userAddress, result);
        new UserValidator().validate(user,result);

        appCredential.setPassword(bCryptPasswordEncoder.encode(password));
        appCredential.setAccesstoken(DigestUtils.md5DigestAsHex((email + password).getBytes()));

        if(result.hasErrors()){
            this.serviceResponse.setError(result,true,false);
            return this.serviceResponse;
        }



     //   appLoginCredentialModel.insert(appCredential);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(this.serviceResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serviceResponse;
    }


}
