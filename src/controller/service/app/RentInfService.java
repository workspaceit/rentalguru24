package controller.service.app;

import helper.ServiceResponse;
import model.ProductModel;
import model.RentInfModel;
import model.RentalProductReturnedHistoryModel;
import model.RentalProductReturnedModel;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductReturned;
import model.entity.app.product.rentable.RentalProductReturnedHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/29/16.
 */
@RestController
@RequestMapping("/api/auth/rent-inf")
public class RentInfService {
    @Autowired
    RentalProductReturnedModel rentalProductReturnedModel;
    @Autowired
    RentalProductReturnedHistoryModel rentalProductReturnedHistoryModel;
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    ProductModel productModel;

    @RequestMapping(value = "/get-by-rent-request-id/{rentRequestId}", method = RequestMethod.GET)
    public ServiceResponse getByRentRequestId(HttpServletRequest request,
                                      @PathVariable("rentRequestId") int rentRequestId,
                                        @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentInf rentInf = rentInfModel.getByRentRequestId(rentRequestId);

        if(rentInf==null){
            serviceResponse.setRequestError("rentRequestId", "No order details found");
            return serviceResponse;
        }
        if(rentInf.getRentalProduct().getOwner().getId() != appCredential.getId()
                &&
                rentInf.getRentRequest().getRequestedBy().getId() != appCredential.getId() ){
            serviceResponse.setRequestError("rentRequestId", "You don't have privilege to access this order details");
            return serviceResponse;
        }

        serviceResponse.setResponseData(rentInf);

        return serviceResponse;
    }

   

}
