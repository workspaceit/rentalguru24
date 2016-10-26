package controller.web.app;


import helper.ServiceResponse;
import helper.UrlHelper;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    CategoryModel categoryModel;

    @Autowired
    ProductModel productModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "cid",required = false)Integer categoryId,
                              @RequestParam(value = "radius",required = false)Float radius,
                              @RequestParam(value = "lat",required = false)Double lat,
                              @RequestParam(value = "lng",required = false)Double lng) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("baseURL");

        ModelAndView modelAndView = new ModelAndView("public/Search");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<RentalProduct> rentalProducts = new ArrayList<>();

        try {
            if(title!=null && !title.equals(""))
                title = URLDecoder.decode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            serviceResponse.setRequestError("title","title is not in valid format");
        }
        if(radius!=null){
            if(lat==null || lng==null){
                GeoIp geoIp = new GeoIpManager().getGeoIp(request);
                if(geoIp!=null){
                    lat = geoIp.getLatitude();
                    lng = geoIp.getLongitude();
                }
            }
        }

        rentalProducts = productModel.getRentalProductForSearch(categoryId,title,lat,lng,radius,8,0);


        System.out.println("GeoIpManager "+GeoIpManager.getRemoteAddress(request));

        String productListTitle = "Search result";

        String preSelectedCategoryName = "Select Category";


        /* Need to check of null pointer exception on */
//        Optional<Category> tempCat = category.stream().filter(c->c.getId()==categoryId).findFirst();
//        Category selectedCategory = (tempCat!=null)?tempCat.get():null;

            /* Getting from Database Fix the above code */

        Category selectedCategory = null;

        if(categoryId!=null){
             selectedCategory = categoryModel.getById(categoryId);
        }
        if(selectedCategory!=null){
            preSelectedCategoryName = selectedCategory.getName();
        }

        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product"+ UrlHelper.getLoadMoreUrlParams(title,categoryId,lat,lng,radius);

        modelAndView.addObject("selectedCategoryId", categoryId);
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
        modelAndView.addObject("category", category);
        modelAndView.addObject("productListTitle",productListTitle);
        modelAndView.addObject("rentalProducts", rentalProducts);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",baseUrl);
        return modelAndView;
    }

}