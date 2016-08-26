package controller.service.app;

import helper.ServiceResponse;
import model.RentProductModel;
import model.RequestProductReturnModel;
import model.entity.app.AppCredential;
import model.entity.app.RentInf;
import model.entity.app.product.RequestProductReturn;
import model.entity.app.product.rentable.iface.RentalProduct;
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


        RequestProductReturn requestProductReturn = new RequestProductReturn();
        RentInf rentInf = rentProductModel.getById(rentalInfId);

        System.out.println(rentInf.getRentalProduct().getId());
        requestProductReturn.setRentInf(rentInf);
        requestProductReturn.setIsExpired(false);
        requestProductReturnModel.insert(requestProductReturn);
        serviceResponse.setResponseData(rentProductModel.getById(rentalInfId));
        return serviceResponse;


    }
}
