package controller.web.app;

import model.IdentityTypeModel;
import model.ProductModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.IdentityType;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
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

    @Autowired
    IdentityTypeModel identityTypeModel;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "User Dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/my-products", method = RequestMethod.GET)
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

    @RequestMapping(value = "/my-rentrequest",method = RequestMethod.GET)
    public ModelAndView getMyRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myrentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        List <RentRequest>rentRequests=rentRequestModel.getByRequestedBy(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Rent Request");
        modelAndView.addObject("myRentRequests", rentRequests );

        return modelAndView;

    }

    @RequestMapping(value = "/my-approved-bookings",method = RequestMethod.GET)
    public ModelAndView getAllMyApprovedBookings(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myApprovedBookings");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<RentRequest> rentRequests=rentRequestModel.getAllApproveRequestByRequestedBy(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Approved Booking Request");
        modelAndView.addObject("rentRequests", rentRequests );
        return modelAndView;


    }

    @RequestMapping(value = "/my-profile-edit", method = RequestMethod.GET)
    public ModelAndView getMyprofileEdit(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/editprofile");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<IdentityType> identityTypes = identityTypeModel.getAll();


        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Profile Edit");
        modelAndView.addObject("usersDetails", appCredential );
        modelAndView.addObject("identityTypes", identityTypes );
        return modelAndView;
    }

    @RequestMapping(value = "/my-dispproved-bookings",method = RequestMethod.GET)
    public ModelAndView getAllMyDisapprovedBookings(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myDisapprovedBookings");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<RentRequest> rentRequests=rentRequestModel.getAllDisapproveRequestByRequestedBy(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Disapproved Booking Request");
        modelAndView.addObject("rentRequests", rentRequests );
        return modelAndView;
    }

    @RequestMapping(value = "/my-approved-rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllMyApprovedRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myApprovedRentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<RentRequest> rentRequests=rentRequestModel.getAllApproveRequestByProductOwner(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Approved Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        return modelAndView;
    }

    @RequestMapping(value = "/my-disapproved-rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllMyDisApprovedRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myDisapprovedRentrequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<RentRequest> rentRequests=rentRequestModel.getAllDisapproveRequestByProductOwner(appCredential.getId());
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Disapproved Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        return modelAndView;
    }
}
