package controller.web.app;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import helper.ServiceResponse;
import model.RentRequestModel;
import model.admin.AdminSitesFeesModel;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;



/**
 * Created by omar on 8/27/16.
 */
@Controller
@RequestMapping("/rent")
public class RentRequestController {
    @Autowired
    RentRequestModel rentRequestModel;
    @Autowired
    AdminSitesFeesModel adminSitesFeesModel;
    @RequestMapping(value = "/request/{rent_request_id}",method = RequestMethod.GET)
    public ModelAndView getRequestApproval(HttpServletRequest request, @PathVariable("rent_request_id") int rentRequestId){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/order_details");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        AdminSiteFeesEntity adminSitesFees = adminSitesFeesModel.getAdminSiteFees();
        if(rentRequest != null){
            if(rentRequest.getRequestedBy().getId() == appCredential.getId()
                    ||
                rentRequest.getRentalProduct().getOwner().getId() == appCredential.getId()){

                modelAndView.addObject("adminSitesFees", adminSitesFees);
                modelAndView.addObject("IsLogIn", IsLogin);
                modelAndView.addObject("IsLogIn", IsLogin);
                modelAndView.addObject("appCredential", appCredential);
                modelAndView.addObject("rentRequest", rentRequest);
            }else{
                return new ModelAndView("redirect:/home");
            }
        } else{
            return new ModelAndView("redirect:/home");
        }
        return modelAndView;
    }
}
