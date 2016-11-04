package controller.service.admin;

import helper.EmailHelper;
import helper.ServiceResponse;
import model.ProductModel;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tomal on 8/24/2016.
 */
@RestController
@RequestMapping("/api-admin")
public class AdminProductService {

    @Autowired
    ProductModel productModel;

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

        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Review Status was changed Successfully");
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
        serviceResponse.getResponseStat().setMsg("Review Status was changed Successfully");

        return serviceResponse;
    }
}
