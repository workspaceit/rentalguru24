package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor extends HandlerInterceptorAdapter{
    private String baseURL;

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {


        ServiceResponse serviceResponse = new ServiceResponse();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = request.getSession();


        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            AppCredential appCredential =(AppCredential)httpSession.getAttribute("appCredential");
            if(appCredential.getRole() ==1){
                request.setAttribute("serviceResponse", serviceResponse);
                request.setAttribute("appCredential", httpSession.getAttribute("appCredential"));
                serviceResponse.getResponseStat().setIsLogin(true);

                this.baseURL = this.getURLWithContextPath(request);

                request.setAttribute("baseURL", this.baseURL);
            }

            return true;
        }else{
            serviceResponse.getResponseStat().setErrorMsg("Session expired !!!!");
            response.setContentType("application/json");
            response.sendRedirect(this.getURLWithContextPath(request)+"/admin-login");
            return false;
        }
    }

    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {


        System.out.println("INTERCEPTOR postHandle");
        //log it
    }
    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
