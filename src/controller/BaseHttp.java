package controller;

import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mi on 8/1/16.
 */

public class BaseHttp {

    public ServiceResponse serviceResponse;
    public AppCredential appCredential;
    private HttpSession httpSession;
    private HttpServletRequest httpServletRequest;
    private String baseURL;
    public BaseHttp() {
        this.serviceResponse = new ServiceResponse();
        this.appCredential = new AppCredential();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        httpServletRequest = ar.getRequest();
        httpSession = httpServletRequest.getSession();
        baseURL = getURLWithContextPath(httpServletRequest);

        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            try{

                this.appCredential = (AppCredential)httpSession.getAttribute("appCredential");
            }catch (Exception ex){
                ex.printStackTrace();
            }
            this.serviceResponse.getResponseStat().setIsLogin(true);
        }

    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setAppcredentialInSession(AppCredential appCredential){
        this.appCredential = appCredential;
        this.serviceResponse.getResponseStat().setIsLogin(true);
        httpSession.setAttribute("appCredential", appCredential);
    }
    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}