package controller.web.app;

import controller.BaseHttp;
import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/signin")
public class SignInController{

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");


        ModelAndView modelAndView = new ModelAndView("public/SignIn");
        modelAndView.addObject("BaseUrl",baseUrl);
        return modelAndView;


    }
}
