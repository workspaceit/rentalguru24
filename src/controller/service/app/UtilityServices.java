package controller.service.app;

import model.IdentityTypeModel;
import model.entity.app.IdentityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by omar on 8/2/16.
 */
@RestController
@RequestMapping("/api/utility")
public class UtilityServices {
    @Autowired
    IdentityTypeModel identityTypeModel;
    @RequestMapping(value = "/get-identity", method = RequestMethod.GET)
    public List<IdentityType> getAllIdentityType(){
        return identityTypeModel.getAll();
    }
}
