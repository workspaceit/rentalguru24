package controller.web.app;


import com.fasterxml.jackson.databind.deser.Deserializers;
import controller.BaseHttp;
import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signup")
public class SignUpController{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        return  new ModelAndView("public/SignUp");
    }
}
