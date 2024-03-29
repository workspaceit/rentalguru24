package controller.web.app;

import controller.BaseHttp;
import helper.ServiceResponse;
import model.*;
import model.entity.Cities;
import model.entity.State;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.ProductRating;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    ProductRatingModel productRatingModel;

    @Autowired
    StateModel stateModel;

    @Autowired
    CitiesModel citiesModel;

    @RequestMapping(value="/upload",method = RequestMethod.GET)
    public ModelAndView upload(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");
        List<State> states = stateModel.getAll();

        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        ModelAndView modelAndView = new ModelAndView("/product/upload");
        List<RentType> rentTypes = rentTypeModel.getAll();

        String preSelectedCategoryName = "Browse By Category";
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("category", category);
        modelAndView.addObject("states", states);
        modelAndView.addObject("rentTypes", rentTypes);
        modelAndView.addObject("IsLogIn", IsLogin);
        return modelAndView;
    }

    @RequestMapping(value = "/details/{product_id}", method = RequestMethod.GET)
    public ModelAndView details(HttpServletRequest request,@PathVariable("product_id") int productId){
        ModelAndView modelAndView = new ModelAndView("product/details");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("BaseUrl");
        RentalProduct rentalProduct = productModel.getById(productId);

        if(rentalProduct == null){
            return new ModelAndView("redirect:/home");
        }

        List<RentalProduct> newProducts = productModel.getRentalProduct(4, 0, productId);
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<ProductCategory> productCategories = rentalProduct.getProductCategories();
        List<Map<String,String>> breadCrumbStr = new ArrayList<>();


        if(productCategories!=null){
            if(productCategories.size()>0){
                List<Category> parentCategories =  categoryModel.getAllParentBySubcategoryId(productCategories.get(0).getCategory().getId());
                /* Parent category added first if any */
                for(Category c : parentCategories) {
                    Map<String,String> tempBreadCrumbDetails = new HashMap<>();
                    tempBreadCrumbDetails.put("url",baseUrl+"/home/category/"+c.getId());
                    tempBreadCrumbDetails.put("text",c.getName());
                    breadCrumbStr.add(tempBreadCrumbDetails);
                }
                /* Category of product */
                Map<String,String> breadCrumbDetails = new HashMap<>();
                breadCrumbDetails.put("url", baseUrl + "/home/category/" + productCategories.get(0).getCategory().getId());
                breadCrumbDetails.put("text", productCategories.get(0).getCategory().getName());
                breadCrumbStr.add(breadCrumbDetails);
            }
        }

        Map<String,String> productBreadCrumbDetails = new HashMap<>();

        productBreadCrumbDetails.put("url", "javascript:void(0)");
        productBreadCrumbDetails.put("text", "Details");
        breadCrumbStr.add(productBreadCrumbDetails);

        String preSelectedCategoryName = "Browse By Category";
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("breadCrumbStr", breadCrumbStr);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("rentalProduct", rentalProduct);
        modelAndView.addObject("newProducts", newProducts);
        modelAndView.addObject("category", category);
        modelAndView.addObject("pageTitle", rentalProduct.getName());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{product_id}", method = RequestMethod.GET)
    public ModelAndView getEditProduct(HttpServletRequest request, @PathVariable("product_id") int productId){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = (List<Category>) request.getAttribute("category");
        List<State> states = stateModel.getAll();
        RentalProduct rentalProduct = productModel.getMyRentalProductById(productId, appCredential.getId());
        if(rentalProduct == null){
            return new ModelAndView("redirect:/home");
        }

        List<RentType> rentTypes = rentTypeModel.getAll();

        /*------------------------------------------*/
        Category categorySelected = categoryModel.getById(rentalProduct.getProductCategories().get(0).getCategory().getId());

        if(categorySelected.getIsSubcategory() == true){
            Category categoryParent = categoryModel.getParentCategory(rentalProduct.getProductCategories().get(0).getCategory().getId());
            modelAndView.addObject("categoryId", categoryParent.getId());
            modelAndView.addObject("subCategoryId", rentalProduct.getProductCategories().get(0).getCategory().getId());
        }else {
            modelAndView.addObject("categoryId", categorySelected.getId());
        }
        /*------------------------------------------*/
        /*------------------------------------------*/
        RentType rentTypeSelected = rentTypeModel.getById(rentalProduct.getRentType().getId());
        if(rentTypeSelected != null){
            modelAndView.addObject("rentTypeSelectedId", rentTypeSelected.getId());
        }

        /*------------------------------------------*/
        /*------------------------------------------*/

        State stateSelected = stateModel.getById(rentalProduct.getProductLocation().getState().getId());

        /*------------------------------------------*/
        String preSelectedCategoryName = "Browse By Category";

        modelAndView.addObject("stateSelected", stateSelected);
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("rentTypes", rentTypes);
        modelAndView.addObject("category", category);
        modelAndView.addObject("rentalProduct", rentalProduct);
        modelAndView.addObject("states", states);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("appCredential", appCredential);
        return modelAndView;
    }
    @RequestMapping(value = "/other-image/partial-load/{product_id}", method = RequestMethod.GET)
    public ModelAndView getproductOtherImagePartialLoad(HttpServletRequest request, @PathVariable("product_id") int productId){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/productOtherImagePartialLoad");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentalProduct rentalProduct = productModel.getMyRentalProductById(productId, appCredential.getId());
        modelAndView.addObject("rentalProduct", rentalProduct);
        return modelAndView;
    }
    @RequestMapping(value = "/review/partial-load/{product_id}", method = RequestMethod.GET)
    public ModelAndView getProductReviewPartialLoad(HttpServletRequest request, @PathVariable("product_id") int productId){
        ModelAndView modelAndView = new ModelAndView("public/partial/productReviewPartialLoad");
        List<ProductRating> productRatingList = productRatingModel.getByProductId(productId);
//        if(productRatingList == null||productRatingList.isEmpty()){
//            return modelAndView;
//        }
        modelAndView.addObject("productRatingList", productRatingList);
        return modelAndView;
    }
    @RequestMapping(value = "/product-details/partial-load/{product_id}", method = RequestMethod.GET)
    public ModelAndView getProductDetailsPartialLoadModel(HttpServletRequest request, @PathVariable("product_id") int productId){
        ModelAndView modelAndView = new ModelAndView("public/partial/productDetailsModalView");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentalProduct rentalProduct = productModel.getById(productId);

        modelAndView.addObject("rentalProduct", rentalProduct);
        return modelAndView;
    }
}


