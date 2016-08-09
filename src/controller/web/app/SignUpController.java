package controller.web.app;


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
public class SignUpController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/SignUp");
        return modelAndView;
    }
}
