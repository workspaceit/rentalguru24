package controller.web.admin;

import helper.ServiceResponse;
import model.*;
import model.admin.AdminPaypalCredentialModel;
import model.admin.AdminSitesFeesModel;
import model.entity.BannerImage;
import model.entity.admin.*;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.Category;
import model.entity.app.product.rentable.RentInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @Autowired
    RentInfModel rentInfModel;

    @Autowired
    BannerImageModel bannerImageModel;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Dashboard", new String(baseUrl+"/admin/user/dashboard"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Dashboard");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/create-new-admin", method = RequestMethod.GET)
    public ModelAndView createNewAdmin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/createNewAdmin");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("Create New Admin ", new String(baseUrl+"/admin/user/create-new-admin"));

        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("pageHeader", "Create New Admin");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;

    }

    @RequestMapping(value = "/get-all-admin", method = RequestMethod.GET)
    public ModelAndView showAllAdminList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/adminUserDetails");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<AuthCredential> adminUsers = appLoginCredentialModel.getAllAdmin();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("Admin User", new String(baseUrl+"/admin/user/get-all-admin"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUsers", adminUsers);
        modelAndView.addObject("pageHeader", "Admin User");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;

    }

    @RequestMapping(value = "/get-admin-edit-page/{admin_id}", method = RequestMethod.GET)
    public ModelAndView editAdminPage(HttpServletRequest request, @PathVariable("admin_id") int appUserId) {
        ModelAndView modelAndView = new ModelAndView("admin/editAdminProfile");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        AuthCredential admin = appLoginCredentialModel.getById(appUserId);

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("User", new String("javascript:void(0);"));
        breadcrumb.put("Admin User", new String(baseUrl+"/admin/user/get-all-admin"));
        breadcrumb.put("Edit Admin", new String(baseUrl+"/admin/user/get-admin-edit-page/"+appUserId));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("admin", admin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Edit Admin");
        modelAndView.addObject("breadcrumb", breadcrumb);

        return modelAndView;

    }

    @RequestMapping(value = "/get-utility", method = RequestMethod.GET)
    public ModelAndView getUtilitypage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/Utility");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
        AdminSiteFeesEntity adminSiteFeesEntity = adminSitesFeesModel.getAdminSiteFees();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Utility", new String(baseUrl+"/admin/user/get-utility"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("paypalCredientail", adminPaypalCredential);
        modelAndView.addObject("siteFeesCredientail", adminSiteFeesEntity);
        modelAndView.addObject("pageHeader", "Utility");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-paypal-configuration", method = RequestMethod.GET)
    public ModelAndView getPaypalEditPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/editPaypalConfiguration");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
        modelAndView.addObject("paypalCredientail", adminPaypalCredential);
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Utility", new String(baseUrl + "/admin/user/get-utility"));
        breadcrumb.put("Update Paypal Configuration", new String(baseUrl + "/admin/user/edit-paypal-configuration"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Edit Paypal Configration");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-site-fees", method = RequestMethod.GET)
    public ModelAndView getSiteFeesPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/editSiteFeesConfiguration");
        String baseUrl = (String) request.getAttribute("baseURL");
        AdminSiteFeesEntity adminSiteFeesEntity = adminSitesFeesModel.getAdminSiteFees();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Utility", new String(baseUrl+"/admin/user/get-utility"));
        breadcrumb.put("Edit Site Fee", new String(baseUrl+"/admin/user/edit-site-fees"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("siteFeesCredientail", adminSiteFeesEntity);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Edit Site Fee");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public ModelAndView getAddCategory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/category/addCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Category", new String("javascript:void(0);"));
        breadcrumb.put("Create New Category", new String(baseUrl + "/admin/user/add-category"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Create New Category");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/add-sub-category", method = RequestMethod.GET)
    public ModelAndView getAddSubCategory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addSubCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Category", new String("javascript:void(0);"));
        breadcrumb.put("Create New Subcategory", new String(baseUrl+"/admin/user/add-sub-category"));

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Create New Subcategory");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/category-list", method = RequestMethod.GET)
    public ModelAndView getCategoryList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/categoryList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Category", new String("javascript:void(0);"));
        breadcrumb.put("Category And Subcategory List", new String(baseUrl+"/admin/user/category-list"));

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Category Tables");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
    @RequestMapping(value = "/edit-category/{category_id}", method = RequestMethod.GET)
    public ModelAndView editCategory(HttpServletRequest request, @PathVariable("category_id") int categoryId){
        ModelAndView modelAndView = new ModelAndView("admin/category/editCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Category category = categoryModel.getById(categoryId);

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Category", new String("javascript:void(0);"));
        breadcrumb.put("Category And Subcategory List", new String(baseUrl+"/admin/user/category-list"));
        breadcrumb.put("Category Edit", new String(baseUrl+"/admin/user/edit-category/"+categoryId));

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Category Edit");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-sub-category/{sub_category_id}", method = RequestMethod.GET)
    public ModelAndView editSubcategory(HttpServletRequest request,@PathVariable("sub_category_id") int subCategoryId){
        ModelAndView modelAndView = new ModelAndView("admin/category/editSubCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Category subCategory = categoryModel.getById(subCategoryId);

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Category", new String("javascript:void(0);"));
        breadcrumb.put("Category And Subcategory List", new String(baseUrl+"/admin/user/category-list"));
        breadcrumb.put("Subcategory Edit", new String(baseUrl+"/admin/user/edit-sub-category/"+subCategoryId));

        modelAndView.addObject("subCategory", subCategory);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Subcategory Edit");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/get-unread-notification-partial-render", method = RequestMethod.GET)
    public ModelAndView getNotificationPartialRender(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter) {
        ModelAndView modelAndView = new ModelAndView("admin/notificationPartialRendering");
        int limit = Integer.parseInt(allRequestParameter.get("limit"));
        int offset = Integer.parseInt(allRequestParameter.get("offset"));
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getAllNotification(limit, offset);

        modelAndView.addObject("adminGlobalNotifications", adminGlobalNotifications);
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/notification/global", method = RequestMethod.GET)
    public ModelAndView getGlobalNotificationList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/globalNotificationList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getAllNotification();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Notification", new String("javascript:void(0);"));
        breadcrumb.put("Global Notification List", new String(baseUrl+"/admin/user/notification/global"));

        modelAndView.addObject("adminGlobalNotifications", adminGlobalNotifications);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Dispute List");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/notification/global/{notification_id}", method = RequestMethod.GET)
    public ModelAndView getNotificationDetails(HttpServletRequest request, @PathVariable("notification_id") int notificationId){
        ModelAndView modelAndView = new ModelAndView("admin/notificationDetails");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        AdminGlobalNotification adminGlobalNotification = adminGlobalNotificationModel.getById(notificationId);

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Notification", new String("javascript:void(0);"));
        breadcrumb.put("Global Notification List", new String(baseUrl+"/admin/user/notification/global"));
        breadcrumb.put("Notification Details", new String(baseUrl+"/admin/user/notification/global/"+notificationId));


        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminGlobalNotification", adminGlobalNotification);
        modelAndView.addObject("pageHeader", "Notification Details");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/order-details/{notification_id}/{rentinf_id}", method = RequestMethod.GET)
    public ModelAndView getOrderDetails(HttpServletRequest request, @PathVariable("notification_id") int notificationId ,@PathVariable("rentinf_id") int rentinfId){
        ModelAndView modelAndView = new ModelAndView("admin/orderDetails");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        RentInf rentInf = rentInfModel.getById(rentinfId);

//        HashMap<String, String> breadcrumb = new HashMap<>();
//
//        breadcrumb.put("Notification", new String("javascript:void(0);"));
//        breadcrumb.put("Global Notification List", new String(baseUrl+"/admin/user/notification/global"));
//        breadcrumb.put("Notification Details", new String(baseUrl+"/admin/user/notification/global/"+notificationId));
//        breadcrumb.put("Order Details", new String(baseUrl+"/admin/user/order-details/"+notificationId+"/"+rentinfId));

        Map<String, String> breadcrumb = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

        breadcrumb.put("Notification", new String("javascript:void(0);"));
        breadcrumb.put("Global Notification List", new String(baseUrl+"/admin/user/notification/global"));
        breadcrumb.put("Notification Details", new String(baseUrl+"/admin/user/notification/global/"+notificationId));
        breadcrumb.put("Order Details", new String(baseUrl+"/admin/user/order-details/"+notificationId+"/"+rentinfId));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentInf", rentInf);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Order Details");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/add-banner-image", method = RequestMethod.GET)
    public ModelAndView getAddBannerImage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addBannerImage");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Banner Image", new String("javascript:void(0);"));
        breadcrumb.put("Add Banner Image", new String(baseUrl + "/admin/user/add-banner-image"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Add Banner Image");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/banner-image-list", method = RequestMethod.GET)
    public ModelAndView getBannerImageList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/bannerList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        List<BannerImage> bannerImageList = bannerImageModel.getAll();

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Banner Image", new String("javascript:void(0);"));
        breadcrumb.put("Banner Image List", new String(baseUrl+"/admin/user/banner-image-list"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("bannerImageList", bannerImageList);
        modelAndView.addObject("pageHeader", "Banner Image List");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ModelAndView getTransaction(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/transaction/all-list");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        HashMap<String, String> breadcrumb = new HashMap<>();


        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Banner Image List");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }


}
