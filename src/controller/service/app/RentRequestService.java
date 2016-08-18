package controller.service.app;

import controller.service.BaseService;
import helper.DateHelper;
import helper.ServiceResponse;
import model.ProductModel;
import model.RentProductModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by omar on 8/3/16.
 */
@RestController
@RequestMapping("/api/auth/rent")
public class RentRequestService{
    @Autowired
    RentRequestModel rentRequestModel;
    @Autowired
    RentProductModel rentProductModel;
    @Autowired
    ProductModel productModel;
    @RequestMapping(value = "/request-rent/{productId}", method = RequestMethod.POST)
    public ServiceResponse sendRentRequest(HttpServletRequest request,
                                           @PathVariable("productId") int productId,
                                           @RequestParam("startDate")String startDate,
                                           @RequestParam("endsDate")String endsDate,
                                           @RequestParam(value="remark",required=false)String remark
                                            ){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(startDate==null || startDate.isEmpty()){
           serviceResponse.setRequestError("startDate","Start date is required");
        }
        if(endsDate==null || endsDate.isEmpty()){
           serviceResponse.setRequestError("endsDate","End date is required");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }


        if(!DateHelper.isDateValid(startDate, "dd-MM-yyyy")){
           serviceResponse.setRequestError("startDate","Start date format miss matched");
        }
        if(!DateHelper.isDateValid(endsDate, "dd-MM-yyyy")){
           serviceResponse.setRequestError("endsDate","Ends date format miss matched");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }


        Timestamp startTimeStamp = DateHelper.getStringToTimeStamp(startDate, "dd-MM-yyyy");
        Timestamp endTimeStamp = DateHelper.getStringToTimeStamp(endsDate, "dd-MM-yyyy");


        if(rentProductModel.isProductInRent(productId,startTimeStamp,endTimeStamp)){
           serviceResponse.setRequestError("productId","Product is not available for rent in given date");
            return serviceResponse;
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }
        RentalProduct rentalProduct = productModel.getEntityById(productId);

        if(rentalProduct == null){
            serviceResponse.setRequestError("productId","Product does not exist by this id");
            return serviceResponse;
        }

        if(rentalProduct.getOwner().getId() == appCredential.getId()){
            serviceResponse.setRequestError("productId","You can not rent your own product");
            return serviceResponse;
        }

        if(rentRequestModel.isAlreadyRequested(appCredential.getId(),productId,startTimeStamp,endTimeStamp)){
            serviceResponse.getResponseStat().setErrorMsg("You are already requested for this product in between those date");
            return serviceResponse;
        }

        RentRequest rentRequest = new RentRequest();

        rentRequest.setApprove(false);
        rentRequest.setRequestedBy(appCredential);
        rentRequest.setRentalProduct(rentalProduct);
        rentRequest.setStartDate(new Date(startTimeStamp.getTime()));
        rentRequest.setEndDate(new Date(endTimeStamp.getTime()));
        rentRequest.setRemark(remark);

        rentRequestModel.insert(rentRequest);

        serviceResponse.setResponseData(rentRequest, "Internal server error");

        return serviceResponse;

    }

    @RequestMapping(value = "/get-my-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getRentRequest(HttpServletRequest request,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getByProductOwner(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-product-rent-request/{productId}", method = RequestMethod.POST)
    public ServiceResponse getRentRequestByProductId(HttpServletRequest request,
                                                     @PathVariable int productId,
                                                     @RequestParam("limit") int limit,
                                                     @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getProductId(appCredential.getId(), productId,limit,offset), "No record found");
        return serviceResponse;
    }
}
