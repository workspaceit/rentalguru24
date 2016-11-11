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
import java.util.HashMap;

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
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("CMS", new String("javascript:void(0);"));
        breadcrumb.put("Create New CMS Page", new String(baseUrl+"/admin/cms/page"));

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Create New CMS Page");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return  modelAndView;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ModelAndView getAllCmsPages(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cmsPagesList");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("CMS", new String("javascript:void(0);"));
        breadcrumb.put("CMS Page List", new String(baseUrl+"/admin/cms/get-all"));

        modelAndView.addObject("cmsPages", adminCmsPageModel.getAll());
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);;
        modelAndView.addObject("pageHeader", "CMS Page List");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }

    @RequestMapping(value = "/static/{PageKey}", method = RequestMethod.GET)
    public ModelAndView getCmsEditPage(HttpServletRequest request, @PathVariable("PageKey") String PageKey){
        ModelAndView modelAndView = new ModelAndView("admin/cmsEditPage");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        HashMap<String, String> breadcrumb = new HashMap<>();

        breadcrumb.put("CMS", new String("javascript:void(0);"));
        breadcrumb.put("CMS Page List", new String(baseUrl+"/admin/cms/get-all"));
        breadcrumb.put("Edit "+PageKey+" Page", new String(baseUrl+"admin/cms/static/"+PageKey));


        modelAndView.addObject("cmsPages", adminCmsPageModel.getByPageKey(PageKey));
        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageHeader", "Edit "+PageKey+" Page");
        modelAndView.addObject("breadcrumb", breadcrumb);
        return modelAndView;
    }
}
