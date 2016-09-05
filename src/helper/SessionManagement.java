package helper;

import jdk.nashorn.internal.ir.RuntimeNode;
import model.entity.app.AppCredential;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/24/16.
 */
public class SessionManagement {
    public static void setAppCredentialInSession(HttpServletRequest request,ServiceResponse serviceResponse,AppCredential appCredential){

        serviceResponse.getResponseStat().setIsLogin(true);
        request.getSession().setAttribute("appCredential", appCredential);
        /* For Ever Session is open unless signout ... Not a Good idea */
        request.getSession().setMaxInactiveInterval(-1);
    }
    public static void destroySession(HttpServletRequest request){
        request.getSession().invalidate();
    }
    public static void setPreviousUrl(HttpServletRequest request,String url){

        request.getSession().setAttribute("prevUrl", url);
        System.out.println("**********FROM SETTER*********");
        System.out.println(url);
        System.out.println((String) request.getSession().getAttribute("prevUrl"));
        /* For Ever Session is open unless signout ... Not a Good idea */
    }
    public static String getPreviousUrl(HttpServletRequest request){

        return (String)request.getSession().getAttribute("prevUrl");
    }
    public static void destroyPreviousUrl(HttpServletRequest request){

        request.getSession().setAttribute("prevUrl", null);
    }
}
