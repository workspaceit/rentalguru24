package controller.service.app;


import javax.inject.Inject;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.User;
import model.entity.app.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by omar on 7/21/16.
 */
@RestController
@RequestMapping("/signup")
public class SignupService{
    //ServiceResponse serviceResponse;
    @Inject
    ServiceResponse serviceResponse;
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

//    @Autowired
//    private MessageSource messageSource;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ServiceResponse test(@RequestParam Map<String, String> allRequestParams){
        serviceResponse.responseStat.msg = "TEST";
        return serviceResponse;
    }
    @RequestMapping(value = "/postsignup", method = RequestMethod.GET)
    public ServiceResponse postSignup(@RequestParam Map<String, String> allRequestParams,@Valid UserAddress userAddress,BindingResult result){
      //  serviceResponse = new ServiceResponse();
        System.out.println("ok01");
        String firstName ="TEST";// allRequestParams.get("first_name");
        String lastName ="TEST";//allRequestParams.get("last_name");
        String email = "email@email.com";//allRequestParams.get("email");
        String password = "123456";//allRequestParams.get("password");

        AppCredential appCredential = new AppCredential();
        User user = new User();
//        UserAddress userAddress = new UserAddress();
        userAddress.setAddress("sdfds");
        //userAddress.setCity("Pitsnurg");
        userAddress.setZip("1245");
        userAddress.setState("Pelsanvania");
        user.setuserAddress(userAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        appCredential.setEmail(email);
        appCredential.setPassword(password);
        appCredential.setUser(user);

       // UserAddressValidator userAddressValidator = new UserAddressValidator();
     //   new UserAddressValidator().validate(userAddress, result);


        appCredential.setPassword(bCryptPasswordEncoder.encode(password));
        appCredential.setAccesstoken(DigestUtils.md5DigestAsHex((email + password).getBytes()));
//        if(result.hasErrors()){
//            System.out.println("hasErrors");
//            for (Object object : result.getAllErrors()) {
//                if(object instanceof FieldError) {
//                    FieldError fieldError = (FieldError) object;
//
//                    /**
//                     * Use null as second parameter if you do not use i18n (internationalization)
//                     */
//
//                    String message = messageSource.getMessage(fieldError, null);
//                    System.out.println(message);
//                }
//            }
//        }
        System.out.println("result.hasErrors() "+result.hasErrors());
        //serviceResponse.responseData = userAddress;
        if(result.hasErrors()) {
            for (ObjectError object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;

                    System.out.println(fieldError.getCode());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    System.out.println(objectError.getCode());
                    objectError.getObjectName();
                }
            }
        }


     //   appLoginCredentialModel.insert(appCredential);
       // return result.getAllErrors();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(this.serviceResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serviceResponse;
    }

}
