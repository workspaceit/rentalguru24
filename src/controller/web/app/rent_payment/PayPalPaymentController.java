package controller.web.app.rent_payment;

import com.paypal.api.payments.Payer;
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
import model.entity.app.Category;
import model.entity.app.RentRequest;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 9/21/16.
 */
@Controller
@RequestMapping("/paypal/rent-payment")
public class PayPalPaymentController {
    @Autowired
    AdminPaypalCredentailModel adminPaypalCredentailModel;
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    RentPaymentModel rentPaymentModel;
    @Autowired
    RentRequestModel rentRequestModel;

    @RequestMapping(value = "/payment-success/{rentRequestId}", method = RequestMethod.GET)
    public ModelAndView successPayment(HttpServletRequest request,
                                @PathVariable int rentRequestId,
                                @RequestParam Map<String,String> allParam){
        ModelAndView modelAndView = new ModelAndView("payment/payment_success");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        modelAndView.addObject("IsLogIn", IsLogin);

        String paymentId = allParam.get("paymentId");
        String token = allParam.get("token");
        String payerId = allParam.get("PayerID");
        if (paymentId==null) {
        }
        if (token==null) {
        }
        if (payerId==null) {
        }


        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);



        if(rentRequest==null){
            modelAndView.addObject("statusMsg","Invalid rent request");
            return modelAndView;
        }
        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            modelAndView.addObject("statusMsg","This rent request is not belongs to you");
            return modelAndView;
        }
        /* **** *** ** * Check the payment is already Execute * ** *** *** */
        if(rentPaymentModel.isPaymentAlreadyExist(paymentId, payerId)){
            modelAndView.addObject("statusMsg","Payment already recorded");
            return modelAndView;
        }


        /* *** ** * Execute Paypal Payment * ** *** */
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();
        Payment executedPayment  = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret())
                .executePayments(paymentId, payerId);

        if(executedPayment==null){
            modelAndView.addObject("statusMsg","Invalid payment id");
            return modelAndView;
        }

        Transaction payPalTransactions = null;
        Sale payPalSale = null;
        String payPalSaleId = null;
        String payPalPayerId = null;
        Double transactionFee = 0d;
        Double totalAmount = 0d;
        String currency = "";
        if(executedPayment.getTransactions()!=null && executedPayment.getTransactions().size() > 0){
            payPalTransactions = executedPayment.getTransactions().get(0);
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
        if(executedPayment.getPayer()!=null){
            payPalPayerId = executedPayment.getPayer().getPayerInfo().getPayerId();
        }

        /* Insert payment information */

        RentPayment rentPayment = new RentPayment();
        rentPayment.setRentRequest(rentRequest);
        rentPayment.setAppCredential(appCredential);
        rentPayment.setPaypalPayerId(payPalPayerId);
        rentPayment.setPaypalPayId(executedPayment.getId());
        rentPayment.setPaypalSaleId(payPalSaleId);
        rentPayment.setTransactionFee(transactionFee);
        rentPayment.setTotalAmount(totalAmount);
        rentPayment.setCurrency(currency);
       // rentPayment.setPaypalPaymentDate(DateHelper.getCurrentUtcDateTimeStamp());
        rentPaymentModel.insert(rentPayment);

        /* Updating rent request payment completion */
        rentRequest.setIsPaymentComplete(true);
        rentRequestModel.update(rentRequest);


        modelAndView.addObject("statusMsg", "Payment successfully received");

        return modelAndView;
    }
    @RequestMapping(value = "/payment-cancel/{rentRequestId}", method = RequestMethod.GET)
    public ModelAndView paymentCancel(HttpServletRequest request,
                                @PathVariable int rentRequestId,
                                @RequestParam Map<String,String> allParam){
        ModelAndView modelAndView = new ModelAndView("payment/payment_cancel");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        modelAndView.addObject("IsLogIn", IsLogin);
        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);
        if(rentRequest==null){
            modelAndView.addObject("statusMsg","Payment already canceled");
            return modelAndView;
        }
        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            modelAndView.addObject("statusMsg","This rent request is not belongs to you");
            return modelAndView;
        }
        if(rentRequest.getIsPaymentComplete()){
            modelAndView.addObject("statusMsg", "Payment was done before, can't cancel now ");
            return modelAndView;
        }
        rentRequestModel.delete(rentRequest);
        modelAndView.addObject("statusMsg", "Payment canceled");
        return modelAndView;
    }
}
