package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.ServiceResponse;
import model.entity.app.AppCredential;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ServiceAuthInterceptor extends HandlerInterceptorAdapter{


    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {


        ServiceResponse serviceResponse = new ServiceResponse();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = request.getSession();


        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            request.setAttribute("serviceResponse", serviceResponse);
            request.setAttribute("appCredential", httpSession.getAttribute("appCredential"));
            return true;
        }else{
            serviceResponse.getResponseStat().setErrorMsg("Session expired !!!!");
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            pw.print(objectMapper.writeValueAsString(serviceResponse));
            System.out.println("INTERCEPTOR postHandle");
            pw.close();
            return false;
        }

    }

    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {


        System.out.println("INTERCEPTOR postHandle");

    }
}
