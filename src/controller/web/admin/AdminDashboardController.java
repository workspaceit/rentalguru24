package controller.web.admin;

import model.AppLoginCredentialModel;
import model.admin.AdminPaypalCredentailModel;
import model.admin.AdminSitesFeesModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 8/24/16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminDashboardController {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    AdminPaypalCredentailModel adminPaypalCredentailModel;

    @Autowired
    AdminSitesFeesModel adminSitesFeesModel;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/create-new-admin", method = RequestMethod.GET)
    public ModelAndView createNewAdmin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/createNewAdmin");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("PageTitle", "Create New Admin");
        return modelAndView;

    }

    @RequestMapping(value = "/get-all-admin", method = RequestMethod.GET)
    public ModelAndView showAllAdminList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/adminUserDetails");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<AuthCredential> adminUsers = appLoginCredentialModel.getAllAdmin();

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUsers", adminUsers);
        modelAndView.addObject("PageTitle", "All Admin");


        return modelAndView;

    }

    @RequestMapping(value = "/get-admin-edit-page/{admin_id}", method = RequestMethod.GET)
    public ModelAndView editAdminPage(HttpServletRequest request, @PathVariable("admin_id") int appUserId) {
        ModelAndView modelAndView = new ModelAndView("admin/editAdminProfile");
        String baseUrl = (String) request.getAttribute("baseURL");
        AuthCredential admin = appLoginCredentialModel.getById(appUserId);


        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUser", admin);
        modelAndView.addObject("PageTitle", "Admin Edit");

        return modelAndView;

    }

    @RequestMapping(value = "/get-utility", method = RequestMethod.GET)
    public ModelAndView getUtilitypage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/Utility");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();
        AdminSiteFeesEntity adminSiteFeesEntity = adminSitesFeesModel.getAdminSiteFees();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("paypalCredientail", adminPaypalCredential);
        modelAndView.addObject("siteFeesCredientail", adminSiteFeesEntity);
        modelAndView.addObject("PageTitle", "Utility");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-paypal-configuration", method = RequestMethod.GET)
    public ModelAndView getPaypalEditPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/editPaypalConfiguration");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();
        modelAndView.addObject("paypalCredientail", adminPaypalCredential);
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Edit Paypal Configration");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-site-fees", method = RequestMethod.GET)
    public ModelAndView getSiteFeesPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/editSiteFeesConfiguration");


        String baseUrl = (String) request.getAttribute("baseURL");
        AdminSiteFeesEntity adminSiteFeesEntity = adminSitesFeesModel.getAdminSiteFees();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("siteFeesCredientail", adminSiteFeesEntity);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Edit Site Fee");
        return modelAndView;
    }
    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public ModelAndView getAddCategory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Add Category");
        return modelAndView;
    }
}
