package controller.web.admin;

import helper.ServiceResponse;
import model.AdminGlobalNotificationModel;
import model.AdminUnreadAlertCounterModel;
import model.AppLoginCredentialModel;
import model.CategoryModel;
import model.admin.AdminPaypalCredentialModel;
import model.admin.AdminSitesFeesModel;
import model.entity.admin.*;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 8/24/16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminDashboardController {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    AdminPaypalCredentialModel adminPaypalCredentialModel;

    @Autowired
    AdminSitesFeesModel adminSitesFeesModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    AdminGlobalNotificationModel adminGlobalNotificationModel;

    @Autowired
    AdminUnreadAlertCounterModel adminUnreadAlertCounterModel;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Dashboard");
        modelAndView.addObject("pageHeader", "Dashboard");
        modelAndView.addObject("mainMenu", "Dashboard");
        modelAndView.addObject("pageUrl", "admin/user/dashboard");
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
        modelAndView.addObject("pageHeader", "Create New Admin");
        modelAndView.addObject("mainMenu", "User");
        modelAndView.addObject("subMenu", "Create New Admin");
        modelAndView.addObject("pageUrl", "admin/user/create-new-admin");
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
        modelAndView.addObject("pageHeader", "Admin User");
        modelAndView.addObject("mainMenu", "User");
        modelAndView.addObject("subMenu", "Admin User");
        modelAndView.addObject("pageUrl", "admin/user/get-all-admin");
        return modelAndView;

    }

    @RequestMapping(value = "/get-admin-edit-page/{admin_id}", method = RequestMethod.GET)
    public ModelAndView editAdminPage(HttpServletRequest request, @PathVariable("admin_id") int appUserId) {
        ModelAndView modelAndView = new ModelAndView("admin/editAdminProfile");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        AuthCredential admin = appLoginCredentialModel.getById(appUserId);

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("admin", admin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Edit");
        modelAndView.addObject("pageHeader", "Edit Admin");
        modelAndView.addObject("mainMenu", "Edit Admin");
        modelAndView.addObject("pageUrl", "admin/user/get-admin-edit-page/"+appUserId);

        return modelAndView;

    }

    @RequestMapping(value = "/get-utility", method = RequestMethod.GET)
    public ModelAndView getUtilitypage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/Utility");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
        AdminSiteFeesEntity adminSiteFeesEntity = adminSitesFeesModel.getAdminSiteFees();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("paypalCredientail", adminPaypalCredential);
        modelAndView.addObject("siteFeesCredientail", adminSiteFeesEntity);
        modelAndView.addObject("PageTitle", "Utility");
        modelAndView.addObject("pageHeader", "Utility");
        modelAndView.addObject("mainMenu", "Utility");
        modelAndView.addObject("pageUrl", "admin/user/get-utility");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-paypal-configuration", method = RequestMethod.GET)
    public ModelAndView getPaypalEditPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/editPaypalConfiguration");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
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
        modelAndView.addObject("pageHeader", "Create New Category");
        modelAndView.addObject("mainMenu", "Category");
        modelAndView.addObject("subMenu", "Add Category");
        modelAndView.addObject("pageUrl", "admin/user/add-category");
        return modelAndView;
    }

    @RequestMapping(value = "/add-sub-category", method = RequestMethod.GET)
    public ModelAndView getAddSubCategory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addSubCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Add Subcategory");
        modelAndView.addObject("pageHeader", "Create New Subcategory");
        modelAndView.addObject("mainMenu", "Category");
        modelAndView.addObject("subMenu", "Add Subcategory");
        modelAndView.addObject("pageUrl", "admin/user/add-sub-category");
        return modelAndView;
    }

    @RequestMapping(value = "/category-list", method = RequestMethod.GET)
    public ModelAndView getCategoryList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/categoryList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Category List");
        modelAndView.addObject("pageHeader", "Category Tables");
        modelAndView.addObject("mainMenu", "Category");
        modelAndView.addObject("subMenu", "Category And Subcategory List");
        modelAndView.addObject("pageUrl", "admin/user/category-list");
        return modelAndView;
    }
    @RequestMapping(value = "/edit-category/{category_id}", method = RequestMethod.GET)
    public ModelAndView editCategory(HttpServletRequest request, @PathVariable("category_id") int categoryId){
        ModelAndView modelAndView = new ModelAndView("admin/editCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Category category = categoryModel.getById(categoryId);

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Edit Category");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-sub-category/{sub_category_id}", method = RequestMethod.GET)
    public ModelAndView editSubcategory(HttpServletRequest request,@PathVariable("sub_category_id") int subCategoryId){
        ModelAndView modelAndView = new ModelAndView("admin/editSubCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Category subCategory = categoryModel.getById(subCategoryId);

        modelAndView.addObject("subCategory", subCategory);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Edit Category");
        return modelAndView;
    }

    @RequestMapping(value = "/get-unread-notification-partial-render", method = RequestMethod.GET)
    public ModelAndView getNotificationPartialRender(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter) {
        ModelAndView modelAndView = new ModelAndView("admin/notificationPartialRendering");
        int limit = Integer.parseInt(allRequestParameter.get("limit"));
        int offset = Integer.parseInt(allRequestParameter.get("offset"));
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getUnreadNotification(limit, offset);

        modelAndView.addObject("adminGlobalNotifications", adminGlobalNotifications);
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/test/test", method = RequestMethod.GET)
    public ModelAndView getGlobalNotificationList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/globalNotificationList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getAllNotification();

        modelAndView.addObject("adminGlobalNotifications", adminGlobalNotifications);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Global Notification List");
        modelAndView.addObject("pageHeader", "Global Notification List Tables");
        modelAndView.addObject("mainMenu", "Notification");
        modelAndView.addObject("subMenu", "Global Notification List");
        modelAndView.addObject("pageUrl", "admin/user/test/test");
        return modelAndView;
    }

    @RequestMapping(value = "/notification-details/{notification_id}", method = RequestMethod.GET)
    public ModelAndView getNotificationDetails(HttpServletRequest request, @PathVariable("notification_id") int notificationId){
        ModelAndView modelAndView = new ModelAndView("admin/notificationDetails");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        AdminGlobalNotification adminGlobalNotification = adminGlobalNotificationModel.getById(notificationId);

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminGlobalNotification", adminGlobalNotification);
        modelAndView.addObject("PageTitle", "Notification Details");
        modelAndView.addObject("pageHeader", "Notification Details");
        modelAndView.addObject("mainMenu", "Notification");
        modelAndView.addObject("subMenu", "Notification Details");
        modelAndView.addObject("pageUrl", "admin/user/notification-details");
        return modelAndView;
    }
}
