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
        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("baseURL");

        ModelAndView modelAndView = new ModelAndView("public/Home");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<RentalProduct> rentalProducts = productModel.getRentalProduct(8, 0);
        List<RentalProduct> rentalProductsTop = productModel.getRentalProductOrderByRating(3, 0);
        if(rentalProductsTop==null || rentalProductsTop.size()==0){
            rentalProductsTop = productModel.getRentalProduct(3, 0);
        }
        List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);
        RentalProduct rentalProductsRandom1 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom2 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom3 = productModel.getRentalProductRandom();
        RentalProduct rentalProductsRandom4 = productModel.getRentalProductRandom();

        modelAndView.addObject("category", category);
        modelAndView.addObject("productListTitle","New product");
        modelAndView.addObject("products", rentalProducts);
        modelAndView.addObject("productsAscending", rentalProductsAscending);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("rentalProductsTop",rentalProductsTop);
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
        List<Category> category = (List<Category>) request.getAttribute("category");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        ModelAndView modelAndView = new ModelAndView("public/Home");
        Category categorySelected = categoryModel.getById(categoryId);
        if(categorySelected!=null)
            modelAndView.addObject("productListTitle",categorySelected.getName());
        else
            modelAndView.addObject("productListTitle","");

        List<RentalProduct> rentalProducts = productModel.getProductByCategoryId(categoryId,8,1);
        if(rentalProducts != null){
            List<RentalProduct> rentalProductsTop = productModel.getRentalProductOrderByRating(3, 0);
            if(rentalProductsTop==null || rentalProductsTop.size()==0){
                rentalProductsTop = productModel.getRentalProduct(3, 0);
            }
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
            modelAndView.addObject("rentalProductsTop",rentalProductsTop);
            modelAndView.addObject("rentalProductsRandom1",rentalProductsRandom1);
            modelAndView.addObject("rentalProductsRandom2",rentalProductsRandom2);
            modelAndView.addObject("rentalProductsRandom3",rentalProductsRandom3);
            modelAndView.addObject("rentalProductsRandom4",rentalProductsRandom4);
            modelAndView.addObject("pageTitle", "RentGuru");
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/partial-rendering/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getCategory(HttpServletRequest request,@PathVariable("categoryId") int categoryId){
        Category categorySelected = categoryModel.getById(categoryId);

        String baseUrl = (String) request.getAttribute("baseURL");
        ModelAndView modelAndView = new ModelAndView("public/partial_rendering_new_product");
        if(categorySelected!=null)
            modelAndView.addObject("productListTitle",categorySelected.getName());
        else
            modelAndView.addObject("productListTitle","");

        List rentalProduct = productModel.getProductByCategoryId(categoryId);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("products",rentalProduct);
        return modelAndView;
    }
}
