package helper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mi on 10/29/15.
 */
public class DateHelper {



    public static String getProcessedTimeStamp(Timestamp timeStamp) {
        String processedTime = "";
        if(timeStamp!=null){

            Long longTime = timeStamp.getTime() / 1000;
            processedTime = Long.toString(longTime);
        }

        return processedTime;
    }
    public static String getUtcDateProcessedTimeStamp() {
        String processedTime = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = dateFormat.parse(dateFormat.format(new Date()));
            Timestamp timeStampDate = new Timestamp(date.getTime());
            Long longTime = timeStampDate.getTime() / 1000;
            return Long.toString(longTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return "";
    }
    public static String getUtcDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date());
    }
    public static Timestamp getUtcTimeStamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));


        return getStringToTimeStamp(dateFormat.format(new Date()),"yyyy-MM-dd");
    }

    public static boolean isDateValid(String date,String dateFormat)
    {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Timestamp getStringToTimeStamp(String strDate,String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(strDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
