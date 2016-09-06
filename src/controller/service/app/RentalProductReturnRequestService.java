package controller.service.app;

import helper.DateHelper;
import helper.ServiceResponse;
import model.ProductModel;
import model.RentInfModel;
import model.RentalProductReturnRequestModel;
import model.RentalProductReturnedModel;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductReturnRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mi on 8/26/16.
 */
@RestController
@RequestMapping("/api/auth/return-request")
public class RentalProductReturnRequestService {
    @Autowired
    RentalProductReturnRequestModel rentalProductReturnRequestModel;
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    ProductModel productModel;


    @RequestMapping(value = "/make-request/{rentalInfId}",method = RequestMethod.POST)
    public ServiceResponse requestForReturnProductByProductOwner(HttpServletRequest request,
                                                                 @PathVariable int rentalInfId,
                                                                 @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturnRequest rentalProductReturnRequest = new RentalProductReturnRequest();
        RentInf rentInf = rentInfModel.getById(rentalInfId);
        if(rentInf==null){
            serviceResponse.setRequestError("rentalInfId","No rent information is found by this rentInfId");
            return serviceResponse;
        }
        if(rentInf.getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentalInfId","This rent is not belongs to you");
            return serviceResponse;
        }

        if(rentalProductReturnRequestModel.alreadyRequestedToReturn(rentalInfId)){
            serviceResponse.setRequestError("rentalInfId","You have already requested to return your product");
            return serviceResponse;
        }

        rentalProductReturnRequest.setRentInf(rentInf);
        rentalProductReturnRequest.setIsExpired(false);
        rentalProductReturnRequest.setRemarks((remarks == null || remarks.trim().isEmpty()) ? null : remarks);
        rentalProductReturnRequest.setCreatedDate(DateHelper.getUtcDateProcessedTimeStamp());
        rentalProductReturnRequestModel.insert(rentalProductReturnRequest);

        serviceResponse.setResponseData(rentInfModel.getById(rentalInfId));
        return serviceResponse;
    }
}
