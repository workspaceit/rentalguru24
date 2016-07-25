package controller.service.app;


import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.User;
import model.entity.app.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by omar on 7/21/16.
 */
@RestController
@RequestMapping("/signup")
public class SignupService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @RequestMapping(value = "/postsignup", method = RequestMethod.POST)
    public void postSignup(@RequestParam Map<String, String> allRequestParams){

        String firstName ="TEST";// allRequestParams.get("first_name");
        String lastName ="TEST";//allRequestParams.get("last_name");
        String email = "email@email.com";//allRequestParams.get("email");
        String password = "123456";//allRequestParams.get("password");

        AppCredential appCredential = new AppCredential();
        User user = new User();
        UserAddress userAddress = new UserAddress();
        userAddress.setAddress("sdfds");
        userAddress.setCity("Pitsnurg");
        userAddress.setZip("1245");
        userAddress.setState("Pelsanvania");
        user.setuserAddress(userAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        appCredential.setEmail(email);
        appCredential.setPassword(password);
        appCredential.setUser(user);



        appCredential.setPassword(bCryptPasswordEncoder.encode(password));
        appCredential.setAccesstoken(DigestUtils.md5DigestAsHex((email + password).getBytes()));


        appLoginCredentialModel.insert(appCredential);
    }

}
