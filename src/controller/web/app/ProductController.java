package controller.web.app;

import controller.BaseHttp;
import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.RentTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.RentType;
import model.entity.app.product.ProductCategory;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        ModelAndView modelAndView = new ModelAndView("/product/upload");
        List<RentType> rentTypes = rentTypeModel.getAll();

        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("category", category);
        modelAndView.addObject("rentTypes", rentTypes);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("pageTitle", "Product Upload");
        return modelAndView;
    }

    @RequestMapping(value = "/details/{product_id}", method = RequestMethod.GET)
    public String details(HttpServletRequest request,@PathVariable("product_id") int productId, Model model){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("baseURL");
        System.out.println("baseUrl "+baseUrl);
        RentalProduct rentalProduct = productModel.getById(productId);
        List<RentalProduct> newProducts = productModel.getRentalProduct(4, 0, productId);
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<ProductCategory> productCategories = rentalProduct.getProductCategories();
        List<Map<String,String>> breadCrumbStr = new ArrayList<>();

        if(productCategories!=null){
            if(productCategories.size()>0){
                List<Category> parentCategories =  categoryModel.getAllParentBySubcategoryId(productCategories.get(0).getId());

                Map<String,String> breadCrumbDetails = new HashMap<>();
                breadCrumbDetails.put("url", baseUrl + "/home/category/" + productCategories.get(0).getCategory().getId());
                breadCrumbDetails.put("text", productCategories.get(0).getCategory().getName());
                breadCrumbStr.add(breadCrumbDetails);


                Map<String,String> tempBreadCrumbDetails = new HashMap<>();

                for(Category c : parentCategories) {
                    tempBreadCrumbDetails.put("url",baseUrl+"/home/category/"+c.getId());
                    tempBreadCrumbDetails.put("text",c.getName());
                    breadCrumbStr.add(tempBreadCrumbDetails);
                }
            }
        }

        Map<String,String> productBreadCrumbDetails = new HashMap<>();
        productBreadCrumbDetails.put("url", baseUrl + "/product/details/" + rentalProduct.getId());
        productBreadCrumbDetails.put("text", "Product details");
        breadCrumbStr.add(productBreadCrumbDetails);

        model.addAttribute("breadCrumbStr", breadCrumbStr);
        model.addAttribute("IsLogIn", IsLogin);
        model.addAttribute("rentalProduct", rentalProduct);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("BaseUrl",baseUrl);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Product Details");
        return "product/details";
    }

}


