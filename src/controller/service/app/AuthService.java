package controller.service.app;

import controller.service.BaseService;
import model.AuthCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/app")
@Scope("request")
public class AuthService extends BaseService {
    @Autowired
    AuthCredentialModel appLoginCredentialModel;

//    @RequestMapping(value="/getbyid",method = RequestMethod.GET)
//    public AEntity getById(){
//        System.out.println("SD");
//        //    AppLoginCredentialModel apcm = new AppLoginCredentialModel();
//        return authCredentialModel.aEntity();
//    }
//    @RequestMapping(value="/auth",method = RequestMethod.POST)
//    public AppLoginCredentialEntity getAuthinticated(@RequestParam("access_token") String accessToken){
//        //  AppLoginCredentialModel authCredentialModel = new AppLoginCredentialModel();
//        return authCredentialModel.isAuthenticated(accessToken);
//        //  isAuthenticated
//    }
    @RequestMapping(value = "/getbypassword", method = RequestMethod.GET)
    public AppCredential getByPassword(){
        return (AppCredential)appLoginCredentialModel.getByPassword();
    }
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public AppCredential getById(){
        return (AppCredential)appLoginCredentialModel.dummy();
    }

}