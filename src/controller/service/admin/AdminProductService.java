package controller.service.admin;

import helper.EmailHelper;
import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomal on 8/24/2016.
 */
@RestController
@RequestMapping("/api-admin")
public class AdminProductService {

    @Autowired
    ProductModel productModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    RentRequestModel rentRequestModel;

    @RequestMapping(value = "/product/approve-product", method = RequestMethod.POST)
    public ServiceResponse approveRentalProduct(HttpServletRequest request,  @RequestParam("pid") int productId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        boolean flag=productModel.approveRentalProduct(productId);
        if (!flag){
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("Something Went Wrong");
            return serviceResponse;
        }

        RentalProduct rentalProduct = productModel.getById(productId);
        List<ProductCategory> productCategories = rentalProduct.getProductCategories();
        for(int i = 0; i < productCategories.size(); i++){
            categoryModel.categoryCountIncrease(productCategories.get(i).getCategory().getId());
        }

        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Product status changed Successfully");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Email sending");
                RentalProduct rentalProduct = productModel.getById(productId);
                if(rentalProduct!=null){
                    EmailHelper.rentalProductApprovalDisapprovalEmail(rentalProduct, true);
                    System.out.println("Inside Email sent");
                }
            }
        }).start();

        return serviceResponse;
    }

    @RequestMapping(value = "/product/disapprove-product", method = RequestMethod.POST)
    public ServiceResponse disApproveRentalProduct(HttpServletRequest request,  @RequestParam("pid") int productId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        boolean flag=productModel.disapproveRentalProduct(productId);

        if (!flag){
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("Something Went Wrong");
            return serviceResponse;
        }

        RentalProduct rentalProduct = productModel.getById(productId);
        List<ProductCategory> productCategories = rentalProduct.getProductCategories();
        for(int i = 0; i < productCategories.size(); i++){
            categoryModel.categoryCountDecrease(productCategories.get(i).getCategory().getId());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Email sending");
                RentalProduct rentalProduct = productModel.getById(productId);
                if(rentalProduct!=null){
                    EmailHelper.rentalProductApprovalDisapprovalEmail(rentalProduct, false);
                    System.out.println("Inside Email sent");
                }
            }
        }).start();


        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Product status changed successfully");

        return serviceResponse;
    }

    @RequestMapping(value = "/pending/product-search-dates", method = RequestMethod.GET)
    public ServiceResponse searchPandingRentRequesBetweenDates(HttpServletRequest request, @RequestParam Map<String, String> allRequestParam){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        String stDate = allRequestParam.get("stDate");
        String edDate = allRequestParam.get("edDate");

        List<RentRequest> rentRequestList = rentRequestModel.searchRentRequestPendingByBetweenDates(stDate, edDate);
        if(rentRequestList == null){
            serviceResponse.setRequestError("date","No product found between this date");
            return serviceResponse;
        }
        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Rent request list");
        serviceResponse.setResponseData(rentRequestList);
        return serviceResponse;
    }
}
