package ConsoleTesting;

import helper.DateHelper;

import java.sql.Timestamp;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mi on 9/20/16.
 */
public class DateHelperTest {
    final static int DAY=1;
    final static int WEEK=2;
    final static int MONTH=3;
    final static int YEAR=4;
    public static void main(String args[]){
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        Date date2 = null;
        try {
            date = sdf.parse("02-02-2015");
            cal1.setTime(date);
            date2 = sdf.parse("03-02-2017");
            cal2.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //cal1.set(2008, 8, 1);
        //cal2.set(2008, 9, 31);

//        System.out.println("FEEs = "+getRentFee(4,2, DateHelper.getSQLDateToTimeStamp(date), DateHelper.getSQLDateToTimeStamp(date2)));
        System.out.println("FEEs = "+ String.format("%.2f", 465465.54879678676));

    }
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
    public static double getRentFee(int rentTypeId,double rentFee,Timestamp startDate,Timestamp endsDate){
        int day = daysBetween(new Date(startDate.getTime()),new Date(endsDate.getTime()));
        System.out.println("Days= "+day);
        return day*getPerDayRentFee(rentTypeId,rentFee);
    }
}
