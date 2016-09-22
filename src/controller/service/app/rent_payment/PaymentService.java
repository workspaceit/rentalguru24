package controller.service.app.rent_payment;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;
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
                                                            baseURL + "/paypal/rent-payment/payment-success/"+rentRequest.getId(),
                                                            baseURL + "/paypal/rent-payment/payment-cancel/"+rentRequest.getId());

        responseObj.put("payId",createdPayment.getId());


        Iterator<Links> links = createdPayment.getLinks().iterator();
        while (links.hasNext()) {
            Links link = links.next();
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                String approveUrl = link.getHref();
                if(PayPalPayment.mode.equals("sandbox")){
                    approveUrl =  link.getHref().replaceAll("https://www.paypal.com/", "https://www.sandbox.paypal.com/");
                }
                responseObj.put("url",approveUrl);
                System.out.println("redirectURL" + link.getHref());
            }
        }

        System.out.println(createdPayment.toJSON());
        serviceResponse.setResponseData(responseObj);

        return serviceResponse;
    }
    @RequestMapping(value = "/verify-payment/{rentRequestId}", method = RequestMethod.GET)
    public ServiceResponse verifyPayment(HttpServletRequest request,
                                         @PathVariable int rentRequestId,
                                         @RequestParam String paymentId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        AdminPaypalCredential adminPaypalCredential =  adminPaypalCredentailModel.getAdminPaypalCredentail();
        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());




        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);

//        if(rentRequest.getRequestedBy().getId() == appCredential.getId()){
//            serviceResponse.getResponseStat().setErrorMsg("This request is not belongs to you. Suck a lemon !! ");
//            return serviceResponse;
//        }

        if(adminPaypalCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("No payPal App credential found");
            return serviceResponse;
        }

        if(rentRequest==null){
            serviceResponse.getResponseStat().setErrorMsg("No rent request information found");
            return serviceResponse;
        }

        Payment payment = payPalPayment.getDetails(paymentId);

        if(payment==null){
            serviceResponse.getResponseStat().setErrorMsg("No payment information found");
            return serviceResponse;
        }


        /* **** *** ** * Check the payment is already exist in database * ** *** *** */

        if(rentPaymentModel.isPaymentAlreadyExist(paymentId, payment.getPayer().getPayerInfo().getPayerId())){
            serviceResponse.getResponseStat().setErrorMsg("Payment has been already recorded by payment id : "+paymentId);
            return serviceResponse;
        }
        if(payment.getState().equals("approved")){
            serviceResponse.getResponseStat().setErrorMsg("You payment state is '"+payment.getState()+" but not 'approve' yet");
            return serviceResponse;
        }

        /* *** ** * Execute Paypal Payment * ** *** */

        Transaction payPalTransactions = null;
        Sale payPalSale = null;
        String payPalSaleId = null;
        String payPalPayerId = null;
        Double transactionFee = 0d;
        Double totalAmount = 0d;
        String currency = "";
        if(payment.getTransactions()!=null && payment.getTransactions().size() > 0){
            payPalTransactions = payment.getTransactions().get(0);
            // Have to handle the scenario
        }
        if(payPalTransactions!=null){
            if( payPalTransactions.getRelatedResources()!=null && payPalTransactions.getRelatedResources().size() > 0){
                payPalSale = payPalTransactions.getRelatedResources().get(0).getSale();
            }
            // Have to handle the scenario
        }
        if(payPalSale!=null){
            payPalSaleId = payPalSale.getId();
            payPalSale.getCreateTime();

            try{
                transactionFee = Double.parseDouble(payPalSale.getTransactionFee().getValue());
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                transactionFee = 0d;
            }

            try{
                totalAmount = Double.parseDouble(payPalSale.getAmount().getTotal());
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                totalAmount = 0d;
            }
            currency = payPalSale.getAmount().getCurrency();
        }
        if(payment.getPayer()!=null){
            payPalPayerId = payment.getPayer().getPayerInfo().getPayerId();
        }
        if(rentRequest.getRentalProduct().getCurrentValue()<totalAmount){
            serviceResponse.getResponseStat().setErrorMsg("You have charge less then product current value. Have you been drinking !!");
            return serviceResponse;
        }
        /* Insert payment information */

        RentPayment rentPayment = new RentPayment();
        rentPayment.setRentRequest(rentRequest);
        rentPayment.setAppCredential(appCredential);
        rentPayment.setPaypalPayerId(payPalPayerId);
        rentPayment.setPaypalPayId(payment.getId());
        rentPayment.setPaypalSaleId(payPalSaleId);
        rentPayment.setTransactionFee(transactionFee);
        rentPayment.setTotalAmount(totalAmount);
        rentPayment.setCurrency(currency);
        // rentPayment.setPaypalPaymentDate(DateHelper.getCurrentUtcDateTimeStamp());
        rentPaymentModel.insert(rentPayment);

        /* Updating rent request payment completion */
        rentRequest.setIsPaymentComplete(true);
        rentRequestModel.update(rentRequest);


        serviceResponse.setResponseData(rentPayment);
        return serviceResponse;
    }
}