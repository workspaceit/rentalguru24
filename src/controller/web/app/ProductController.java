package controller.web.app;

import controller.BaseHttp;
import model.CategoryModel;
import model.ProductModel;
import model.entity.app.Category;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import java.util.List;

/**
 * Created by omar on 8/16/16.
 */

@Controller
@RequestMapping("/product")
@Scope("request")
public class ProductController extends BaseHttp{
    @Autowired
    ProductModel productModel;

    @Autowired
    CategoryModel categoryModel;

    @RequestMapping(value="/upload",method = RequestMethod.GET)
    public ModelAndView upload(){
        ModelAndView modelAndView = new ModelAndView("public/product/upload");
        List<Category> category = categoryModel.getAllCategoryParent();
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @RequestMapping(value = "/details/{product_id}", method = RequestMethod.GET)
    public String details(@PathVariable("product_id") int productId, Model model){
        RentalProduct rentalProduct = productModel.getById(productId);
        List<RentalProduct> newProducts = productModel.getRentalProduct(4, 0);
        model.addAttribute("rentedProduct", rentalProduct);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("BaseUrl",this.getBaseURL());
        return "product/details";
    }

}
