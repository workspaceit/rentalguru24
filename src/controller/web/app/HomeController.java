package controller.web.app;

import controller.BaseHttp;
import model.CategoryModel;
import model.ProductModel;
import model.entity.app.Category;
import model.entity.app.product.rentable.iface.RentalProduct;
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

    @Autowired
    ProductModel productModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("public/Home");
        Boolean IsLogin = this.serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = categoryModel.getAll();
        List<RentalProduct> rentalProducts = productModel.getRentalProduct(8, 0);

        modelAndView.addObject("category", category);
        modelAndView.addObject("products", rentalProducts);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",this.getBaseURL());
        return modelAndView;
    }
}
