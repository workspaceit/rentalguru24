package helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mi on 9/20/16.
 */
public class RentFeesHelper {
    final static int DAY=1;
    final static int WEEK=2;
    final static int MONTH=3;
    final static int YEAR=4;

    public static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    public static double getPerDayRentFee(int rentTypeId,double rentFee){

        switch (rentTypeId){
            case  DAY:
                return rentFee;
            case  WEEK:
                return rentFee/7;
            case  MONTH:
                return rentFee/30;
            case  YEAR:
                return rentFee/365;
            default:
                return rentFee;
        }
    }
    public static double getRentFee(int rentTypeId,double rentFee,Date startDate,Date endsDate){
        int day = daysBetween(startDate,endsDate);
        System.out.println("Days= "+day);
        double fee = day*getPerDayRentFee(rentTypeId,rentFee);

        return fee;
    }
}
