package controller.web.admin;

import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 8/23/16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUsersController {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;
    @RequestMapping(value = "/app-user", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/appUsersDetails");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllAppUser();

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "App User");
        modelAndView.addObject("mainMenu", "User");
        modelAndView.addObject("subMenu", "App User");
        modelAndView.addObject("pageUrl", "app-user");
        modelAndView.addObject("PageTitle", "Admin Users Details");
        return modelAndView;
    }
    @RequestMapping(value = "/app-user/verified", method = RequestMethod.GET)
    public ModelAndView getAllActiveAppUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/appUsersDetails");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllVerifiedAppUser();

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Active Users Details");
        modelAndView.addObject("pageHeader", "Verified User");
        modelAndView.addObject("mainMenu", "User");
        modelAndView.addObject("subMenu", "Verified User");
        modelAndView.addObject("pageUrl", "app-user/verified");
        return modelAndView;
    }

    @RequestMapping(value = "/app-user/unverified", method = RequestMethod.GET)
    public ModelAndView getAllInactiveAppUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/appUsersDetails");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllUnVerifiedAppUser();

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Active Users Details");
        modelAndView.addObject("pageHeader", "Unverified User");
        modelAndView.addObject("mainMenu", "User");
        modelAndView.addObject("subMenu", "Unverified User");
        modelAndView.addObject("pageUrl", "app-user/unverified");
        return modelAndView;
    }
}
