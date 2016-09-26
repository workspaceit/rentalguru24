package controller.web.app.rent_payment;

import com.paypal.api.payments.*;
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
            modelAndView.addObject("payPalStatusMsg","No pay id found");
            return modelAndView;
        }
        if (token==null) {
        }
        if (payerId==null) {
        }


        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);

        modelAndView.addObject("rentRequest",rentRequest);
        if(rentRequest==null){
            modelAndView.addObject("payPalStatusMsg","Invalid rent request");
            return modelAndView;
        }

        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            modelAndView.addObject("payPalStatusMsg","This rent request is not belongs to you");
            return modelAndView;
        }
        /* **** *** ** * Check the payment is already Execute * ** *** *** */
        if(rentPaymentModel.isPaymentAlreadyExist(paymentId, payerId)){
            modelAndView.addObject("payPalStatusMsg","Payment already recorded");
            return modelAndView;
        }


        /* *** ** * Execute Paypal Payment * ** *** */
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();
        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());
        Payment executedPayment  = payPalPayment.executePayments(paymentId, payerId);

        if(executedPayment==null){
            modelAndView.addObject("payPalStatusMsg","Invalid payment id");
            return modelAndView;
        }
        if(!executedPayment.getState().equals("approved")){
            modelAndView.addObject("payPalStatusMsg", "Your Payment is not 'approved' yet, current state is '" + executedPayment.getState() + "'");
            return modelAndView;
        }

        Transaction payPalTransaction = null;
        Authorization authorization = null;
        Sale payPalSale = null;
        String payPalSaleId = null;
        String payPalPayerId = null;
        Double transactionFee = 0d;
        Double totalAmount = 0d;
        String currency = "";
        String authorizationId = null;
        if(executedPayment.getTransactions()!=null && executedPayment.getTransactions().size() > 0){
            payPalTransaction = executedPayment.getTransactions().get(0);
            // Have to handle the scenario
        }


        if(payPalTransaction!=null){
            if( payPalTransaction.getRelatedResources()!=null && payPalTransaction.getRelatedResources().size() > 0){
                payPalSale = payPalTransaction.getRelatedResources().get(0).getSale();
            }
            // Have to handle the scenario
        }
        if(payPalTransaction.getRelatedResources() !=null && payPalTransaction.getRelatedResources().size()>0){
            authorization = payPalTransaction.getRelatedResources().get(0).getAuthorization();
        }
        /* If Intent is 'authorize' */
        if(authorization!=null){
            authorizationId = authorization.getId();
            try{
                transactionFee = Double.parseDouble(authorization.getAmount().getTotal());
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                transactionFee = 0d;
            }

            currency = authorization.getAmount().getCurrency();
        }
          /* If Intent is 'sale' */
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
        rentPayment.setAuthorizationId(authorizationId);
        rentPayment.setRentFee(rentRequest.getRentFee());
       // rentPayment.setPaypalPaymentDate(DateHelper.getCurrentUtcDateTimeStamp());
        rentPaymentModel.insert(rentPayment);

        /* Updating rent request payment completion */
        rentRequest.setIsPaymentComplete(true);
        rentRequestModel.update(rentRequest);

        modelAndView.addObject("rentRequest", rentRequest);
        modelAndView.addObject("payPalStatusMsg", "Payment successfully received");
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
        modelAndView.addObject("rentRequest",rentRequest);
        if(rentRequest==null){
            modelAndView.addObject("payPalStatusMsg","Payment already canceled");
            return modelAndView;
        }
        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            modelAndView.addObject("payPalStatusMsg","This rent request is not belongs to you");
            return modelAndView;
        }
        if(rentRequest.getIsPaymentComplete()){
            modelAndView.addObject("payPalStatusMsg", "Payment was done before, can't cancel now ");
            return modelAndView;
        }
        rentRequestModel.delete(rentRequest);

        modelAndView.addObject("payPalStatusMsg", "Payment canceled");
        return modelAndView;
    }
}
