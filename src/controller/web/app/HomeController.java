package controller.web.app;


import helper.ServiceResponse;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
import model.BannerImageModel;
import model.CategoryModel;
import model.ProductModel;
import model.entity.BannerImage;
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
import java.util.ArrayList;
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

    @Autowired
    BannerImageModel bannerImageModel;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "distance",required = false)Integer distance,
                              @RequestParam(value = "lat",required = false)Double centralLatitude,
                              @RequestParam(value = "lng",required = false)Double centralLongitude) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        String baseUrl = (String) request.getAttribute("baseURL");

        ModelAndView modelAndView = new ModelAndView("public/Home");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<RentalProduct> rentalProducts = new ArrayList<>();
        boolean showTopGallery = (title!=null&&title!="")?true:false;

         if(title!=null&&title!=""){
            if(distance!=null&&distance>0){
                GeoIp geoIp = new GeoIp();
                if(centralLatitude==null || centralLongitude ==null ){
                    GeoIpManager geoIpManager = new GeoIpManager();
                    geoIp =geoIpManager.getGeoIp(request);

                    centralLatitude = geoIp.getLatitude();
                    centralLongitude = geoIp.getLongitude();

                    System.out.println("From geoIp ");
                }

                if(geoIp!=null){
                    rentalProducts = productModel.getRentalProductByDistance(title,centralLatitude,centralLongitude,distance,8,0);
                }else{
                    rentalProducts = productModel.getRentalProductByTitle(title,8, 0);
                    System.out.println(geoIp);
                }

            }else{
                 rentalProducts = productModel.getRentalProductByTitle(title,8, 0);
                System.out.println("geoIp is null");
             }
         }else{
             rentalProducts = productModel.getRentalProduct(8, 0);
         }


        List<RentalProduct> rentalProductsTop = productModel.getOnlyRatedRentalProductOrderByRating(3, 0);
        String topRentalProductHeadTitle = "MOST RATED";
        if(rentalProductsTop==null || rentalProductsTop.size()==0){
            rentalProductsTop = productModel.getRentalProduct(3, 0);
            topRentalProductHeadTitle = "NEW PRODUCT";
        }
        System.out.println("GeoIpManager "+GeoIpManager.getRemoteAddress(request));
        String productListTitle = "NEW PRODUCT";
        if(showTopGallery){
            productListTitle = "Search result";

        }
//        List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);
//        RentalProduct rentalProductsRandom1 = productModel.getRentalProductRandom();
//        RentalProduct rentalProductsRandom2 = productModel.getRentalProductRandom();
//        RentalProduct rentalProductsRandom3 = productModel.getRentalProductRandom();
//        RentalProduct rentalProductsRandom4 = productModel.getRentalProductRandom();

        List<BannerImage> bannerImageList = bannerImageModel.getAll();

        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product?";
        loadMoreProductUrl = (title!=null&&!title.equals(""))?loadMoreProductUrl+"title="+title:loadMoreProductUrl;
        String preSelectedCategoryName = "Select Category";
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("bannerImageList", bannerImageList);
        modelAndView.addObject("showTopGallery", showTopGallery);
        modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
        modelAndView.addObject("category", category);
        modelAndView.addObject("topRentalProductHeadTitle", topRentalProductHeadTitle);
        modelAndView.addObject("productListTitle",productListTitle);
        modelAndView.addObject("rentalProducts", rentalProducts);
     //   modelAndView.addObject("productsAscending", rentalProductsAscending);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("rentalProductsTop",rentalProductsTop);
//        modelAndView.addObject("rentalProductsRandom1",rentalProductsRandom1);
//        modelAndView.addObject("rentalProductsRandom2",rentalProductsRandom2);
//        modelAndView.addObject("rentalProductsRandom3",rentalProductsRandom3);
//        modelAndView.addObject("rentalProductsRandom4",rentalProductsRandom4);
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
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");
        String baseUrl = (String) request.getAttribute("baseURL");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        boolean showTopGallery = true;
        boolean clientFeedback = true;

        ModelAndView modelAndView = new ModelAndView("public/Home");
        Category categorySelected = categoryModel.getById(categoryId);
        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product?categoryId="+categoryId;
        String preSelectedcategoryName = "Select a category";
        String selectedCategoryName = "";
        if(categorySelected!=null){
            preSelectedcategoryName = categorySelected.getName();
            selectedCategoryName = categorySelected.getName();
        }



        List<RentalProduct> rentalProducts;

        if(title == null || title.isEmpty()){
            rentalProducts = productModel.getRentalProductByCategoryId(categoryId, 8, 0);
        }else {
            if(distance!=null&&distance>0){
                GeoIp geoIp = new GeoIp();
                if(centralLatitude==null || centralLongitude ==null ){
                    GeoIpManager geoIpManager = new GeoIpManager();
                    geoIp =geoIpManager.getGeoIp(request);

                    centralLatitude = geoIp.getLatitude();
                    centralLongitude = geoIp.getLongitude();

                    System.out.println("From geoIp ");
                }

                if(geoIp!=null){
                    rentalProducts = productModel.getRentalProductByDistance(categoryId,title,centralLatitude,centralLongitude,distance,8,0);
                }else{
                    rentalProducts = productModel.getRentalProductByCategoryIdTitle(categoryId, title, 8, 0);
                    System.out.println(geoIp);
                }

            }else{
                rentalProducts = productModel.getRentalProductByCategoryIdTitle(categoryId, title, 8, 0);
                System.out.println("geoIp is null");
            }

            loadMoreProductUrl +="&title="+title;
        }

        if(rentalProducts != null){
            List<RentalProduct> rentalProductsTop = productModel.getOnlyRatedRentalProductOrderByRating(3, 0);
            String topRentalProductHeadTitle = "MOST RATED";
            if(rentalProductsTop==null || rentalProductsTop.size()==0){
                topRentalProductHeadTitle = "NEW PRODUCT";
                rentalProductsTop = productModel.getRentalProduct(3, 0);
            }
            List<RentalProduct> rentalProductsAscending = productModel.getRentalProductAscending(8, 0);
            RentalProduct rentalProductsRandom1 = productModel.getRentalProductRandom();
            RentalProduct rentalProductsRandom2 = productModel.getRentalProductRandom();
            RentalProduct rentalProductsRandom3 = productModel.getRentalProductRandom();
            RentalProduct rentalProductsRandom4 = productModel.getRentalProductRandom();

            modelAndView.addObject("showTopGallery", showTopGallery);
            modelAndView.addObject("selectedCategoryId", categoryId);
            modelAndView.addObject("clientFeedback", clientFeedback);
            modelAndView.addObject("productListTitle",selectedCategoryName);
            modelAndView.addObject("preSelectedCategoryName",preSelectedcategoryName );
            modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
            modelAndView.addObject("category", category);
            modelAndView.addObject("topRentalProductHeadTitle", topRentalProductHeadTitle);
            modelAndView.addObject("rentalProducts", rentalProducts);
            modelAndView.addObject("productsAscending", rentalProductsAscending);
            modelAndView.addObject("IsLogIn", IsLogin);
            modelAndView.addObject("BaseUrl",baseUrl);
            modelAndView.addObject("rentalProductsTop",rentalProductsTop);
            modelAndView.addObject("rentalProductsRandom1",rentalProductsRandom1);
            modelAndView.addObject("rentalProductsRandom2",rentalProductsRandom2);
            modelAndView.addObject("rentalProductsRandom3",rentalProductsRandom3);
            modelAndView.addObject("rentalProductsRandom4",rentalProductsRandom4);
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/partial-rendering/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getCategory(HttpServletRequest request,@PathVariable("categoryId") int categoryId){
        Category categorySelected = categoryModel.getById(categoryId);

        String baseUrl = (String) request.getAttribute("baseURL");
        ModelAndView modelAndView = new ModelAndView("public/partial/partial_rendering_new_product");
        if(categorySelected!=null)
            modelAndView.addObject("productListTitle",categorySelected.getName());
        else
            modelAndView.addObject("productListTitle","");

        List rentalProduct = productModel.getRentalProductByCategoryId(categoryId, 8, 0);
        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("rentalProducts",rentalProduct);
        return modelAndView;
    }

    @RequestMapping(value = "/partial-rendering/load/more/rental-product", method = RequestMethod.GET)
    public ModelAndView getMoreRentalProduct(HttpServletRequest request,
                                             @RequestParam(value = "categoryId",required = false,defaultValue = "0") int categoryId,
                                             @RequestParam(value = "title",required = false) String title,
                                             @RequestParam int limit,
                                             @RequestParam int offset){
        System.out.println("categoryId "+categoryId);
        Category categorySelected = categoryModel.getById(categoryId);
        List rentalProduct = new ArrayList<>();


        String baseUrl = (String) request.getAttribute("baseURL");
        ModelAndView modelAndView = new ModelAndView("public/partial/load_more_product");
        if(categorySelected!=null)
            modelAndView.addObject("productListTitle",categorySelected.getName());
        else
            modelAndView.addObject("productListTitle","");

        if(categoryId>0){
            if(title!=null && title!=""){
                rentalProduct = productModel.getRentalProductByCategoryIdTitle(categoryId, title, limit, offset);
                System.out.println("title "+rentalProduct.size());
            }else{
                rentalProduct = productModel.getRentalProductByCategoryId(categoryId, limit, offset);
                System.out.println("rentalProduct "+rentalProduct.size());
            }
        }else{
            if(title!=null && title!="") {
                rentalProduct = productModel.getRentalProductByTitle(title, limit, offset);
                System.out.println(" only title "+rentalProduct.size());
            }else {
                rentalProduct = productModel.getRentalProduct(limit, offset);
                System.out.println(" None "+rentalProduct.size());
            }
        }


        modelAndView.addObject("BaseUrl",baseUrl);
        modelAndView.addObject("rentalProducts",rentalProduct);
        return modelAndView;
    }


}
