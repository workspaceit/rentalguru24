package controller.service.app.rent_payment;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import helper.DateHelper;
import helper.ServiceResponse;
import library.paypal.PayPalPayment;
import model.RentInfModel;
import model.RentPaymentModel;
import model.RentRequestModel;
import model.admin.AdminPaypalCredentailModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mi on 7/20/16.
 */

@RestController
@RequestMapping("/api/rent-payment")
public class PaymentService {
    @Autowired
    RentRequestModel rentRequestModel;
    @Autowired
    RentPaymentModel rentPaymentModel;
    @Autowired
    AdminPaypalCredentailModel adminPaypalCredentailModel;

    @RequestMapping(value = "/create-payment/{rentRequestId}", method = RequestMethod.GET)
    public ServiceResponse createPayment(HttpServletRequest request,@PathVariable int rentRequestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseURL  = (String) request.getAttribute("baseURL");
        Map<String,String> responseObj = new HashMap<>();
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();

        if(adminPaypalCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("No payPal App credential found");
            return serviceResponse;
        }

        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);

        if(rentRequest==null){
            serviceResponse.getResponseStat().setErrorMsg("No rent request found");
            return serviceResponse;
        }

        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());
        Payment createdPayment = payPalPayment.createPayment(rentRequest,
                                                            baseURL + "/paypal/rent-payment/payment-success",
                                                            baseURL + "/paypal/rent-payment/payment-cancel");

        responseObj.put("payId",createdPayment.getId());


        Iterator<Links> links = createdPayment.getLinks().iterator();
        while (links.hasNext()) {
            Links link = links.next();
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                responseObj.put("url", link.getHref());
                System.out.println("redirectURL" + link.getHref());
            }
        }
        System.out.println(createdPayment.toJSON());
        serviceResponse.setResponseData(responseObj);

        return serviceResponse;
    }
}