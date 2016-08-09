package controller.service.app;

import controller.service.BaseService;
import helper.DateHelper;
import helper.ServiceResponse;
import model.RentProductModel;
import model.RentRequestModel;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by omar on 8/3/16.
 */
@RestController
@RequestMapping("/api/rent")
@Scope("request")
public class RentRequestService extends BaseService{
    @Autowired
    RentRequestModel rentRequestModel;
    @Autowired
    RentProductModel rentProductModel;
    @RequestMapping(value = "/request-rent/{productId}", method = RequestMethod.POST)
    public ServiceResponse testMail(@PathVariable("productId") int productId,@RequestParam("startDate")String startDate,@RequestParam("endsDate")String endsDate){
        if(!this.serviceResponse.getResponseStat().getIsLogin()){
            this.serviceResponse.getResponseStat().setErrorMsg("Session expired !! , please login ");
            return this.serviceResponse;
        }


        if(startDate==null || startDate.isEmpty()){
            this.serviceResponse.setRequestError("startDate","Start date is required");
        }
        if(endsDate==null || endsDate.isEmpty()){
            this.serviceResponse.setRequestError("endsDate","End date is required");
        }

        if(this.serviceResponse.hasErrors()){
            return this.serviceResponse;
        }


        if(!DateHelper.isDateValid(startDate, "dd-MM-yyyy")){
            this.serviceResponse.setRequestError("startDate","Start date format miss matched");
        }
        if(!DateHelper.isDateValid(endsDate, "dd-MM-yyyy")){
            this.serviceResponse.setRequestError("endsDate","Ends date format miss matched");
        }

        if(this.serviceResponse.hasErrors()){
            return this.serviceResponse;
        }


        Timestamp startTimeStamp = DateHelper.getStringToTimeStamp(startDate, "dd-MM-yyyy");
        Timestamp endTimeStamp = DateHelper.getStringToTimeStamp(endsDate,"dd-MM-yyyy");


        if(rentProductModel.isProductInRent(productId,startTimeStamp,endTimeStamp)){
            this.serviceResponse.setRequestError("productId","Product is not available for rent in given date");
            return this.serviceResponse;
        }

        if(this.serviceResponse.hasErrors()){
            return this.serviceResponse;
        }

        RentRequest rentRequest = new RentRequest();

        rentRequest.setApprove(false);
        rentRequest.setRequestedBy(this.appCredential.getId());
        rentRequest.setProductId(productId);
        rentRequest.setStartDate(new Date(startTimeStamp.getTime()));
        rentRequest.setEndDate(new Date(endTimeStamp.getTime()));
        rentRequestModel.insert(rentRequest);
        this.serviceResponse.setResponseData(rentRequest, "Internal server error");
        return this.serviceResponse;

    }
}
