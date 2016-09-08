package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.CategoryModel;
import model.admin.AdminCmsPageModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

public class WebAuthInterceptor extends HandlerInterceptorAdapter{
    private String baseURL;
    @Autowired
    CategoryModel categoryModel;

    @Autowired
    AdminCmsPageModel adminCmsPageModel;
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
            request.setAttribute("appUserVerification",(Boolean) SessionManagement.getAppUserVerification(request));
            serviceResponse.getResponseStat().setIsLogin(true);

            this.baseURL = this.getURLWithContextPath(request);

            request.setAttribute("cmsPages",adminCmsPageModel.getAll());
            request.setAttribute("category",categoryModel.getAll());
            request.setAttribute("baseURL", this.baseURL);
            return true;
        }else{
            serviceResponse.getResponseStat().setErrorMsg("Session expired !!!!");
            response.setContentType("application/json");

            response.sendRedirect(this.getURLWithContextPath(request) + "/signin?r=" + java.net.URLEncoder.encode(this.getURL(request), "UTF-8"));
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

    private static String getURL(HttpServletRequest req) {

        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();          // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
