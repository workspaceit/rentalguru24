package controller.web.app;

import helper.ServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 9/3/16.
 */
@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ModelAndView getPasswordReset(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        if(IsLogin != true){
            ModelAndView modelAndView = new ModelAndView("public/password-reset");
            String baseUrl = (String) request.getAttribute("baseURL");
            modelAndView.addObject("BaseUrl",baseUrl);
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/home");
        }
    }
}
