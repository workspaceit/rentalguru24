package controller.web.app;


import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    CategoryModel categoryModel;

    @Autowired
    ProductModel productModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");

        ModelAndView modelAndView = new ModelAndView("public/Home");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = categoryModel.getAll();
        List<RentalProduct> rentalProducts = productModel.getRentalProduct(8, 0);
        List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);
        RentalProduct rentalProductsRandom1 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom2 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom3 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom4 = productModel.getRentalProductRandom();

        modelAndView.addObject("category", category);
        modelAndView.addObject("products", rentalProducts);
        modelAndView.addObject("productsAscending", rentalProductsAscending);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("rentalProductsRandom1",rentalProductsRandom1);
        modelAndView.addObject("rentalProductsRandom2",rentalProductsRandom2);
        modelAndView.addObject("rentalProductsRandom3",rentalProductsRandom3);
        modelAndView.addObject("rentalProductsRandom4",rentalProductsRandom4);
        modelAndView.addObject("pageTitle", "Reneguru");
        return modelAndView;
    }

    @RequestMapping(value = "/category/{category_id}", method = RequestMethod.GET)
    public ModelAndView getProductByCategoryId(HttpServletRequest request, @PathVariable("category_id") int categoryId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        ModelAndView modelAndView = new ModelAndView("public/product_by_category");
        List<RentalProduct> rentalProducts = productModel.getProductByCategoryId(categoryId);

        List<Category> category = categoryModel.getAll();
        List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);
        RentalProduct rentalProductsRandom1 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom2 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom3 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom4 = productModel.getRentalProductRandom();


        modelAndView.addObject("products",rentalProducts);
        modelAndView.addObject("productsAscending", rentalProductsAscending);
        modelAndView.addObject("rentalProductsRandom1",rentalProductsRandom1);
        modelAndView.addObject("rentalProductsRandom2",rentalProductsRandom2);
        modelAndView.addObject("rentalProductsRandom3",rentalProductsRandom3);
        modelAndView.addObject("rentalProductsRandom4",rentalProductsRandom4);
        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("pageTitle", "Product By Category");
        return modelAndView;
    }


}
