package controller.web.app;


import helper.ServiceResponse;
import helper.UrlHelper;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
import model.CategoryModel;
import model.CitiesModel;
import model.ProductModel;
import model.StateModel;
import model.entity.Cities;
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


    @Autowired
    StateModel stateModel;

    @Autowired
    CitiesModel citiesModel;

    @RequestMapping(value={"","/{usState}"},method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
                              @PathVariable(value="usState") Optional<String> stateCode,
                              @RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "cityId",required = false)Integer cityId,
                              @RequestParam(value = "cid",required = false)Integer categoryId,
                              @RequestParam(value = "radius",required = false)Float radius,
                              @RequestParam(value = "lat",required = false)Double lat,
                              @RequestParam(value = "lng",required = false)Double lng) {
        State selectedUsState = null;
        if(stateCode.isPresent()){
            selectedUsState = stateModel.getByCode(stateCode.get().trim());
        }


        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> categoryList = (List<Category>) request.getAttribute("category");
        String baseURL =  (String) request.getAttribute("baseURL");

        ModelAndView modelAndView = new ModelAndView("public/Search");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<RentalProduct> rentalProducts = new ArrayList<>();

        Category selectedCategory = null;
        String productListTitle = "Search result";
        String preSelectedCategoryName = "Browse By Category";

        if(categoryId!=null){
            selectedCategory = categoryModel.getById(categoryId);
        }
        if(selectedCategory!=null){
            preSelectedCategoryName = selectedCategory.getName();
        }
//
//        Category searchedCategory = (categoryId!=null)?categoryList.stream()
//                                                        .filter(category -> category.getId() == categoryId)
//                                                        .findFirst()
//                                                        .orElse(null):null;
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

        Cities city = (cityId!=null)?citiesModel.getById(cityId):null;


        rentalProducts = productModel.getRentalProductForSearch(selectedUsState,city,selectedCategory,title,lat,lng,radius,12,0);


        System.out.println("GeoIpManager "+GeoIpManager.getRemoteAddress(request));



        Integer stateId = (selectedUsState!=null)?selectedUsState.getId():null;



        String loadMoreProductUrl = "/home/partial-rendering/load/more/rental-product"+ UrlHelper.getLoadMoreUrlParams(stateId,title,categoryId,lat,lng,radius);

        String categoryBySearchUrl = "/search/"+selectedUsState.getCode().toLowerCase();
        categoryBySearchUrl+=(cityId!=null)?"?cityId="+cityId:"";
        System.out.println(categoryBySearchUrl);
        modelAndView.addObject("categoryBySearchUrl", categoryBySearchUrl);
        modelAndView.addObject("selectedCityId", cityId);
        modelAndView.addObject("isSearchPage", true);
        modelAndView.addObject("selectedUsState", selectedUsState);
        modelAndView.addObject("selectedCategoryId", categoryId);
        modelAndView.addObject("preSelectedCategoryName", preSelectedCategoryName);
        modelAndView.addObject("loadMoreProductUrl", loadMoreProductUrl);
        modelAndView.addObject("category", categoryList);
        modelAndView.addObject("productListTitle",productListTitle);
        modelAndView.addObject("rentalProducts", rentalProducts);
        modelAndView.addObject("IsLogIn", IsLogin);
        return modelAndView;
    }

}