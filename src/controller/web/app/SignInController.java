package controller.web.app;

import controller.BaseHttp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signin")
@Scope("request")
public class SignInController extends BaseHttp{

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/SignIn");
        modelAndView.addObject("BaseUrl",this.getBaseURL());
        return modelAndView;


    }
}
