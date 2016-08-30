package controller.web.app;

import helper.ServiceResponse;
import model.CategoryModel;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 8/30/16.
 */
@Controller
@RequestMapping("/page-not-found")
public class Page404Controller {
    @Autowired
    CategoryModel categoryModel;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("public/404");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
//        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = categoryModel.getAll();
//        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("pageTitle", "404 Page Not Found");
        modelAndView.addObject("category", category);
        return modelAndView;
    }
}
