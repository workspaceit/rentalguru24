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
    public static void setAdminCredentialInSession(HttpServletRequest request,ServiceResponse serviceResponse,AppCredential appCredential){

        serviceResponse.getResponseStat().setIsLogin(true);
        request.getSession().setAttribute("adminAppCredential", appCredential);
        /* For Ever Session is open unless signout ... Not a Good idea */
        request.getSession().setMaxInactiveInterval(-1);
    }
    public static void destroySession(HttpServletRequest request){
        request.getSession().invalidate();
    }
    public static void setVerificationSession(HttpServletRequest request,Boolean verified){
        request.getSession().setAttribute("appVerification", verified);
    }
    public static Boolean getVerificationSession(HttpServletRequest request,Boolean verified){
        return (Boolean)request.getSession().getAttribute("appVerification");
    }

}
