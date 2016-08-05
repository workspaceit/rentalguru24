package controller.web.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import java.io.IOException;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "public/SignUp";
    }
}
