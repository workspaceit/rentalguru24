package controller.web.admin;

import model.AppLoginCredentialModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar on 8/23/16.
 */
@Controller
@RequestMapping("/admin/rent-request")
public class AdminRentRequestController {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;
    @Autowired
    RentRequestModel rentRequestModel;

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,@PathVariable String type){
        type = type.trim();
        ModelAndView modelAndView = new ModelAndView("admin/rent-request/all-list");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        List<RentRequest> rentRequests = new ArrayList<>();
        switch (type){
            case "all":
                rentRequests = rentRequestModel.getAll();
                break;
           case "pending":
                rentRequests = rentRequestModel.getAllPending();
                break;
            case  "on-progress":
                rentRequests = rentRequestModel.getAllInProgress();
                break;
            case  "complete":
                rentRequests = rentRequestModel.getAllCompelte();
                break;
        }


        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentRequests", rentRequests);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "App User");
        modelAndView.addObject("mainMenu", "Rent request");
//        modelAndView.addObject("subMenu", "App User");
//        modelAndView.addObject("pageUrl", "admin/user/app-user");
        modelAndView.addObject("PageTitle", "Admin Users Details");
        return modelAndView;
    }
    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ModelAndView details(HttpServletRequest request,@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView("admin/rent-request/rent-request-details");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(id);



        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentRequest", rentRequest);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "App User");
        modelAndView.addObject("mainMenu", "Rent request");
        modelAndView.addObject("subMenu", "Details");
        modelAndView.addObject("pageUrl", "admin/rent-request/all");
        modelAndView.addObject("PageTitle", "Admin Users Details");
        return modelAndView;
    }

}
