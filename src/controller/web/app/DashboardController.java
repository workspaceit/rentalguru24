package controller.web.app;

import helper.ServiceResponse;
import model.IdentityTypeModel;
import model.ProductModel;
import model.RentInfModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.IdentityType;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.iface.MyRentalProduct;
import model.entity.app.product.rentable.iface.MyRentedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    RentInfModel rentInfModel;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/dashboard");
        String baseUrl = (String) request.getAttribute("baseURL");
        List<Category> category = (List<Category>) request.getAttribute("category");

        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "User Dashboard");
        modelAndView.addObject("appCredential", appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-products", method = RequestMethod.GET)
    public ModelAndView getMyProducts(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/myproducts");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<RentalProduct> rentalProducts = productModel.getMyRentalProductList(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Product");
        modelAndView.addObject("myRentalProduct", rentalProducts);
        modelAndView.addObject("appCredential", appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/rentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<RentRequest>rentRequests=rentRequestModel.getAllPendingRequestByProductOwner(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        modelAndView.addObject("appCredential", appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-booking",method = RequestMethod.GET)
    public ModelAndView getMyRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myrentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List <RentRequest>rentRequests=rentRequestModel.getAllPendingRequestByRequestedBy(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Rent Request");
        modelAndView.addObject("myRentRequests", rentRequests );
        modelAndView.addObject("appCredential",appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-approved-bookings",method = RequestMethod.GET)
    public ModelAndView getAllMyApprovedBookings(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myApprovedBookings");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<RentRequest> rentRequests=rentRequestModel.getAllApproveRequestByRequestedBy(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Approved Booking Request");
        modelAndView.addObject("rentRequests", rentRequests );
        modelAndView.addObject("appCredential",appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-profile-edit", method = RequestMethod.GET)
    public ModelAndView getMyprofileEdit(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/editprofile");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<IdentityType> identityTypes = identityTypeModel.getAll();
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Profile Edit");
        modelAndView.addObject("appCredential", appCredential );
        modelAndView.addObject("identityTypes", identityTypes );
        return modelAndView;
    }

    @RequestMapping(value = "/my-dispproved-bookings",method = RequestMethod.GET)
    public ModelAndView getAllMyDisapprovedBookings(HttpServletRequest request){
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");
        List<RentRequest> rentRequests=rentRequestModel.getAllDisapproveRequestByRequestedBy(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        ModelAndView modelAndView=new ModelAndView("user_dashboard/myDisapprovedBookings");
        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Disapproved Booking Request");
        modelAndView.addObject("rentRequests", rentRequests );
        modelAndView.addObject("appCredential",appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-approved-rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllMyApprovedRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myApprovedRentRequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<RentRequest> rentRequests=rentRequestModel.getAllApproveRequestByProductOwner(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Approved Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        modelAndView.addObject("appCredential",appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-disapproved-rentrequest",method = RequestMethod.GET)
    public ModelAndView getAllMyDisApprovedRentRequest(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("user_dashboard/myDisapprovedRentrequest");
        String baseUrl=(String)request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<RentRequest> rentRequests=rentRequestModel.getAllDisapproveRequestByProductOwner(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Disapproved Rent Request");
        modelAndView.addObject("rentRequests", rentRequests );
        modelAndView.addObject("appCredential",appCredential);
        return modelAndView;
    }

    @RequestMapping(value = "/my-rented-products", method = RequestMethod.GET)
    public ModelAndView getMyRentedProducts(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/my_rented_products");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");
//        List<MyRentedProduct> rentalProducts = productModel.getMyCurrentRentedProduct(appCredential.getId());
        Set<MyRentedProduct> rentalProducts =  new HashSet<MyRentedProduct>(productModel.getMyCurrentRentedProduct(appCredential.getId()));

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Rented Product");
        modelAndView.addObject("myRentedProduct", rentalProducts);
        modelAndView.addObject("appCredential", appCredential);
        System.out.println("rentalProducts " + rentalProducts.size());
        for(MyRentedProduct mp : rentalProducts){
            System.out.println("rentalProducts "+mp.getName());
        }
        return modelAndView;
    }
    @RequestMapping(value = "/my-products-on-rent", method = RequestMethod.GET)
    public ModelAndView getMyRentedProductsOnRent(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/my_rental_products_on_rent");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");

        List<MyRentalProduct> rentalProducts = productModel.getMyCurrentProductOnRent(appCredential.getId());
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Rented Product");
        modelAndView.addObject("myProductOnRent", rentalProducts);
        modelAndView.addObject("appCredential", appCredential);


        return modelAndView;
    }

    @RequestMapping(value = "/rent-history", method = RequestMethod.GET)
    public ModelAndView getRentHistory(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/rentHistory");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        List<Category> category = (List<Category>) request.getAttribute("category");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();

        List<RentInf> rentInfs = rentInfModel.getProductByAppcridentialId(appCredential.getId());

        modelAndView.addObject("category", category);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "My Rent History");
        modelAndView.addObject("appCredential", appCredential);
        modelAndView.addObject("rentInfs", rentInfs);

        return modelAndView;
    }

    @RequestMapping(value = "/edit-product/{product_id}/{owner_id}", method = RequestMethod.GET)
    public ModelAndView getEditProduct(HttpServletRequest request, @PathVariable("product_id") int productId, @PathVariable("owner_id") int ownerId){
        ModelAndView modelAndView = new ModelAndView("user_dashboard/editProduct");
        String baseUrl = (String) request.getAttribute("baseURL");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Boolean IsLogin = serviceResponse.getResponseStat().getIsLogin();
        List<Category> category = (List<Category>) request.getAttribute("category");
        RentalProduct rentalProduct = productModel.getMyRentalProductById(productId, ownerId);

        modelAndView.addObject("category", category);
        modelAndView.addObject("rentalProduct", rentalProduct);
        modelAndView.addObject("IsLogIn", IsLogin);
        modelAndView.addObject("BaseUrl", baseUrl);
        modelAndView.addObject("pageTitle", "Edit Product");
        modelAndView.addObject("appCredential", appCredential);
        return modelAndView;
    }
}
