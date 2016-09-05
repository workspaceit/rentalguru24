package controller.web.admin;


import model.ProductModel;

import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * Created by Tomal on 8/24/2016.
 */
@Controller
@RequestMapping("/admin/product")
public class AdminRentalProductController {

    @Autowired
    ProductModel productModel;


    @RequestMapping(value = "/get-all-rental-product",method = RequestMethod.GET)
    public ModelAndView loadAllRentalProduct(HttpServletRequest request){
        ModelAndView modelAndView =new ModelAndView("admin/rentalProductsDetails");
        String baseUrl = (String) request.getAttribute("baseURL");
        List <RentalProductEntity>rentalProductEntities=productModel.getRentalProduct();
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        modelAndView.addObject("adminUser", appCredential);
        modelAndView.addObject("rentalProducts", rentalProductEntities);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("PageTitle", "Rental Product Details");

        return modelAndView;

    }


}
