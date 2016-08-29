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
@RequestMapping("/api/auth/return-product")
public class ReturnProductService {
    @Autowired
    RentalProductReturnedModel rentalProductReturnedModel;
    @Autowired
    RentInfModel rentInfModel;

    @RequestMapping(value = "/{rentInfId}", method = RequestMethod.POST)
    public ServiceResponse renturnProduct(HttpServletRequest request,
                                      @PathVariable("rentInfId") int rentInfId,
                                        @RequestParam(value = "remarks",required = false) String remarks){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        RentInf rentInf = rentInfModel.getById(rentInfId);

        if(rentInf==null){
            serviceResponse.setRequestError("rentInfId","No rent information is found by this rentInfId");
            return serviceResponse;
        }

        if(rentInf.getRentRequest().getRequestedBy().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentInfId","This rent is not belongs to you moran !! Suck a lemon");
            return serviceResponse;
        }


        if(rentalProductReturnedModel.alreadyReturnedProduct(rentInfId)){
            serviceResponse.setRequestError("rentInfId","You have already return the product");
            return serviceResponse;
        }


        RentalProductReturned rentalProductReturned = new RentalProductReturned();
        rentalProductReturned.setConfirm(false);
        rentalProductReturned.setDispute(false);
        rentalProductReturned.setRenteeRemarks((remarks == null || remarks.trim().isEmpty()) ? null : remarks);


        rentInf.setProductReturned(true);

        rentalProductReturned.setRentInf(rentInf);

        rentalProductReturnedModel.insert(rentalProductReturned);

        serviceResponse.setResponseData(rentalProductReturned);

        return serviceResponse;
    }


}
