package controller.interceptor;

/**
 * Created by mi on 8/16/16.
 */

import helper.ServiceResponse;
import model.CategoryModel;
import model.admin.AdminCmsPageModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebInterceptor extends BaseWebInterceptor{

    @Autowired
    CategoryModel categoryModel;
    @Autowired
    AdminCmsPageModel adminCmsPageModel;
    private String baseURL;
    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {


        ServiceResponse serviceResponse = new ServiceResponse();
        AppCredential appCredential =  new AppCredential();
        ServletRequestAttributes ar = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = request.getSession();

        this.baseURL = this.getURLWithContextPath(request);

        if(httpSession.getAttribute("appCredential") instanceof AppCredential){
            serviceResponse.getResponseStat().setIsLogin(true);
            appCredential =(AppCredential) httpSession.getAttribute("appCredential");
        }
        Boolean isLogin = serviceResponse.getResponseStat().getIsLogin();
        request.setAttribute("isLogin",isLogin);
        request.setAttribute("preSelectedCategoryName","Select a category");
        request.setAttribute("category", categoryModel.getAll());
        request.setAttribute("cmsPages",adminCmsPageModel.getAll());
        request.setAttribute("BaseUrl", this.baseURL);
        request.setAttribute("serviceResponse", serviceResponse);
        request.setAttribute("appCredential", appCredential);
        request.setAttribute("stateList", stateList);
        return true;

    }

    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

    }
    public String getBaseURL() {
        return baseURL;
    }

    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
