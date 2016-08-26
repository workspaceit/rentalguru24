package controller.service.app;

import helper.DateHelper;
import helper.ServiceResponse;
import model.ProductModel;
import model.RentProductModel;
import model.RentRequestModel;
import model.entity.app.AppCredential;
import model.entity.app.RentInf;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;

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

    /* **************************** Rent Request action [Started] ************************** */

    @RequestMapping(value = "/make-request/{productId}", method = RequestMethod.POST)
    public ServiceResponse makeRentRequest(HttpServletRequest request,
                                           @PathVariable("productId") int productId,
                                           @RequestParam("startDate")String startDate,
                                           @RequestParam("endsDate")String endsDate,
                                           @RequestParam(value="remark",required=false)String remark
    ){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");


        RentalProduct rentalProduct = productModel.getEntityById(productId);


        if(rentalProduct == null){
            serviceResponse.setRequestError("productId","Product does not exist by this id");
            return serviceResponse;
        }

        if(rentalProduct.getOwner().getId() == appCredential.getId()){
            serviceResponse.setRequestError("productId","You can not rent your own product");
            return serviceResponse;
        }


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


        if(rentProductModel.isProductInRent(productId, startTimeStamp, endTimeStamp)){
            serviceResponse.setRequestError("productId","Product is already in rent on given date");
            return serviceResponse;
        }



        if(startTimeStamp.before(rentalProduct.getAvailableFrom()) || startTimeStamp.after(rentalProduct.getAvailableTill())){
            serviceResponse.setRequestError("startDate","Product is not available for rent on given date");
        }

        if(endTimeStamp.before(rentalProduct.getAvailableFrom()) || endTimeStamp.after(rentalProduct.getAvailableTill())){
            serviceResponse.setRequestError("endsDate","Product is not available for rent on given date");
        }



        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }



        if(rentRequestModel.isAlreadyRequested(appCredential.getId(),productId,startTimeStamp,endTimeStamp)){
            serviceResponse.getResponseStat().setErrorMsg("You are already requested for this product in between those date");
            return serviceResponse;
        }

        RentRequest rentRequest = new RentRequest();

        rentRequest.setIsExpired(false);
        rentRequest.setIsExtension(false);
        rentRequest.setRequestCancel(false);
        rentRequest.setDisapprove(false);
        rentRequest.setApprove(false);
        rentRequest.setRequestedBy(appCredential);
        rentRequest.setRentalProduct(rentalProduct);
        rentRequest.setStartDate(new Date(startTimeStamp.getTime()));
        rentRequest.setEndDate(new Date(endTimeStamp.getTime()));
        rentRequest.setRemark(remark);

        rentRequestModel.insert(rentRequest);

        serviceResponse.getResponseStat().setMsg("Request successfully sent");
        serviceResponse.setResponseData(rentRequest, "Internal server error");

        return serviceResponse;

    }


    @RequestMapping(value = "/approve-request/{requestId}", method = RequestMethod.GET)
    public ServiceResponse approveRequest(HttpServletRequest request,
                                         @PathVariable("requestId") int requestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(requestId);
        if(rentRequest==null){
            serviceResponse.setRequestError("requestId","No request exist by this id ");
            return serviceResponse;
        }

        if(rentRequest.getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("requestId","You are not allowed to perform action for this rent request");
            return serviceResponse;
        }

        if(rentRequest.getApprove()){
            serviceResponse.setRequestError("requestId", "Request already approved");
            return serviceResponse;
        }

        RentInf rentInf = new RentInf();

        boolean isProductInRent = rentProductModel.isProductInRent( rentRequest.getRentalProduct().getId(),
                                                                    DateHelper.getSQLDateToTimeStamp(rentRequest.getStartDate()),
                                                                    DateHelper.getSQLDateToTimeStamp(rentRequest.getEndDate())
                                                                  );

        if(isProductInRent){
            serviceResponse.setRequestError("requestId","You can not approve, your product is in rent");
            return serviceResponse;
        }


        rentRequest.setApprove(true);
        rentRequest.setDisapprove(false);

        rentRequestModel.update(rentRequest);

        /* ~~~~~~~~~~~~~  Rent Product Insertion ~~~~~~~~~~~~~~~~*/

        rentInf.setRentRequest(rentRequest);
        rentInf.setProductReceived(false);
        rentInf.setProductReturned(false);
        rentInf.setExpired(false);
        rentInf.setStartDate(rentRequest.getStartDate());
        rentInf.setEndsDate(rentRequest.getEndDate());
        rentInf.setRentalProduct(rentRequest.getRentalProduct());
        rentInf.setRenteeId(rentRequest.getRequestedBy().getId());

        rentProductModel.insert(rentInf);

        /* ~~~~~~~~~~~~~  Expire Request in Between date ~~~~~~~~~~~~~~~~*/


        rentRequestModel.expireByDateBetween(rentRequest.getId(),rentRequest.getRentalProduct().getId(),
                DateHelper.getSQLDateToTimeStamp(rentRequest.getStartDate()),
                DateHelper.getSQLDateToTimeStamp(rentRequest.getEndDate())
        );

        serviceResponse.setResponseData(rentRequest, "Internal server error");
        return serviceResponse;
    }

    @RequestMapping(value = "/disapprove-request/{requestId}", method = RequestMethod.GET)
    public ServiceResponse disapproveRequest(HttpServletRequest request,
                                         @PathVariable("requestId") int requestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(requestId);
        if(rentRequest==null){
            serviceResponse.setRequestError("requestId","No request exist by this id ");
            return serviceResponse;
        }

        if(rentRequest.getRentalProduct().getOwner().getId() != appCredential.getId()){
            serviceResponse.setRequestError("requestId","You are not allowed to perform action for this rent request");
            return serviceResponse;
        }

        if(rentRequest.getApprove()){
            serviceResponse.setRequestError("requestId", "Can not be disapproved , request was approved and product is in rent");
            return serviceResponse;
        }

        if(rentRequest.getDisapprove()){
            serviceResponse.getResponseStat().setErrorMsg("Request already disapproved");
            return serviceResponse;
        }

        boolean isProductInRent = rentProductModel.isProductInRent( rentRequest.getRentalProduct().getId(),
                DateHelper.getSQLDateToTimeStamp(rentRequest.getStartDate()),
                DateHelper.getSQLDateToTimeStamp(rentRequest.getEndDate())
        );

        if(isProductInRent){
            serviceResponse.setRequestError("requestId", "You can not disapprove, your product is in rent");
            return serviceResponse;
        }


        rentRequest.setDisapprove(true);

        rentRequestModel.update(rentRequest);
        serviceResponse.setResponseData(rentRequest,"No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/cancel-request/{requestId}", method = RequestMethod.GET)
    public ServiceResponse cancelRequest(HttpServletRequest request,
                                             @PathVariable("requestId") int requestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(requestId);
        if(rentRequest==null){
            serviceResponse.setRequestError("requestId","No request exist by this id ");
            return serviceResponse;
        }

        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            serviceResponse.setRequestError("requestId","You are not allowed to perform action for this rent request");
            return serviceResponse;
        }

        if(rentRequest.getRequestCancel()){
            serviceResponse.getResponseStat().setErrorMsg("Request already canceled");
            return serviceResponse;
        }

        rentRequest.setRequestCancel(true);

        rentRequestModel.update(rentRequest);
        serviceResponse.setResponseData(rentRequest,"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/undo-cancel-request/{requestId}", method = RequestMethod.GET)
    public ServiceResponse undoCancelRequest(HttpServletRequest request,
                                         @PathVariable("requestId") int requestId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        RentRequest rentRequest = rentRequestModel.getById(requestId);
        if(rentRequest==null){
            serviceResponse.setRequestError("requestId","No request exist by this id ");
            return serviceResponse;
        }

        if(rentRequest.getRequestedBy().getId() != appCredential.getId()){
            serviceResponse.setRequestError("requestId","You are not allowed to perform action for this rent request");
            return serviceResponse;
        }

        if(!rentRequest.getRequestCancel()){
            serviceResponse.getResponseStat().setErrorMsg("Request is not in cancel state");
            return serviceResponse;
        }

        rentRequest.setRequestCancel(false);

        rentRequestModel.update(rentRequest);
        serviceResponse.setResponseData(rentRequest,"No record found");
        return serviceResponse;
    }


    /* **************************** Rent Request action [Ends] ************************** */


    /* **************************** Get all Rent request list For Product Owner [Started] ************************** */

    @RequestMapping(value = "/get-my-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyProductRentRequest(HttpServletRequest request,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getByProductOwner(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-product-rent-request/{productId}", method = RequestMethod.POST)
    public ServiceResponse getMyProductRentRequestByProductId(HttpServletRequest request,
                                                     @PathVariable int productId,
                                                     @RequestParam("limit") int limit,
                                                     @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getByProductOwnerAndProductId(appCredential.getId(), productId, limit, offset), "No record found");
        return serviceResponse;
    }


    @RequestMapping(value = "/get-my-approved-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyProductAllApprovedRentRequest(HttpServletRequest request,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllApproveRequestByProductOwner(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-disapproved-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyProductAllDisapprovedRentRequest(HttpServletRequest request,
                                                     @RequestParam("limit") int limit,
                                                     @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllDisapproveRequestByProductOwner(appCredential.getId(), limit, offset),"No record found");

        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-pending-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyProductAllPendingRentRequest(HttpServletRequest request,
                                                        @RequestParam("limit") int limit,
                                                        @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllPendingRequestByProductOwner(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-canceled-product-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyProductAllCanceledRentRequest(HttpServletRequest request,
                                                    @RequestParam("limit") int limit,
                                                    @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllCanceledRequestByProductOwner(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }

     /* **************************** Get all Rent request list For Product Owner [Ends] ************************** */



     /* **************************** Get all Rent request list For Request Maker [Started] ************************** */

    @RequestMapping(value = "/get-my-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyRentRequest(HttpServletRequest request,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getByRequestedBy(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-rent-request/{productId}", method = RequestMethod.POST)
    public ServiceResponse getMyRentRequestByProductId(HttpServletRequest request,
                                                              @PathVariable int productId,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        System.out.print("productId "+productId);
        serviceResponse.setResponseData(rentRequestModel.getByRequestedByAndProductId(appCredential.getId(), productId, limit, offset), "No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-pending-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyPendingRentRequest(HttpServletRequest request,
                                            @RequestParam("limit") int limit,
                                            @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllPendingRequestByRequestedBy(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-approved-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyApprovedRentRequest(HttpServletRequest request,
                                                   @RequestParam("limit") int limit,
                                                   @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllApproveRequestByRequestedBy(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-my-disapproved-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyAllDisapprovedRentRequest(HttpServletRequest request,
                                                                 @RequestParam("limit") int limit,
                                                                 @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllDisapproveRequestByRequestedBy(appCredential.getId(), limit, offset),"No record found");

        return serviceResponse;
    }
    @RequestMapping(value = "/get-my-canceled-rent-request", method = RequestMethod.POST)
    public ServiceResponse getMyAllCanceledRentRequest(HttpServletRequest request,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam("offset")int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        serviceResponse.setResponseData(rentRequestModel.getAllCanceledRequestByRequestedBy(appCredential.getId(), limit, offset),"No record found");
        return serviceResponse;
    }



}
