package controller.service.app;

import model.RentRequestModel;
import model.entity.app.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by omar on 8/3/16.
 */
@RestController
@RequestMapping("/api/rent")
public class RentRequestService {
    @Autowired
    RentRequestModel rentRequestModel;
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public void postRentRequsest(@RequestParam Map<String, String> allrequstvalue) throws ParseException {

        String productId = allrequstvalue.get("productId");
        String requestedBy = allrequstvalue.get("requestedBy");
        String bookingId = allrequstvalue.get("bookingId");
        String requestId = allrequstvalue.get("requestId");
        String startDate = allrequstvalue.get("startDate");
        String endDate = allrequstvalue.get("endDate");

//        System.out.println(productId);
//        System.out.println(requestedBy);
//        System.out.println(bookingId);
//        System.out.println(requestId);
//        System.out.println(startDate);
//        System.out.println(endDate);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date Sdate = formatter.parse(startDate);
        java.sql.Date SDate = new Date(Sdate.getTime());

        java.util.Date Edate = formatter.parse(endDate);
        java.sql.Date EDate = new Date(Edate.getTime());

        RentRequest rentRequest = new RentRequest();

        rentRequest.setProductId(Integer.parseInt(productId));
        rentRequest.setRequestedBy(Integer.parseInt(requestedBy));
        rentRequest.setBookingId(Integer.parseInt(bookingId));
        rentRequest.setRequestId(Integer.parseInt(requestId));
        rentRequest.setStartDate(SDate);
        rentRequest.setEndDate(EDate);

        rentRequestModel.insert(rentRequest);
    }
}
