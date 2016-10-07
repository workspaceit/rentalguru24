package scheduler;

import helper.DateHelper;

import library.paypal.PayPalPayment;
import model.RentPaymentModel;
import model.RentRequestModel;
import model.admin.AdminPaypalCredentialModel;
import model.entity.admin.AdminPaypalCredential;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 9/22/16.
 */


public class Scheduler {

    @Autowired
    PayPalPayment payPalPayment;
    @Autowired
    private RentPaymentModel rentPaymentModel;
    @Autowired
    private RentRequestModel rentRequestModel;
    @Autowired
    private AdminPaypalCredentialModel adminPaypalCredentialModel;

    public void rentRequestExpiration(){

        Timestamp currentSystemUtcTime = DateHelper.getCurrentUtcDateTimeStamp();
        List<RentRequest> pendingRentRequestList = rentRequestModel.getAllPendingRequest(0);
        System.out.println("Hi THere"+pendingRentRequestList.size());
       // payPalPayment.getDetails("asdasd");
        for(RentRequest rentRequest : pendingRentRequestList){
          System.out.println(rentRequest.getId());
        //   payPalPayment.refundOtherRentRequest(rentRequest);
        }
    }



}