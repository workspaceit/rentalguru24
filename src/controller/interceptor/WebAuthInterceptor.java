package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

public class WebAuthInterceptor extends HandlerInterceptorAdapter{


    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {


        ServiceResponse serviceResponse = new ServiceResponse();
        AppCredential appCredential = null;// new AppCredential();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = request.getSession();


        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            try{

                appCredential = (AppCredential)httpSession.getAttribute("appCredential");
            }catch (Exception ex){
                ex.printStackTrace();
            }
            serviceResponse.getResponseStat().setIsLogin(true);
        }
        request.setAttribute("serviceResponse",serviceResponse);


        System.out.println("INTERCEPTOR preHandle");
//        PrintWriter pw = response.getWriter();
//        pw.print("{sd:\"sd\"}");
//        pw.close();
        return true;
    }

    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {


        System.out.println("INTERCEPTOR postHandle");
        //log it
    }
}
