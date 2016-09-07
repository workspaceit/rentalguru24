package helper;


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
    public static Object getAppCredential(HttpServletRequest request){
        return request.getSession().getAttribute("appCredential");

    }
    public static void setAdminCredentialInSession(HttpServletRequest request,ServiceResponse serviceResponse,AppCredential appCredential){

        serviceResponse.getResponseStat().setIsLogin(true);
        request.getSession().setAttribute("adminAppCredential", appCredential);
        /* For Ever Session is open unless signout ... Not a Good idea */
        request.getSession().setMaxInactiveInterval(-1);
    }
    public static Object getAdminCredential(HttpServletRequest request){
        return request.getSession().getAttribute("adminAppCredential");
    }
    public static void destroySession(HttpServletRequest request){
        request.getSession().invalidate();
    }
    public static void setVerificationSession(HttpServletRequest request,Boolean verified){
        request.getSession().setAttribute("appUserVerification", verified);
    }
    public static Boolean getAppUserVerification(HttpServletRequest request){
        return (Boolean)request.getSession().getAttribute("appUserVerification");
    }
}
