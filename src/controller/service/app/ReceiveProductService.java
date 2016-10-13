package controller.service.app;

import com.paypal.api.payments.PayoutBatch;
import com.paypal.api.payments.Refund;
import com.paypal.base.rest.PayPalRESTException;
import helper.DateHelper;
import helper.ServiceResponse;
import library.paypal.PayPalPayment;
import model.*;
import model.admin.AdminPaypalCredentialModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.app.AppCredential;
import model.entity.app.UserPaypalCredential;
import model.entity.app.payments.PaymentRefund;
import model.entity.app.payments.Payout;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductReturned;
import model.entity.app.product.rentable.RentalProductReturnedHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/29/16.
 */
@RestController
@RequestMapping("/api/auth/receive-product")
public class ReceiveProductService {
    @Autowired
    RentalProductReturnedModel rentalProductReturnedModel;
    @Autowired
    RentalProductReturnedHistoryModel rentalProductReturnedHistoryModel;
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    ProductModel productModel;
    @Autowired
    RentPaymentModel rentPaymentModel;
    @Autowired
    AdminPaypalCredentialModel adminPaypalCredentialModel;
    @Autowired
    UserPaypalCredentialModel userPaypalCredentialModel;
    @Autowired
    PayoutModel payoutModel;
    @Autowired
    PaymentRefundModel paymentRefundModel;


    @RequestMapping(value = "/confirm-receive/{rentalProductReturnId}", method = RequestMethod.POST)
    public ServiceResponse renturnProduct(HttpServletRequest request,
                                          @PathVariable("rentalProductReturnId") int rentalProductReturnId,
                                          @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturned rentalProductReturned = rentalProductReturnedModel.getById(rentalProductReturnId);

        if(rentalProductReturned==null){
            serviceResponse.setRequestError("rentalProductReturnId","No product return information is found by this rentalProductReturnId");
            return serviceResponse;
        }

        if(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentalProductReturnId","Can't confirm received ,you are not product owner !! Suck a lemon");
            return serviceResponse;
        }

        if(rentalProductReturned.isConfirm()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.isDispute()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.getIsExpired()) {
            serviceResponse.setRequestError("rentalProductReturnId","This record is expired");
            return serviceResponse;
        }


        UserPaypalCredential payoutUserPaypalCredential = userPaypalCredentialModel.getByAppCredentialId(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId());
        if(payoutUserPaypalCredential==null){

            serviceResponse.setRequestError("paypalCredential","You have not add paypal account yet");
            return serviceResponse;
        }

        RentPayment rentPayment = rentPaymentModel.getByRentRentInfId(rentalProductReturned.getRentInf().getId());






        /*~~~~~~~~~~ Paypal initiation ~~~~~~~~~~~~~~~~~*/

        AdminPaypalCredential adminPaypalCredentail = adminPaypalCredentialModel.getAdminPaypalCredentail();
        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredentail.getApiKey(),adminPaypalCredentail.getApiSecret());

         /*~~~~~~~~ Refund to rentee initiating~~~~~~~~~~~~*/

        Double refundAmount = rentPayment.getTotalAmount() - ( rentPayment.getRentFee() + rentPayment.getSiteFee() );
        Refund refund = null;

        try {
            refund = payPalPayment.refund(rentPayment.getPaypalSaleId(),refundAmount);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        if(refund==null){
            serviceResponse.getResponseStat().setErrorMsg("Paypal server error try again later");
            return serviceResponse;
        }
        PaymentRefund paymentRefund = new PaymentRefund();
        paymentRefund.setAppCredential(rentalProductReturned.getRentInf().getRentRequest().getRequestedBy());
        paymentRefund.setParentPayId(refund.getParentPayment());
        paymentRefund.setPaypalSaleId(refund.getSaleId());
        try{
            paymentRefund.setTotalAmount(Double.parseDouble(refund.getAmount().getTotal()));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
        paymentRefund.setRentPayment(rentPayment);

        paymentRefundModel.insert(paymentRefund);


       /*~~~~~~~~ Payout initiating~~~~~~~~~~~~*/

        Payout payout = new Payout();
        Double payOutAmount = rentPayment.getRentFee();
        String payOutAmountStr = String.format("%.2f",payOutAmount);
        String payoutState = "pending";
        if(payoutUserPaypalCredential!=null && !payoutUserPaypalCredential.getEmail().isEmpty()){

            PayoutBatch payoutBatch = null;
            try {
                payoutBatch = payPalPayment.payOut(payoutUserPaypalCredential.getEmail(), payOutAmountStr, "You have a payout", "Payout released");
                System.out.println(payoutBatch.toJSON());
            } catch (PayPalRESTException e) {
                e.printStackTrace();
                serviceResponse.getResponseStat().setErrorMsg("Paypal server error try again later");
                return serviceResponse;
            }
            if(payoutBatch==null){

                serviceResponse.getResponseStat().setErrorMsg("Paypal server error try again later");
                return serviceResponse;
            }
            payoutState = "completed";
            if(payoutBatch.getItems()!=null && payoutBatch.getItems().size()>0){

                payout.setTransactionId(payoutBatch.getItems().get(0).getTransactionId());
                payout.setTransactionStatus(payoutBatch.getItems().get(0).getTransactionStatus());
                payout.setPayoutBatchId(payoutBatch.getItems().get(0).getPayoutBatchId());
            }
        }else{

        }

        /*~~~~~~~~~~~~~ Payout entity ~~~~~~~~~~~~~~~~~~*/

        payout.setAppCredential(rentalProductReturned.getRentInf().getRentalProduct().getOwner());
        payout.setTotalAmount(payOutAmount);
        payout.setRentInf(rentalProductReturned.getRentInf());
        payout.setState(payoutState);

        payoutModel.insert(payout);
        /*~~~~~~~~~~~~~~~~~~Change rent inf complete ~~~~~~~~~~~~~~~`*/
        RentInf rentInf = rentalProductReturned.getRentInf();
        rentInf.setIsRentComplete(true);
        if(rentInf.getRentRequest()!=null){
            rentInf.getRentRequest().setIsRentComplete(true);
        }
        rentInfModel.update(rentInf);
        /*~~~~~~~~~~~~~~Payout Entity [Ends]~~~~~~~~~~~~~~~*/

        this.processProductReturnConfirmDispute(serviceResponse,rentalProductReturned,remarks,true,false);

        return serviceResponse;
    }
    @RequestMapping(value = "/dispute-receive/{rentalProductReturnId}", method = RequestMethod.POST)
    public ServiceResponse disputeReturnProduct(HttpServletRequest request,
                                                @PathVariable("rentalProductReturnId") int rentalProductReturnId,
                                                @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturned rentalProductReturned = rentalProductReturnedModel.getById(rentalProductReturnId);

        if(rentalProductReturned==null){
            serviceResponse.setRequestError("rentalProductReturnId","No product return information is found by this rentalProductReturnId");
            return serviceResponse;
        }

        if(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentalProductReturnId","Can't confirm received ,you are not product owner !! Suck a lemon");
            return serviceResponse;
        }

        if(rentalProductReturned.isConfirm()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.isDispute()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.getIsExpired()) {
            serviceResponse.setRequestError("rentalProductReturnId","This record is expired");
            return serviceResponse;
        }


        this.processProductReturnConfirmDispute(serviceResponse,rentalProductReturned,remarks,false,true);

        return serviceResponse;
    }
    private ServiceResponse processProductReturnConfirmDispute(ServiceResponse serviceResponse,
                                                               RentalProductReturned rentalProductReturned,
                                                               String remarks,
                                                               Boolean confirm,
                                                               Boolean dispute){
        rentalProductReturned.setConfirm(confirm);
        rentalProductReturned.setDispute(dispute);
        //rentalProductReturned.setIsExpired(true);
        rentalProductReturned.setRenterRemarks((remarks == null || remarks.trim().isEmpty()) ? null : remarks);
        //rentalProductReturned.getRentInf().setExpired(false);


        /* Update RentalProductReturned .. Set Product receive on confirmed true/false  */
        rentalProductReturned.getRentInf().setProductReceived(confirm);
        rentalProductReturnedModel.update(rentalProductReturned);

        /* Insert to RentalProductReturnedHistory .. */
        RentalProductReturnedHistory rentalProductReturnedHistory = new RentalProductReturnedHistory();

        rentalProductReturnedHistory.setConfirm(rentalProductReturned.isConfirm());
        rentalProductReturnedHistory.setDispute(rentalProductReturned.isDispute());
        rentalProductReturnedHistory.setRentalProductReturned(rentalProductReturned);
        rentalProductReturnedHistory.setCreatedDate(DateHelper.getCurrentUtcDateTimeStamp());
        System.out.println("DateHelper.getCurrentUtcDateTimeStamp().getTime() " + DateHelper.getCurrentUtcDateTimeStamp().getTime());

        rentalProductReturnedHistoryModel.insert(rentalProductReturnedHistory);

        /* Make product available */
        rentalProductReturned.getRentInf().getRentalProduct().setCurrentlyAvailable(true);

        productModel.update(rentalProductReturned.getRentInf().getRentalProduct());
        serviceResponse.setResponseData(rentalProductReturned);

        return serviceResponse;
    }


}