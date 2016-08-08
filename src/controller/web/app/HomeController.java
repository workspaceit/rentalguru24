package controller.web.app;

import model.CategoryModel;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Model;
import java.util.List;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    CategoryModel categoryModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/Home");
        List<Category> category = categoryModel.getAll();
        modelAndView.addObject("category", category);
        return modelAndView;
    }
}
