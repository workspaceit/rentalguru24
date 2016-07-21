package controller.service.app;


import model.AppLoginCredentialModel;
import model.entity.app.AppLoginCredentialEntity;
import model.entity.app.DeviceInfoEntity;
import model.entity.app.UserInfEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

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

        String firstName = allRequestParams.get("first_name");
        String lastName =allRequestParams.get("last_name");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");

        AppLoginCredentialEntity appLoginCredentialEntity = new AppLoginCredentialEntity();
        UserInfEntity userInfEntity = new UserInfEntity();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        userInfEntity.setFirstName(firstName);
        userInfEntity.setLastName(lastName);

        appLoginCredentialEntity.setEmail(email);
        appLoginCredentialEntity.setPassword(bCryptPasswordEncoder.encode(password));
        appLoginCredentialEntity.setAccessToken(DigestUtils.md5DigestAsHex((email+password).getBytes()));
        appLoginCredentialEntity.setUserInfByUserInfId(userInfEntity);
        appLoginCredentialEntity.setDeviceInfosById(new ArrayList<DeviceInfoEntity>());
        appLoginCredentialModel.insert(appLoginCredentialEntity);
    }

}
