package controller.web.app;

import controller.BaseHttp;
import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.RentTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.RentType;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 8/16/16.
 */

@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    ProductModel productModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    RentTypeModel rentTypeModel;

    @RequestMapping(value="/upload",method = RequestMethod.GET)
    public ModelAndView upload(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        ModelAndView modelAndView = new ModelAndView("/product/upload");
        List<Category> category = categoryModel.getAllCategoryParent();
        List<RentType> rentTypes = rentTypeModel.getAll();

        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("category", category);
        modelAndView.addObject("rentTypes", rentTypes);
        modelAndView.addObject("IsLogIn", IsLogin);
        return modelAndView;
    }

    @RequestMapping(value = "/details/{product_id}", method = RequestMethod.GET)
    public String details(HttpServletRequest request,@PathVariable("product_id") int productId, Model model){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        System.out.println("baseUrl "+baseUrl);
        RentalProduct rentalProduct = productModel.getById(productId);
        List<RentalProduct> newProducts = productModel.getRentalProduct(4, 0, productId);
        model.addAttribute("rentalProduct", rentalProduct);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("BaseUrl",baseUrl);
        return "product/details";
    }

}


