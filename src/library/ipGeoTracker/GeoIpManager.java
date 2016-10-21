package library.ipGeoTracker;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/**
 * Created by mi on 10/21/16.
 */
import library.ipGeoTracker.dataModel.GeoIp;

import javax.servlet.http.HttpServletRequest;

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
    public GeoIp getGeoIp(HttpServletRequest request){
        return getGeoIp(GeoIpManager.getRemoteAddress(request));
    }
    public static String getRemoteAddress(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress != null) {
            ipAddress = ipAddress.replaceFirst(",.*", "");  // cares only about the first IP if there is a list
        } else {
            ipAddress = req.getRemoteAddr();
        }
        return ipAddress;
    }
    // Does not works on ajax
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
