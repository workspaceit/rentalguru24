package controller.web.app;

import helper.ServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        if(!IsLogin){
            ModelAndView modelAndView = new ModelAndView("public/password-reset");
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/change-password/{token}", method = RequestMethod.GET)
    public ModelAndView getChangePassword(HttpServletRequest request, @PathVariable("token") String token){
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        if(!IsLogin){
            ModelAndView modelAndView = new ModelAndView("public/change-password");
            modelAndView.addObject("token", token);
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/home");
        }
    }
}
