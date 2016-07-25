package controller.service.app;

import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/app")
public class AuthService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

//    @RequestMapping(value="/getbyid",method = RequestMethod.GET)
//    public AEntity getById(){
//        System.out.println("SD");
//        //    AppLoginCredentialModel apcm = new AppLoginCredentialModel();
//        return appLoginCredentialModel.aEntity();
//    }
//    @RequestMapping(value="/auth",method = RequestMethod.POST)
//    public AppLoginCredentialEntity getAuthinticated(@RequestParam("access_token") String accessToken){
//        //  AppLoginCredentialModel appLoginCredentialModel = new AppLoginCredentialModel();
//        return appLoginCredentialModel.isAuthenticated(accessToken);
//        //  isAuthenticated
//    }
    @RequestMapping(value = "/getbypassword", method = RequestMethod.GET)
    public AppCredential getByPassword(){
        return appLoginCredentialModel.getByPassword();
    }

}