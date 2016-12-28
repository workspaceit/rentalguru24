package controller.service.app.rent_payment;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;
import helper.DateHelper;
import helper.ServiceResponse;
import library.paypal.PayPalPayment;
import model.RentPaymentModel;
import model.RentRequestModel;
import model.UserPaypalCredentialModel;
import model.admin.AdminPaypalCredentialModel;
import model.admin.AdminSitesFeesModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.UserPaypalCredential;
import model.entity.app.payments.RentPayment;
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
@RequestMapping("/api/auth/rent-payment")
public class PaymentService {
    @Autowired
    RentRequestModel rentRequestModel;
    @Autowired
    RentPaymentModel rentPaymentModel;
    @Autowired
    AdminPaypalCredentialModel adminPaypalCredentialModel;
    @Autowired
    UserPaypalCredentialModel userPaypalCredentialModel;

    @Autowired
    AdminSitesFeesModel adminSitesFeesModel;

    @RequestMapping(value = "/create-payment/{rentRequestId}", method = RequestMethod.GET)
    public ServiceResponse createPayment(HttpServletRequest request,@PathVariable int rentRequestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseURL  = (String) request.getAttribute("baseURL");

        UserPaypalCredential userPaypalCredential = userPaypalCredentialModel.getByAppCredentialId(appCredential.getId());

        if(userPaypalCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("You have not add paypal account yet.");
            return serviceResponse;
        }

        Map<String,String> responseObj = new HashMap<>();
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();

        if(adminPaypalCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("No payPal App credential found");
            return serviceResponse;
        }

        RentRequest rentRequest = rentRequestModel.getValidRentRequestById(rentRequestId);

        if(rentRequest==null){
            serviceResponse.getResponseStat().setErrorMsg("No valid rent request found");
            return serviceResponse;
        }

        AdminSiteFeesEntity adminSitesFees = adminSitesFeesModel.getAdminSiteFees();

        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());
        Payment createdPayment = payPalPayment.createPayment(rentRequest,adminSitesFees,
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
    @RequestMapping(value = "/verify-payment/{rentRequestId}", method = RequestMethod.POST)
    public ServiceResponse verifyPayment(HttpServletRequest request,
                                         @PathVariable int rentRequestId,
                                         @RequestParam String paymentId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        AdminPaypalCredential adminPaypalCredential =  adminPaypalCredentialModel.getAdminPaypalCredentail();
        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());




        RentRequest rentRequest = rentRequestModel.getById(rentRequestId);

        if(rentRequest==null){
            serviceResponse.setRequestError("rentRequest", "No rent request found");
            return serviceResponse;
        }
        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentRequestId", "This request is not belongs to you. Suck a lemon !! ");
            return serviceResponse;
        }

        if(adminPaypalCredential==null){
            serviceResponse.getResponseStat().setErrorMsg("No payPal App credential found");
            return serviceResponse;
        }

        Payment payment = payPalPayment.getDetails(paymentId);

        if(payment==null){
            serviceResponse.setRequestError("paymentId", "No payment information found");
            return serviceResponse;
        }
        if(!payment.getState().equals("approved")){
            serviceResponse.setRequestError("paymentId", "Your Payment is not 'approved' yet , current state is '" + payment.getState() + "'");
            return serviceResponse;
        }
        System.out.println(payment.toJSON());

        /* **** *** ** * Check the payment is already exist in database * ** *** *** */

        if(rentPaymentModel.isPaymentAlreadyExist(paymentId, payment.getPayer().getPayerInfo().getPayerId())){
            serviceResponse.setRequestError("paymentId", "Payment has been already recorded by payment id : "+paymentId);
            return serviceResponse;
        }




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
        AdminSiteFeesEntity adminSitesFees = adminSitesFeesModel.getAdminSiteFees();

        /* Insert payment information */
        double siteFees = 0;
        if(adminSitesFees.isFixed()){
            siteFees = adminSitesFees.getFixedValue();
        }else if(adminSitesFees.isPercentage()){
            siteFees = (adminSitesFees.getPercentageValue()*rentRequest.getRentFee()) / 100;
        }
        RentPayment rentPayment = new RentPayment();
        rentPayment.setRentRequest(rentRequest);
        rentPayment.setAppCredential(appCredential);
        rentPayment.setPaypalPayerId(payPalPayerId);
        rentPayment.setPaypalPayId(payment.getId());
        rentPayment.setPaypalSaleId(payPalSaleId);
        rentPayment.setTransactionFee(transactionFee);
        rentPayment.setTotalAmount(totalAmount);
        rentPayment.setCurrency(currency);
        rentPayment.setSiteFee(siteFees);
        rentPayment.setCreatedDate(DateHelper.getCurrentUtcDateTimeStamp());
        // rentPayment.setPaypalPaymentDate(DateHelper.getCurrentUtcDateTimeStamp());
        rentPaymentModel.insert(rentPayment);

        /* Updating rent request payment completion */
        rentRequest.setIsPaymentComplete(true);
        rentRequestModel.update(rentRequest);


        serviceResponse.setResponseData(rentPayment);
        return serviceResponse;
    }
//    private void test(){
//        {
//
//            ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
//            AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
//            String baseURL  = (String) request.getAttribute("baseURL");
//            Map<String,String> extraObj = new HashMap<>();
//
//            if(!appLoginCredentialModel.isVerified(appCredential.getId())){
//                serviceResponse.getResponseStat().setErrorMsg("You account is not verified");
//                SessionManagement.destroySession(request);
//                return serviceResponse;
//            }
//            RentalProduct rentalProduct = productModel.getEntityById(productId);
//
//
//            if(rentalProduct == null){
//                serviceResponse.setRequestError("productId","Product does not exist by this id");
//                return serviceResponse;
//            }
//
//            if(rentalProduct.getOwner().getId() == appCredential.getId()){
//                serviceResponse.setRequestError("productId","You can not rent your own product");
//                return serviceResponse;
//            }
//
//
//            if(startDate==null || startDate.isEmpty()){
//                serviceResponse.setRequestError("startDate","Start date is required");
//            }
//            if(endsDate==null || endsDate.isEmpty()){
//                serviceResponse.setRequestError("endsDate","End date is required");
//            }
//
//            if(serviceResponse.hasErrors()){
//                return serviceResponse;
//            }
//
//
//            if(!DateHelper.isDateValid(startDate, "dd-MM-yyyy")){
//                serviceResponse.setRequestError("startDate","Start date format miss matched");
//            }
//            if(!DateHelper.isDateValid(endsDate, "dd-MM-yyyy")){
//                serviceResponse.setRequestError("endsDate","Ends date format miss matched");
//            }
//
//            if(serviceResponse.hasErrors()){
//                return serviceResponse;
//            }
//
//
//            Timestamp startTimeStamp = DateHelper.getStringToTimeStamp(startDate, "dd-MM-yyyy");
//            Timestamp endTimeStamp = DateHelper.getStringToTimeStamp(endsDate, "dd-MM-yyyy");
//
//
//            if(rentInfModel.isProductInRent(productId, startTimeStamp, endTimeStamp)){
//                serviceResponse.setRequestError("productId","Product is already in rent on given date");
//                return serviceResponse;
//            }
//
//
//
//            if(startTimeStamp.before(rentalProduct.getAvailableFrom()) || startTimeStamp.after(rentalProduct.getAvailableTill())){
//                serviceResponse.setRequestError("startDate","Product is not available for rent on given date");
//            }
//
//            if(endTimeStamp.before(rentalProduct.getAvailableFrom()) || endTimeStamp.after(rentalProduct.getAvailableTill())){
//                serviceResponse.setRequestError("endsDate","Product is not available for rent on given date");
//            }
//
//
//
//            if(serviceResponse.hasErrors()){
//                return serviceResponse;
//            }
//
//
//
//            if(rentRequestModel.isAlreadyRequested(appCredential.getId(),productId,startTimeStamp,endTimeStamp)){
//                serviceResponse.getResponseStat().setErrorMsg("You have already requested for this product in between those date");
//                return serviceResponse;
//            }
//
//
//            RentRequest rentRequest = new RentRequest();
//
//            rentRequest.setIsExpired(false);
//            rentRequest.setAdvanceAmount(rentalProduct.getCurrentValue());
//            rentRequest.setIsExtension(false);
//            rentRequest.setRequestCancel(false);
//            rentRequest.setDisapprove(false);
//            rentRequest.setApprove(false);
//            rentRequest.setRequestedBy(appCredential);
//            rentRequest.setRentalProduct(rentalProduct);
//            rentRequest.setStartDate(new Date(startTimeStamp.getTime()));
//            rentRequest.setEndDate(new Date(endTimeStamp.getTime()));
//            rentRequest.setRemark(remark);
//            rentRequest.setIsPaymentComplete(false);
//            rentRequest.setCreatedDate(DateHelper.getCurrentUtcDateTimeStamp());
//
//            Double rentFee = RentFeesHelper.getRentFee(rentalProduct.getRentType().getId(),rentalProduct.getRentFee(),rentRequest.getStartDate(),rentRequest.getEndDate());
//            rentRequest.setRentFee(rentFee);
//
//            rentRequestModel.insert(rentRequest);
//
//
//        /* If */
//            if(createPayment!=null && createPayment.booleanValue()){
//                AdminPaypalCredential adminPaypalCredential = adminPaypalCredentailModel.getAdminPaypalCredentail();
//
//                if(adminPaypalCredential==null){
//                    serviceResponse.getResponseStat().setErrorMsg("No payPal App credential found");
//                    return serviceResponse;
//                }
//
//                if(rentRequest==null){
//                    serviceResponse.getResponseStat().setErrorMsg("No rent request found");
//                    return serviceResponse;
//                }
//
//                PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());
//                Payment createdPayment = payPalPayment.createPayment(rentRequest,
//                        baseURL + "/paypal/rent-payment/payment-success/"+rentRequest.getId(),
//                        baseURL + "/paypal/rent-payment/payment-cancel/"+rentRequest.getId());
//
//                extraObj.put("payId",createdPayment.getId());
//
//
//                Iterator<Links> links = createdPayment.getLinks().iterator();
//                while (links.hasNext()) {
//                    Links link = links.next();
//                    if (link.getRel().equalsIgnoreCase("approval_url")) {
//                        String approveUrl = link.getHref();
//                        if(PayPalPayment.mode.equals("sandbox")){
//                            approveUrl =  link.getHref().replaceAll("https://www.paypal.com/", "https://www.sandbox.paypal.com/");
//                        }
//                        extraObj.put("url",approveUrl);
//                        System.out.println("redirectURL" + link.getHref());
//                    }
//                }
//
//                System.out.println(createdPayment.toJSON());
//
//            }
//
//            serviceResponse.getResponseStat().setMsg("Request successfully sent");
//            serviceResponse.setResponseData(rentRequest, "Internal server error");
//            serviceResponse.setExtras(extraObj);
//
//
//            return serviceResponse;
//
//        }
//    }
}