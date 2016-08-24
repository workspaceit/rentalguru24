package controller.web.admin;

import model.entity.app.AppCredential;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 8/24/16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminDashboardController {
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndViev = new ModelAndView("admin/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndViev.addObject("adminUser", appCredential);
        modelAndViev.addObject("BaseUrl", baseUrl);
        modelAndViev.addObject("PageTitle", "Admin Dashboard");
        return modelAndViev;
    }
}
