package controller.web.admin;

import model.AppLoginCredentialModel;
import model.entity.app.AppCredential;
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
@RequestMapping("/admin/user/app-user")
public class AdminUsersController {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/home");
        String baseUrl = (String) request.getAttribute("baseURL");
        List <AppCredential> appCredentials = appLoginCredentialModel.getAllAppUser();
        modelAndView.addObject("allUsers", appCredentials);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin Home");
        return modelAndView;
    }
}
