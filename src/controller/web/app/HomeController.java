package controller.web.app;

import controller.BaseHttp;
import model.CategoryModel;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/home")
@Scope("request")
public class HomeController extends BaseHttp {
    @Autowired
    CategoryModel categoryModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/Home");
        List<Category> category = categoryModel.getAll();
        modelAndView.addObject("category", category);
        modelAndView.addObject("BaseUrl",this.getBaseURL());
        return modelAndView;
    }
}
