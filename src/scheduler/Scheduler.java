package scheduler;

import helper.DateHelper;

import library.paypal.PayPalPayment;
import model.CronLastExecutedModel;
import model.CronLogModel;
import model.RentRequestModel;
import model.entity.app.RentRequest;
import model.entity.developer.cron.CronLastExecuted;
import model.entity.developer.cron.CronLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 9/22/16.
 */


public class Scheduler {

    @Autowired
    PayPalPayment payPalPayment;
    @Autowired
    private RentRequestModel rentRequestModel;
    @Autowired
    private CronLogModel cronLogModel;
    @Autowired
    private CronLastExecutedModel cronLastExecutedModel;

    public void rentRequestExpiration(){

        Timestamp currentSystemUtcTime = DateHelper.getCurrentUtcDateTimeStamp();
        int maxRentRequestId = cronLastExecutedModel.getMaxRentRequestId();
        List<RentRequest> pendingRentRequestList = rentRequestModel.getAllPendingRequest(maxRentRequestId);

        System.out.println("Size of Rent request "+pendingRentRequestList.size()+" MAX ID : "+maxRentRequestId);

        String requestIds = "";
        int maxId =-1;
        for(RentRequest rentRequest : pendingRentRequestList){
            long h24 =   (((60* 60)*24 ) * 1000); // 24 Hours in nano sec
            Timestamp requestCreatedDate = rentRequest.getCreatedDate();
            requestCreatedDate.setTime(requestCreatedDate.getTime()+h24);

            int difference =  currentSystemUtcTime.compareTo(requestCreatedDate);
            System.out.println(" "+requestCreatedDate+" "+currentSystemUtcTime+" "+difference);
            if(difference>=0){
                if(rentRequest.getIsPaymentComplete()){
                    payPalPayment.refundOtherRentRequest(rentRequest);
                }

                rentRequest.setIsExpired(true);
                rentRequestModel.update(rentRequest);


                  /* For Cron Log */
                requestIds+=" "+rentRequest.getId();
                maxId = rentRequest.getId();
            }
        }

        if(pendingRentRequestList.size()>0){

            /*~~~~~~~~~~ Max Id Storing ~~~~~~~~~~~~~~~~~~ */
            try{
                if(maxId>0){
                    RentRequest rentRequest = pendingRentRequestList.get(maxId);
                    CronLastExecuted cronLastExecuted = new CronLastExecuted();
                    cronLastExecuted.setRentRequestId(rentRequest.getId());

                    cronLastExecutedModel.saveOrUpdateMaxRentRequestId(cronLastExecuted);
                }
            }catch (Exception ex){
                System.out.println("Exception caused by Max Id :"+maxId);
            }


        }
          /*~~~~~~~~~~ Cron Log ~~~~~~~~~~~~~~~~~~ */
        CronLog cronLog = new CronLog();
        cronLog.setDescription("RENT REQUEST ID : "+requestIds);
        cronLog.setCreatedDate(currentSystemUtcTime);
        cronLogModel.insert(cronLog);
    }
}