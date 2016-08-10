package controller.web.app;


import com.fasterxml.jackson.databind.deser.Deserializers;
import controller.BaseHttp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signup")
@Scope("request")
public class SignUpController extends BaseHttp{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/SignUp");
        modelAndView.addObject("BaseUrl",this.getBaseURL());
        return modelAndView;
    }
}
