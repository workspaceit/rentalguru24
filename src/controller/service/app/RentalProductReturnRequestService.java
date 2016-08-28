package controller.service.app;

import helper.ServiceResponse;
import model.RentProductModel;
import model.RequestProductReturnModel;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/26/16.
 */
@RestController
@RequestMapping("/api/auth/return-request")
public class RentalProductReturnRequestService {
    @Autowired
    RequestProductReturnModel requestProductReturnModel;
    @Autowired
    RentProductModel rentProductModel;


    @RequestMapping(value = "/{rentalInfId}",method = RequestMethod.GET)
    public ServiceResponse requestForReturnProductByProductOwner(HttpServletRequest request,@PathVariable int rentalInfId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturnRequest rentalProductReturnRequest = new RentalProductReturnRequest();
        RentInf rentInf = rentProductModel.getById(rentalInfId);

        System.out.println(rentInf.getRentalProduct().getId());
        rentalProductReturnRequest.setRentInf(rentInf);
        rentalProductReturnRequest.setIsExpired(false);
        requestProductReturnModel.insert(rentalProductReturnRequest);
        serviceResponse.setResponseData(rentProductModel.getById(rentalInfId));
        return serviceResponse;


    }
}
