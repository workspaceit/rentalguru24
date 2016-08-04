package controller;

import helper.ServiceResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mi on 8/1/16.
 */

public class BaseHttp {

    public ServiceResponse serviceResponse;
    private HttpServletRequest httpServletRequest;
    public BaseHttp() {
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        httpServletRequest = ar.getRequest();
        HttpSession httpSession = httpServletRequest.getSession();
        System.out.println("Session Value" + httpSession.getAttribute("asd"));
        httpSession.setAttribute("asd","asdasdas");
    }
}