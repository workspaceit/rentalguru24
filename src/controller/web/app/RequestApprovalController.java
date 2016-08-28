package controller.web.app;

import helper.ServiceResponse;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 8/27/16.
 */
@Controller
@RequestMapping("/rent")
public class RequestApprovalController {
    @Autowired
    RentRequestModel rentRequestModel;
    @RequestMapping(value = "/request/{rent_request_id}",method = RequestMethod.GET)
    public ModelAndView getRequestApproval(HttpServletRequest request, @PathVariable("rent_request_id") int rentRequestId){
        ModelAndView modelAndView = new ModelAndView("public/request_approval");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);

        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            modelAndView.addObject("rentRequest", rentRequest);
        }else{
            return new ModelAndView("redirect:/home");
        }
        modelAndView.addObject("pageTitle", "Request Approval");

        return modelAndView;
    }
}