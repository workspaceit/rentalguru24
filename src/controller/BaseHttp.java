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

    public BaseHttp() {
        this.serviceResponse = new ServiceResponse();
        this.appCredential = new AppCredential();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        httpServletRequest = ar.getRequest();
        httpSession = httpServletRequest.getSession();

        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            try{

                this.appCredential = (AppCredential)httpSession.getAttribute("appCredential");
            }catch (Exception ex){
                ex.printStackTrace();
            }
            this.serviceResponse.getResponseStat().setIsLogin(true);
        }

        System.out.println("FIRST NAME "+appCredential.getUserInf().getFirstName());
    }
    public void setAppcredentialInSession(AppCredential appCredential){
        this.appCredential = appCredential;
        this.serviceResponse.getResponseStat().setIsLogin(true);
        httpSession.setAttribute("appCredential", appCredential);
    }

}