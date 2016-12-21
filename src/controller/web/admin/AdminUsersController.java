package controller.web.admin;

import model.AppLoginCredentialModel;
import model.ProductModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    ProductModel productModel;
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
    @RequestMapping(value = "/app-user/edit/{app_user_id}", method = RequestMethod.GET)
    public ModelAndView getAppUserProfileEdit(HttpServletRequest request, @PathVariable("app_user_id") int appUserId){
        ModelAndView modelAndView = new ModelAndView("admin/app-user/editAppUserProfile");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        AuthCredential authCredential = appLoginCredentialModel.getById(appUserId);
        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("App User", new String(baseUrl+"/admin/user/app-user"));
        breadcrumb.put("Edit App User Profile", new String(baseUrl+"/admin/user/app-user/edit/"+appUserId));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("appUser", authCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Edit App User Profile");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
    @RequestMapping(value = "/app-user/details/{user_id}", method = RequestMethod.GET)
    public ModelAndView getAppUserProfileDetails(HttpServletRequest request, @PathVariable("user_id") int userId){
        ModelAndView modelAndView = new ModelAndView("admin/app-user/appUserDetails");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        AuthCredential authCredential = appLoginCredentialModel.getById(userId);
        List<RentalProduct> ownerRentalProductList = productModel.getMyRentalProductList(userId);
        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("App User", new String(baseUrl+"/admin/user/app-user"));
        breadcrumb.put("App User Details", new String(baseUrl+"/admin/user/app-user/details/"+userId));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("user", authCredential);
        modelAndView.addObject("ownerRentalProductList", ownerRentalProductList);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "App User Profile Details");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
}
