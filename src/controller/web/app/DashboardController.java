package controller.web.app;

import model.ProductModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by omar on 8/24/16.
 */
@Controller
@RequestMapping("/user/dashboard")
public class DashboardController {
    @Autowired
    ProductModel productModel;

    @Autowired
    RentRequestModel rentRequestModel;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "User Dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/myproducts", method = RequestMethod.GET)
    public ModelAndView getMyProducts(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/myproducts");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<RentalProduct> rentalProducts = productModel.getMyRentalProductList(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Product");
        modelAndView.addObject("myRentalProduct", rentalProducts);
        return modelAndView;
    }

    @RequestMapping(value = "/rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/rentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        List<RentRequest>rentRequests=rentRequestModel.getAllPendingRequestByProductOwner(appCredential.getId());


        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        return modelAndView;



    }
}
