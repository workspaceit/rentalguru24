package controller.web.app;


import helper.ServiceResponse;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
import model.BannerImageModel;
import model.CategoryModel;
import model.ProductModel;
import model.StateModel;
import model.entity.BannerImage;
import model.entity.State;
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


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/home")
public class HomeController {
    private static List<State> stateList = new ArrayList<>();

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    ProductModel productModel;

    @Autowired
    BannerImageModel bannerImageModel;

    @Autowired
    StateModel stateModel;



    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        System.out.println(stateList);
        ModelAndView modelAndView = new ModelAndView("public/Home");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<RentalProduct> rentalProducts = new ArrayList<>();


        rentalProducts = productModel.getRentalProduct(12, 0);
        List<RentalProduct> rentalProductsTop = productModel.getOnlyRatedRentalProductOrderByRating(3, 0);
        String topRentalProductHeadTitle = "MOST RATED";
        if(rentalProductsTop==null || rentalProductsTop.size()==0){
            rentalProductsTop = productModel.getRentalProduct(3, 0);
            topRentalProductHeadTitle = "Latest Product";
        }

        String productListTitle = "Latest Product";


        List<BannerImage> bannerImageList = bannerImageModel.getAll();

        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product?fromHomePage=1";
        String preSelectedCategoryName = "Select Category";
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("bannerImageList", bannerImageList);
        modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
        modelAndView.addObject("topRentalProductHeadTitle", topRentalProductHeadTitle);
        modelAndView.addObject("productListTitle",productListTitle);
        modelAndView.addObject("rentalProducts", rentalProducts);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("rentalProductsTop",rentalProductsTop);
        return modelAndView;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getProductByCategoryId(HttpServletRequest request,
                                               @PathVariable("categoryId") int categoryId,
                                               @RequestParam(value = "title", required = false)final String title,
                                               @RequestParam(value = "distance",required = false)Integer distance,
                                               @RequestParam(value = "lat",required = false)Double centralLatitude,
                                               @RequestParam(value = "lng",required = false)Double centralLongitude){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<Category> category = (List<Category>) request.getAttribute("category");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();


        ModelAndView modelAndView = new ModelAndView("public/CategoryWiseProduct");
        Category categorySelected = categoryModel.getById(categoryId);
        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product?cid="+categoryId;
        String preSelectedcategoryName = "Select a category";
        String selectedCategoryName = "";

        if(categorySelected==null){
            return new ModelAndView("redirect:/home");
        }


        preSelectedcategoryName = categorySelected.getName();
        selectedCategoryName = categorySelected.getName();

        List<RentalProduct> rentalProducts = productModel.getRentalProductByCategory(categorySelected, 8, 0);

        if(rentalProducts == null) {
            return new ModelAndView("redirect:/home");
        }
        List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);

        if(categorySelected.getIsSubcategory()){
            Category categoryParent = categoryModel.getParentCategory(categoryId);
            Category categorySelectedParent = categoryModel.getById(categoryParent.getId());
            modelAndView.addObject("categorySelected", categorySelectedParent);
        }else{
            modelAndView.addObject("categorySelected", categorySelected);
        }

        modelAndView.addObject("selectedCategoryId", categoryId);
        modelAndView.addObject("productListTitle",selectedCategoryName);
        modelAndView.addObject("preSelectedCategoryName",preSelectedcategoryName );
        modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
        modelAndView.addObject("category", category);
        modelAndView.addObject("rentalProducts", rentalProducts);
        modelAndView.addObject("productsAscending", rentalProductsAscending);
        return modelAndView;

    }

    @RequestMapping(value = "/partial-rendering/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getCategory(HttpServletRequest request,@PathVariable("categoryId") int categoryId){
        Category categorySelected = categoryModel.getById(categoryId);

        ModelAndView modelAndView = new ModelAndView("public/partial/partial_rendering_new_product");
        if(categorySelected!=null)
            modelAndView.addObject("productListTitle",categorySelected.getName());
        else
            modelAndView.addObject("productListTitle","");

        List rentalProduct = productModel.getRentalProductByCategoryId(categoryId, 8, 0);
        modelAndView.addObject("rentalProducts",rentalProduct);
        return modelAndView;
    }

    @RequestMapping(value = "/partial-rendering/load/more/rental-product", method = RequestMethod.GET)
    public ModelAndView getMoreRentalProduct(HttpServletRequest request,
                                             @RequestParam(value = "fromHomePage",required = false)Boolean fromHomePage,
                                             @RequestParam(value = "stateId",required = false)Integer stateId,
                                             @RequestParam(value = "title",required = false)String title,
                                             @RequestParam(value = "cid",required = false)Integer categoryId,
                                             @RequestParam(value = "radius",required = false)Float radius,
                                             @RequestParam(value = "lat",required = false)Double lat,
                                             @RequestParam(value = "lng",required = false)Double lng,
                                             @RequestParam(value = "limit", required = true)int limit,
                                             @RequestParam(value = "offset", required = true)int offset) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> categoryList = (List<Category>) request.getAttribute("category");


        ModelAndView modelAndView = new ModelAndView("public/partial/load_more_product");
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
        Category searchedCategory = (categoryId!=null)?categoryList.stream().
                                        filter(category -> category.getId() == categoryId)
                                        .findFirst()
                                        .orElse(null):null;
//        if(categoryId!=null){
//            searchedCategory = categoryModel.getById(categoryId);
//        }
        State selectedUsState = null;
        if(selectedUsState!=null){
            selectedUsState = stateModel.getById(stateId);
        }
        if(fromHomePage!=null && fromHomePage){
            rentalProducts =  productModel.getRentalProduct(limit, offset);
        }else{
            rentalProducts = productModel.getRentalProductForSearch(selectedUsState,searchedCategory,title,lat,lng,radius,limit,offset);
        }

        modelAndView.addObject("rentalProducts",rentalProducts);
        return modelAndView;
    }


}
