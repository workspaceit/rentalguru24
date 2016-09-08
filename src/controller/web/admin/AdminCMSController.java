package controller.web.admin;

import model.admin.AdminCmsPageModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 9/8/16.
 */
@Controller
@RequestMapping("/admin/cms")
public class AdminCMSController {
    @Autowired
    AdminCmsPageModel adminCmsPageModel;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView getCmsPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cmsPage");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin CMS Page");
        return  modelAndView;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ModelAndView getAllCmsPages(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cmsPagesList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
        String baseUrl = (String) request.getAttribute("baseURL");

        modelAndView.addObject("cmsPages", adminCmsPageModel.getAll());
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "CMS pages");
        return modelAndView;
    }

    @RequestMapping(value = "/static/{PageKey}", method = RequestMethod.GET)
    public ModelAndView getCmsEditPage(HttpServletRequest request, @PathVariable("PageKey") String PageKey){
        ModelAndView modelAndView = new ModelAndView("admin/cmsEditPage");
        AppCredential appCredential = (AppCredential) request.getAttribute("appcrediential");
        String baseUrl = (String) request.getAttribute("baseURL");

        modelAndView.addObject("cmsPages", adminCmsPageModel.getByPageKey(PageKey));
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "CMS Pages Edit");
        return modelAndView;
    }
}
