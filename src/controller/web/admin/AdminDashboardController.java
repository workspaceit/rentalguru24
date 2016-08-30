package controller.web.admin;

import model.AdminPaypalCredentailModel;
import model.AppLoginCredentialModel;
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

    @RequestMapping(value ="/create-new-admin",method = RequestMethod.GET)
    public ModelAndView createNewAdmin(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("admin/createNewAdmin");
        String baseUrl=(String)request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);

        return modelAndView;

    }

    @RequestMapping(value ="/get-all-admin",method = RequestMethod.GET)
    public ModelAndView showAllAdminList(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("admin/adminUserDetails");
        String baseUrl=(String)request.getAttribute("baseURL");
        List<AuthCredential>adminUsers=appLoginCredentialModel.getAllAdmin();

        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUsers",adminUsers);


        return modelAndView;

    }

    @RequestMapping(value ="/get-admin-edit-page/{admin_id}",method = RequestMethod.GET)
    public ModelAndView editAdminPage(HttpServletRequest request,@PathVariable("admin_id") int appUserId){
        ModelAndView modelAndView=new ModelAndView("admin/editAdminProfile");
        String baseUrl=(String)request.getAttribute("baseURL");
        AuthCredential admin=appLoginCredentialModel.getById(appUserId);


        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("adminUser",admin);


        return modelAndView;

    }
    @RequestMapping(value ="/get-utility",method = RequestMethod.GET)
    public ModelAndView getUtilitypage(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("admin/Utility");
        String baseUrl=(String)request.getAttribute("baseURL");

        modelAndView.addObject("BaseUrl", baseUrl);
        return modelAndView;
    }
}
