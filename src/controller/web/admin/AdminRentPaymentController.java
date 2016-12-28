package controller.web.admin;

import helper.ServiceResponse;
import model.*;
import model.admin.AdminPaypalCredentialModel;
import model.admin.AdminSitesFeesModel;
import model.entity.BannerImage;
import model.entity.admin.AdminGlobalNotification;
import model.entity.admin.AdminPaypalCredential;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.Category;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import model.nonentity.rent_payment.RentPaymentSummary;
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
@RequestMapping("/admin/rent-payment")
public class AdminRentPaymentController {

    @Autowired
    RentPaymentModel rentPaymentModel;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getTransaction(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/rent-payment/all");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        HashMap<String, String> breadcrumb = new HashMap<>();


        List<RentPayment> rentPaymentList  = rentPaymentModel.getAll();
        RentPaymentSummary rentPaymentSummary = rentPaymentModel.getSummary();
        modelAndView.addObject("rentPaymentSummary", rentPaymentSummary);
        modelAndView.addObject("rentPaymentList", rentPaymentList);
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Banner Image List");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }


}
