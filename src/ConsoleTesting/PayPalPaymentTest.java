package ConsoleTesting;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.*;

public class PayPalPaymentTest {
    private static final String clientID = "AWQr0Ls0qt0zRtXFvSBZ2k3zNgt-0ME5eI6qC8A9dTh2RHodYtDre5cJT7BNElg9mm3dZw6v9F-G-vyn";
    private static final String clientSecret = "EAiElxCy_o6-h3VKR_iAGIwUVisEUInSXQwbdgRo-Fd8cKUujB2RH86LTXHwOzgUAAGY6Lbm0Nu3kV9q";
    public static final String mode = "sandbox";

    public static void main(String args[]){
        new PayPalPaymentTest().createPayment();
        // new PayPalPayment().executePayments("PAY-1C069959SC325024UK7P3ADI", "TKCD9W66CR9R4");
        // new PayPalPayment().refund("0CR29744U1543724W");
        //    new PayPalPayment().payOut();



    }
    public void executePayments(String payId,String payerId){
        APIContext apiContext = new APIContext(clientID,clientSecret, mode);

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

            createdPayment = payment.execute(apiContext, paymentExecution);
            System.out.println(createdPayment.toJSON());
            System.out.println("Executed The Payment " + com.paypal.api.payments.Payment.getLastRequest() + com.paypal.api.payments.Payment.getLastResponse());
        } catch (PayPalRESTException e) {
            System.out.println("Executed The Payment " + "Executed The Payment" + com.paypal.api.payments.Payment.getLastRequest() + e.getMessage());
        }

    }
    public void createPayment(){
        try {
            com.paypal.api.payments.Payment createdPayment = null;
            APIContext apiContext = new APIContext(clientID,clientSecret, mode);
            // ###Details
            // Let's you specify details of a payment amount.
            Details details = new Details();
            details.setShipping("1");
            details.setSubtotal("5");
            details.setTax("1");

            // ###Amount
            // Let's you specify a payment amount.
            Amount amount = new Amount();
            amount.setCurrency("USD");
            // Total must be equal to sum of shipping, tax and subtotal.
            amount.setTotal("7");
            amount.setDetails(details);

            // ###Transaction
            // A transaction defines the contract of a
            // payment - what is the payment for and who
            // is fulfilling it. Transaction is created with
            // a `Payee` and `Amount` types
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction
                    .setDescription("This is the payment transaction description.");

            // ### Items
            Item item = new Item();
            item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("5");
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
            redirectUrls.setCancelUrl("http://localhost:9090/home?r=approve");
            redirectUrls.setReturnUrl("http://localhost:9090/home?r=cancel");
            payment.setRedirectUrls(redirectUrls);

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                createdPayment = payment.create(apiContext);
                System.out.println("Created payment with id = "
                        + createdPayment.getId() + " and status = "
                        + createdPayment.getState());
                System.out.println(createdPayment.toJSON());
                // ###Payment Approval Url
                Iterator<Links> links = createdPayment.getLinks().iterator();
                while (links.hasNext()) {
                    Links link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        link.setHref(link.getHref().replaceAll("https://www.paypal.com/","https://www.sandbox.paypal.com/"));
                        System.out.println("redirectURL"+ link.getHref());
                    }
                }

            } catch (PayPalRESTException e) {
                System.out.println(e.getDetails().getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refund(String saleId){
        APIContext apiContext = new APIContext(clientID, clientSecret, mode);


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
        // ###Amount
        // Create an Amount object to
        // represent the amount to be
        // refunded. Create the refund object, if the refund is partial
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("4.00");
        refund.setAmount(amount);
        try {
            // ### Api Context
            // Pass in a `ApiContext` object to authenticate
            // the call and to send a unique request id
            // (that ensures idempotency). The SDK generates
            // a request id if you do not pass one explicitly.


            // Refund by posting to the APIService
            // using a valid AccessToken
            sale.refund(apiContext, refund);
            System.out.println("Sale Refunded "+Sale.getLastRequest()+Sale.getLastResponse());
        } catch (PayPalRESTException e) {
            System.out.println("Sale Refunded"+Sale.getLastRequest()+e.getMessage());
        }
    }
    public void payOut(){
        Payout payout = new Payout();

        PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();

        // ### NOTE:
        // You can prevent duplicate batches from being processed. If you
        // specify a `sender_batch_id` that was used in the last 30 days, the
        // batch will not be processed. For items, you can specify a
        // `sender_item_id`. If the value for the `sender_item_id` is a
        // duplicate of a payout item that was processed in the last 30 days,
        // the item will not be processed.
        // #### Batch Header Instance
        Random random = new Random();
        senderBatchHeader.setSenderBatchId(
                new Double(random.nextDouble()).toString()).setEmailSubject(
                "You have a Payout!");

        // ### Currency
        Currency amount = new Currency();
        amount.setValue("1.00").setCurrency("USD");

        // #### Sender Item
        // Please note that if you are using single payout with sync mode, you
        // can only pass one Item in the request
        PayoutItem senderItem = new PayoutItem();
        senderItem.setRecipientType("Email")
                .setNote("Thanks for your patronage")
                .setReceiver("tahsin_1356501324_per@gmail.com1").setAmount(amount);

        List<PayoutItem> items = new ArrayList<PayoutItem>();
        items.add(senderItem);

        payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

        PayoutBatch batch = null;
        try {

            // ### Api Context
            // Pass in a `ApiContext` object to authenticate
            // the call and to send a unique request id
            // (that ensures idempotency). The SDK generates
            // a request id if you do not pass one explicitly.
            APIContext apiContext = new APIContext(clientID, clientSecret, mode);

            // ###Create Payout Synchronous
            batch = payout.createSynchronous(apiContext);

            System.out.println("Payout Batch With ID: "
                    + batch.getBatchHeader().getPayoutBatchId());
            System.out.println("Created Single Synchronous Payout"+
                    Payout.getLastRequest()+ Payout.getLastResponse());
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
    }
}
