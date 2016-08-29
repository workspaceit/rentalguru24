package controller.service.app;

import helper.ServiceResponse;
import model.RentInfModel;
import model.RentalProductReturnedModel;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductReturned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/29/16.
 */
@RestController
@RequestMapping("/api/auth/receive-product")
public class ReceiveProductService {
    @Autowired
    RentalProductReturnedModel rentalProductReturnedModel;
    @Autowired
    RentInfModel rentInfModel;

    @RequestMapping(value = "/confirm-recive/{rentalProductReturnId}", method = RequestMethod.GET)
    public ServiceResponse renturnProduct(HttpServletRequest request,
                                      @PathVariable("rentalProductReturnId") int rentalProductReturnId,
                                        @RequestParam(value = "remarks",required = false) String remarks){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        RentalProductReturned rentalProductReturned = rentalProductReturnedModel.getById(rentalProductReturnId);

//        if(rentalProductReturned==null){
//            serviceResponse.setRequestError("rentalProductReturnId","No product return information is found by this rentalProductReturnId");
//            return serviceResponse;
//        }
//
//        if(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId() != appCredential.getId()){
//            serviceResponse.setRequestError("rentInfId","Can't confirm received ,you are not product owner !! Suck a lemon");
//            return serviceResponse;
//        }
//
//
//        if(rentalProductReturnedModel.alreadyReturnedProduct(rentalProductReturned)){
//            serviceResponse.setRequestError("rentInfId","You have already return the product");
//            return serviceResponse;
//        }
//
//
//        RentalProductReturned rentalProductReturned = new RentalProductReturned();
//        rentalProductReturned.setConfirm(false);
//        rentalProductReturned.setDispute(false);
//        rentalProductReturned.setRemarks((remarks == null || remarks.trim().isEmpty()) ? null : remarks);
//
//
//        rentInf.setProductReturned(true);
//
//        rentalProductReturned.setRentInf(rentInf);
//
//        rentalProductReturnedModel.insert(rentalProductReturned);
//
//        serviceResponse.setResponseData(rentalProductReturned);

        return serviceResponse;
    }
   

}
