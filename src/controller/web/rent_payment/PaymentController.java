package controller.web.rent_payment;

import helper.DateHelper;
import helper.ServiceResponse;
import model.RentInfModel;
import model.RentPaymentModel;
import model.entity.app.AppCredential;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 7/20/16.
 */

@Controller
@RequestMapping("/paypal/rent-payment")
public class PaymentController {
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    RentPaymentModel rentPaymentModel;


    @RequestMapping(value = "/payment-success", method = RequestMethod.GET)
    public ServiceResponse paymentSuccess(HttpServletRequest request,String paymentId,String PayerID){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentInf rentinf = rentInfModel.getById(12);
        if(rentinf==null){
            serviceResponse.getResponseStat().setErrorMsg("No rent information found");
            return serviceResponse;
        }
        if(appCredential==null){
            // What if session expired while checkout through paypal
        }

        RentPayment rentPayment = new RentPayment();
        rentPayment.setRentInf(rentinf);
        rentPayment.setAppCredential(appCredential);
        rentPayment.setPaypalPayerId("asd");
        rentPayment.setPaypalPayId("sd");
        rentPayment.setPaypalSaleId("asdf");
        rentPayment.setPaypalPaymentDate(DateHelper.getCurrentUtcDateTimeStamp());


        rentPaymentModel.insert(rentPayment);

        return serviceResponse;
    }
    @RequestMapping(value = "/payment-cancel", method = RequestMethod.GET)
    public ServiceResponse paymentCancel(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        return serviceResponse;
    }
}