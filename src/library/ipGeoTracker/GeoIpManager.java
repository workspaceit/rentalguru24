package library.ipGeoTracker;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/**
 * Created by mi on 10/21/16.
 */
import library.ipGeoTracker.dataModel.GeoIp;

public class GeoIpManager{
    private String server;
    private String format;
    private int statusCode;
    private String errorMsg;
    private boolean success;
    public GeoIpManager() {
        this.server = "http://freegeoip.net/";
        this.format = "json";
        this.success = true;
    }

    public GeoIpManager(String server, String format) {
        this.server = server;
        this.format = format;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public GeoIp getGeoIp(String ipAddress){
        RestTemplate rest = new RestTemplate();
        try{
            return rest.getForObject(this.server+"/"+this.format+"/"+ipAddress, GeoIp.class);
        }catch (HttpClientErrorException ex) {
            this.statusCode = ex.getStatusCode().value();
            this.success=false;
            this.errorMsg = ex.getMessage();
            return null;
        }
    }

}
