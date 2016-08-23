package controller.service.app;

import controller.service.BaseService;
import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/signout")
@Scope("request")
public class SignoutService extends BaseService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @RequestMapping( method = RequestMethod.GET)
    public ServiceResponse signOut(){
        this.destroySession();

        this.serviceResponse = new ServiceResponse();

        return this.serviceResponse;
    }

}