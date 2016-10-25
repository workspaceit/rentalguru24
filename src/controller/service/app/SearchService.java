package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import library.ipGeoTracker.GeoIpManager;
import library.ipGeoTracker.dataModel.GeoIp;
import model.*;
import model.entity.app.AppCredential;
import model.entity.app.ProductRating;
import model.entity.app.RentType;
import model.entity.app.TempFile;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.rentable.ProductLocation;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.MyRentedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.entity.app.product.view.ProductView;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validator.form.ProductEditFromValidator;
import validator.form.ProductUploadFormValidator;
import validator.form.class_file.ProductEditFrom;
import validator.form.class_file.ProductUploadForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/search/")
public class SearchService {
    @Autowired
    ProductModel productModel;

    @Autowired
    ProductLocationModel productLocationModel;

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    RentTypeModel rentTypeModel;

    @Autowired
    ProductRatingModel productRatingModel;

    @Autowired
    RentRequestModel rentRequestModel;

    @Autowired
    ProductRatingModel getProductRatingModel;

    @RequestMapping(value = "/rental-product",method = RequestMethod.GET)
    @JsonView(ProductView.RentalProductView.class)
    public ServiceResponse searchRentalProduct(
                                            HttpServletRequest request,
                                            @RequestParam(name = "title" ,required = false) String title,
                                            @RequestParam(name = "categoryId" ,required = false) Integer categoryId,
                                            @RequestParam(name = "radius" ,required = false) Float radius,
                                            @RequestParam(name = "lat" ,required = false) Double lat,
                                            @RequestParam(name = "lng" ,required = false)Double lng,
                                            @RequestParam(name = "limit" ,required = true)Integer limit,
                                            @RequestParam(name = "offset" ,required = true)Integer offset
                                        ){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        try {
            if(title!=null && !title.equals(""))
                title = URLDecoder.decode(title,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            serviceResponse.setRequestError("title","title is not in valid format");
        }
        if(radius!=null){
            if(lat==null || lng==null){
                GeoIp geoIp = new GeoIpManager().getGeoIp(request);
                if(geoIp!=null){
                    lat = geoIp.getLatitude();
                    lng = geoIp.getLongitude();
                }
            }
        }
        limit=(limit > 10)?10:limit;
        List<RentalProduct> rentalProduct = productModel.getRentalProductForSearch(categoryId,title,lat,lng,radius,limit,offset);
        serviceResponse.setResponseData(rentalProduct,"No product found");
        return serviceResponse;

    }


}

