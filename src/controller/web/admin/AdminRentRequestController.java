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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String tableTitle = "";
        switch (type){
            case "all":
                rentRequests = rentRequestModel.getAll();
                tableTitle = "All Rent Request";
                break;
           case "pending":
                rentRequests = rentRequestModel.getAllPending();
                tableTitle = "Pending Rent Request";
                break;
            case  "on-progress":
                rentRequests = rentRequestModel.getAllInProgress();
                tableTitle = "Rent in Progress";
                break;
            case  "complete":
                rentRequests = rentRequestModel.getAllCompelte();
                tableTitle = "Completed Rent Request";
                break;
        }

        Double totalPrice = 0.0;
        Integer totalRent = rentRequests.size();
        for(int i = 0; i<rentRequests.size(); i++){
            totalPrice = totalPrice + rentRequests.get(i).getRentFee();
        }


        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Rent request", new String("javascript:void(0);"));
        breadcrumb.put(tableTitle, new String(baseUrl+"/admin/rent-request/"+tableTitle));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentRequests", rentRequests);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", tableTitle);
        modelAndView.addObject("breadcrumb", breadcrumb);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("totalRent", totalRent);
        modelAndView.addObject("type", type);
        return modelAndView;
    }
    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ModelAndView details(HttpServletRequest request,@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("admin/rent-request/rent-request-details");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(id);

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Rent request", new String("javascript:void(0);"));
        breadcrumb.put(rentRequest.getRentalProduct().getName(), new String(baseUrl+"/admin/rent-request/details/"+id));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentRequest", rentRequest);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", rentRequest.getRentalProduct().getName());
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/search-between-dates", method = RequestMethod.GET)
    public ModelAndView serchBetweenDates(HttpServletRequest request, @RequestParam Map<String, String> allRequestParam){
        ModelAndView modelAndView = new ModelAndView("admin/rent-request/partial-render-list");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        String stDate = allRequestParam.get("stDate");
        String edDate = allRequestParam.get("edDate");
        String type = allRequestParam.get("type");


        List<RentRequest> rentRequests = new ArrayList<>();
        String tableTitle = "";
        switch (type){
            case "all":
                rentRequests = rentRequestModel.searchRentRequestAllByBetweenDates(stDate, edDate);
                tableTitle = "All Rent Request";
                break;
            case "pending":
                rentRequests = rentRequestModel.searchRentRequestPendingByBetweenDates(stDate, edDate);
                tableTitle = "Pending Rent Request";
                break;
            case  "on-progress":
                rentRequests = rentRequestModel.searchRentRequestOnProgressByBetweenDates(stDate, edDate);
                tableTitle = "Rent in Progress";
                break;
            case  "complete":
                rentRequests = rentRequestModel.searchRentRequestCompleteByBetweenDates(stDate, edDate);
                tableTitle = "Completed Rent Request";
                break;
        }

        Double totalPrice = 0.0;
        Integer totalRent = rentRequests.size();
        for(int i = 0; i<rentRequests.size(); i++){
            totalPrice = totalPrice + rentRequests.get(i).getRentFee();
        }

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("Rent request", new String("javascript:void(0);"));
        breadcrumb.put(tableTitle, new String(baseUrl+"/admin/rent-request/"+tableTitle));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentRequests", rentRequests);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", tableTitle);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("totalRent", totalRent);
        modelAndView.addObject("breadcrumb", breadcrumb);
        modelAndView.addObject("type", type);

        return modelAndView;

    }

}
