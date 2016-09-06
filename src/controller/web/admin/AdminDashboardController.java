package controller.web.admin;

import model.AppLoginCredentialModel;
import model.CategoryModel;
import model.admin.AdminPaypalCredentailModel;
import model.admin.AdminSitesFeesModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpRetryException;
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

    @Autowired
    CategoryModel categoryModel;

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

    @RequestMapping(value = "/add-sub-category", method = RequestMethod.GET)
    public ModelAndView getAddSubCategory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addSubCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Add Subcategory");
        return modelAndView;
    }

    @RequestMapping(value = "/category-list", method = RequestMethod.GET)
    public ModelAndView getCategoryList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/categoryList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = categoryModel.getAll();

        modelAndView.addObject("category", category);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Category List");
        return modelAndView;
    }
    @RequestMapping(value = "/edit-category/{category_id}", method = RequestMethod.GET)
    public ModelAndView editCategory(HttpServletRequest request, @PathVariable("category_id") int categoryId){
        ModelAndView modelAndView = new ModelAndView("admin/editCategory");
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
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
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Category subCategory = categoryModel.getById(subCategoryId);

        modelAndView.addObject("subCategory", subCategory);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Edit Category");
        return modelAndView;
    }
}
