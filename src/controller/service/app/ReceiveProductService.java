package controller.service.app;

import helper.DateHelper;
import helper.ServiceResponse;
import model.ProductModel;
import model.RentInfModel;
import model.RentalProductReturnedHistoryModel;
import model.RentalProductReturnedModel;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentalProductReturned;
import model.entity.app.product.rentable.RentalProductReturnedHistory;
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
    RentalProductReturnedHistoryModel rentalProductReturnedHistoryModel;
    @Autowired
    RentInfModel rentInfModel;
    @Autowired
    ProductModel productModel;

    @RequestMapping(value = "/confirm-receive/{rentalProductReturnId}", method = RequestMethod.POST)
    public ServiceResponse renturnProduct(HttpServletRequest request,
                                      @PathVariable("rentalProductReturnId") int rentalProductReturnId,
                                        @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturned rentalProductReturned = rentalProductReturnedModel.getById(rentalProductReturnId);

        if(rentalProductReturned==null){
            serviceResponse.setRequestError("rentalProductReturnId","No product return information is found by this rentalProductReturnId");
            return serviceResponse;
        }

        if(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentalProductReturnId","Can't confirm received ,you are not product owner !! Suck a lemon");
            return serviceResponse;
        }

        if(rentalProductReturned.isConfirm()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.isDispute()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.getIsExpired()) {
            serviceResponse.setRequestError("rentalProductReturnId","This record is expired");
            return serviceResponse;
        }


        this.processProductReturnConfirmDispute(serviceResponse,rentalProductReturned,remarks,true,false);

        return serviceResponse;
    }
    @RequestMapping(value = "/dispute-receive/{rentalProductReturnId}", method = RequestMethod.POST)
    public ServiceResponse disputeReturnProduct(HttpServletRequest request,
                                          @PathVariable("rentalProductReturnId") int rentalProductReturnId,
                                          @RequestParam(value = "remarks",required = false) String remarks){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProductReturned rentalProductReturned = rentalProductReturnedModel.getById(rentalProductReturnId);

        if(rentalProductReturned==null){
            serviceResponse.setRequestError("rentalProductReturnId","No product return information is found by this rentalProductReturnId");
            return serviceResponse;
        }

        if(rentalProductReturned.getRentInf().getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("rentalProductReturnId","Can't confirm received ,you are not product owner !! Suck a lemon");
            return serviceResponse;
        }

        if(rentalProductReturned.isConfirm()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.isDispute()) {
            serviceResponse.setRequestError("rentalProductReturnId","You have already confirmed received the product ");
            return serviceResponse;
        }

        if(rentalProductReturned.getIsExpired()) {
            serviceResponse.setRequestError("rentalProductReturnId","This record is expired");
            return serviceResponse;
        }


        this.processProductReturnConfirmDispute(serviceResponse,rentalProductReturned,remarks,false,true);

        return serviceResponse;
    }
    private ServiceResponse processProductReturnConfirmDispute(ServiceResponse serviceResponse,
                                                               RentalProductReturned rentalProductReturned,
                                                               String remarks,
                                                               Boolean confirm,
                                                               Boolean dispute){
        rentalProductReturned.setConfirm(confirm);
        rentalProductReturned.setDispute(dispute);
        //rentalProductReturned.setIsExpired(true);
        rentalProductReturned.setRenterRemarks((remarks == null || remarks.trim().isEmpty()) ? null : remarks);
        //rentalProductReturned.getRentInf().setExpired(false);


        /* Update RentalProductReturned .. Set Product receive on confirmed true/false  */
        rentalProductReturned.getRentInf().setProductReceived(confirm);
        rentalProductReturnedModel.update(rentalProductReturned);

        /* Insert to RentalProductReturnedHistory .. */
        RentalProductReturnedHistory rentalProductReturnedHistory = new RentalProductReturnedHistory();

        rentalProductReturnedHistory.setConfirm(rentalProductReturned.isConfirm());
        rentalProductReturnedHistory.setDispute(rentalProductReturned.isDispute());
        rentalProductReturnedHistory.setRentalProductReturned(rentalProductReturned);
        rentalProductReturnedHistory.setCreatedDate(DateHelper.getCurrentUtcDateTimeStamp());
        System.out.println("DateHelper.getCurrentUtcDateTimeStamp().getTime() " + DateHelper.getCurrentUtcDateTimeStamp().getTime());

        rentalProductReturnedHistoryModel.insert(rentalProductReturnedHistory);

        /* Make product available */
        rentalProductReturned.getRentInf().getRentalProduct().setCurrentlyAvailable(true);

        productModel.update(rentalProductReturned.getRentInf().getRentalProduct());
        serviceResponse.setResponseData(rentalProductReturned);

        return serviceResponse;
    }
   

}
