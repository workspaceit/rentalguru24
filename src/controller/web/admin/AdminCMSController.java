package controller.web.admin;

import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView getCmsPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cmsPage");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Admin CMS Page");
        return  modelAndView;
    }
}
