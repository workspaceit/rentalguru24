package library.paypal;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import model.PaymentRefundModel;
import model.RentPaymentModel;
import model.admin.AdminPaypalCredentialModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.admin.AdminSiteFeesEntity;
import model.entity.app.RentRequest;
import model.entity.app.payments.PaymentRefund;
import model.entity.app.payments.RentPayment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PayPalPayment {
    private APIContext apiContext;
    private  String clientID;
    private  String clientSecret;

    private PaymentRefundModel paymentRefundModel;
    private AdminPaypalCredentialModel adminPaypalCredentialModel;
    private RentPaymentModel rentPaymentModel;
    public static final String mode = "sandbox";

    public PayPalPayment() {

    }
    public PayPalPayment(String clientID,String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.apiContext = new APIContext(this.clientID,this.clientSecret, mode);
    }

    public PaymentRefundModel getPaymentRefundModel() {
        return paymentRefundModel;
    }

    public void setPaymentRefundModel(PaymentRefundModel paymentRefundModel) {
        this.paymentRefundModel = paymentRefundModel;
    }

    public RentPaymentModel getRentPaymentModel() {
        return rentPaymentModel;
    }

    public void setRentPaymentModel(RentPaymentModel rentPaymentModel) {
        this.rentPaymentModel = rentPaymentModel;
    }

    public AdminPaypalCredentialModel getAdminPaypalCredentialModel() {
        return adminPaypalCredentialModel;
    }

    public void setAdminPaypalCredentialModel(AdminPaypalCredentialModel adminPaypalCredentialModel) {
        this.adminPaypalCredentialModel = adminPaypalCredentialModel;

        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
        this.clientID = adminPaypalCredential.getApiKey();
        this.clientSecret = adminPaypalCredential.getApiSecret();
        this.apiContext = new APIContext(this.clientID,this.clientSecret, mode);
    }

    public Payment getDetails(String payId){
        Payment payment = null;
        try {
            payment = Payment.get(this.apiContext, payId);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return payment;
    }
    public Payment executePayments(String payId,String payerId){

        // ### Api Context
        // Pass in a `ApiContext` object to authenticate
        // the call and to send a unique request id
        // (that ensures idempotency). The SDK generates
        // a request id if you do not pass one explicitly.
            Payment createdPayment = null;
            Payment payment = new com.paypal.api.payments.Payment();
            payment.setId(payId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);
            try {
                createdPayment = payment.execute(this.apiContext, paymentExecution);
            } catch (PayPalRESTException e) {
                System.out.println("Executed The Payment " + "Executed The Payment" + com.paypal.api.payments.Payment.getLastRequest() + e.getMessage());
            }
        return createdPayment;

    }
    public Payment createPayment(RentRequest rentRequest, AdminSiteFeesEntity adminSiteFee,String successRedirect,String cancelRedirect){
        try {
            Payment createdPayment = null;


            Details details = new Details();
            //details.setShipping("1");
            //details.setSubtotal("5");
            //details.setTax("1");

            /* Calculating rent fee */
//            Double rentCharge = RentFeesHelper.getRentFee( rentRequest.getRentalProduct().getRentType().getId(),
//                    rentRequest.getRentalProduct().getRentFee(),
//                    rentRequest.getStartDate(),
//                    rentRequest.getEndDate());
            /* Setting current value as rent charge */
            double rentCharge = rentRequest.getRentalProduct().getCurrentValue();

            double siteFees = 0;
            if(adminSiteFee.isFixed()){
                siteFees = adminSiteFee.getFixedValue();
            }else if(adminSiteFee.isPercentage()){
                siteFees = (adminSiteFee.getPercentageValue()*rentCharge) / 100;
            }


            // ###Amount
            // Let's you specify a payment amount.
            Amount amount = new Amount();
            amount.setCurrency("USD");
            // Total must be equal to sum of shipping, tax and subtotal.
            rentCharge = rentCharge+siteFees;
            amount.setTotal(String.format("%.2f",rentCharge));
            amount.setDetails(details);


            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("Rent request for " + rentRequest.getRentalProduct().getName());

            // ### Items

            Item item = new Item();
            item.setName(rentRequest.getRentalProduct().getName())
                    .setQuantity("1")
                    .setCurrency("USD")
                    .setPrice(String.format("%.2f", rentCharge));
            ItemList itemList = new ItemList();
            List<Item> items = new ArrayList<Item>();
            items.add(item);
            itemList.setItems(items);

            transaction.setItemList(itemList);


            // The Payment creation API requires a list of
            // Transaction; add the created `Transaction`
            // to a List
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);

            // ###Payer
            // A resource representing a Payer that funds a payment
            // Payment Method
            // as 'paypal'
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            // ###Payment
            // A Payment Resource; create one using
            // the above types and intent as 'sale'
            com.paypal.api.payments.Payment payment = new com.paypal.api.payments.Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            // ###Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            redirectUrls.setReturnUrl(successRedirect);
            redirectUrls.setCancelUrl(cancelRedirect);
            payment.setRedirectUrls(redirectUrls);

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                createdPayment = payment.create(this.apiContext);
                return createdPayment;

            } catch (PayPalRESTException e) {
                System.out.println(e.getDetails().getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Refund refund(String saleId, Double totalAmount) throws PayPalRESTException {
        String total = String.format("%.2f",totalAmount);
        // ###Sale
        // A sale transaction.
        // Create a Sale object with the
        // given sale transaction id.
        Sale sale = new Sale();
        sale.setId(saleId);

        // ###Refund
        // A refund transaction.
        // Use the amount to create
        // a refund object
        Refund refund = new Refund();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total);

        refund.setAmount(amount);
        System.out.println(refund.toJSON());
        System.out.println(sale.toJSON()    );

        return  sale.refund(this.apiContext, refund);
    }

    public PayoutBatch payOut(String receiverEmail,String payOutamount,String note,String emailSubject) throws PayPalRESTException {
        Payout payout = new Payout();

        PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();


        Random random = new Random();
        senderBatchHeader.setSenderBatchId(
                new Double(random.nextDouble()).toString()).setEmailSubject(emailSubject);

        // ### Currency
        Currency amount = new Currency();
        amount.setValue(payOutamount).setCurrency("USD");

        // #### Sender Item
        // Please note that if you are using single payout with sync mode, you
        // can only pass one Item in the request
        PayoutItem senderItem = new PayoutItem();
        senderItem.setRecipientType("Email")
                .setNote(note)
                .setReceiver(receiverEmail).setAmount(amount);

        List<PayoutItem> items = new ArrayList<PayoutItem>();
        items.add(senderItem);

        payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

        PayoutBatch batch = null;

        batch = payout.createSynchronous(this.apiContext);
        return batch;
    }
    public Capture capturePayment(Amount amount ,Authorization authorization ){
        Capture capture = null;
        try {
            String captureId = getCaptureId(amount, authorization);


            capture = Capture.get(apiContext, captureId);

            System.out.println("Capture id = " + capture.getId()
                    + " and status = " + capture.getState());
        } catch (PayPalRESTException e) {
            System.out.println("Get Capture"+Capture.getLastRequest()+ e.getMessage());
        }
        return capture;

    }
    private String getCaptureId(Amount amount ,Authorization authorization) throws PayPalRESTException{
        String captureId = null;



        // ###Capture
        Capture capture = new Capture();
        capture.setAmount(amount);

        capture.setIsFinalCapture(true);

        // Capture by POSTing to
        // URI v1/payments/authorization/{authorization_id}/capture
        Capture responseCapture = authorization.capture(apiContext, capture);
        captureId = responseCapture.getId();

        return captureId;
    }
    public void refundOtherRentRequest(RentRequest rentRequest){
        AdminPaypalCredential adminPaypalCredential = adminPaypalCredentialModel.getAdminPaypalCredentail();
        PayPalPayment payPalPayment = new PayPalPayment(adminPaypalCredential.getApiKey(),adminPaypalCredential.getApiSecret());
        RentPayment rentPayment = rentPaymentModel.getByRentRequestId(rentRequest.getId());
        Refund refund = null;

        if(rentPayment==null){
            return;
        }
        try {
            refund = payPalPayment.refund(rentPayment.getPaypalSaleId(),rentPayment.getTotalAmount());
            System.out.print(refund.toJSON());
        } catch (PayPalRESTException e) {
            System.out.println(Sale.getLastRequest() + e.getMessage());

        }



//     refund(String saleId, Amount amount);
        PaymentRefund paymentRefund = new PaymentRefund();
        paymentRefund.setAppCredential(rentRequest.getRequestedBy());
        paymentRefund.setRentPayment(rentPayment);
        paymentRefund.setParentPayId(refund.getParentPayment());
        paymentRefund.setPaypalSaleId(refund.getSaleId());
        paymentRefund.setTotalAmount(Double.parseDouble(refund.getAmount().getTotal()));
        paymentRefundModel.insert(paymentRefund);

    }



}

