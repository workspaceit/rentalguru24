package controller.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signin")
public class SignInController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {


        return "public/SignIn";
    }
}
