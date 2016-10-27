package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebNonAuthInterceptor extends HandlerInterceptorAdapter{
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
            response.sendRedirect(this.getURLWithContextPath(request) + "/home");
            return false;
        }else{
            request.setAttribute("serviceResponse", serviceResponse);
            request.setAttribute("appCredential", httpSession.getAttribute("appCredential"));

            this.baseURL = this.getURLWithContextPath(request);

            Boolean isLogin = serviceResponse.getResponseStat().getIsLogin();
            request.setAttribute("isLogin", isLogin);
            request.setAttribute("preSelectedCategoryName","Select a category");
            request.setAttribute("cmsPages",adminCmsPageModel.getAll());
            request.setAttribute("category",categoryModel.getAll());
            request.setAttribute("BaseUrl", this.baseURL);
            return true;
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
