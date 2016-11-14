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
import java.util.HashMap;
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
        ModelAndView modelAndView = new ModelAndView("admin/app-user/app-users-list");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllAppUser();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("App User", new String(baseUrl+"/admin/user/app-user"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "App User");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
    @RequestMapping(value = "/app-user/verified", method = RequestMethod.GET)
    public ModelAndView getAllActiveAppUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/app-user/app-users-list");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllVerifiedAppUser();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("Verified User", new String(baseUrl+"/admin/user/app-user/verified"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Verified User");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/app-user/unverified", method = RequestMethod.GET)
    public ModelAndView getAllInactiveAppUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/app-user/app-users-list");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List <AuthCredential> authCredentials = appLoginCredentialModel.getAllUnVerifiedAppUser();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("Unverified User", new String(baseUrl+"/admin/user/app-user/unverified"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("allUsers", authCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Unverified User");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
}
