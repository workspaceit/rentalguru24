package controller;

import helper.ServiceResponse;
import model.CategoryModel;
import model.entity.app.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 10/18/16.
 */
@ControllerAdvice
public class MyExceptionController {
    @Autowired
    CategoryModel categoryModel;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView index(HttpServletRequest request, Exception e){
        ModelAndView modelAndView = new ModelAndView("public/404");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
//        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = categoryModel.getAll();
//        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("pageTitle", "404 Page Not Found");
        modelAndView.addObject("category", category);
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
