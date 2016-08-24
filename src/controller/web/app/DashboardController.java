package controller.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 8/24/16.
 */
@Controller
@RequestMapping("/user/dashboard")
public class DashboardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "User Dashboard");
        return modelAndView;
    }
}
