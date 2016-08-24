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
        modelAndView.addObject("PageTitle", "Admin Users Details");
        return modelAndView;
    }
}
