package controller.service.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.service.BaseService;
import helper.DateHelper;
import helper.ImageHelper;
import helper.ServiceResponse;
import model.*;
import model.entity.app.*;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.ProductLocation;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validator.form.ProductUploadFormValidator;
import validator.form.class_file.ProductUploadForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/product")
public class PublicProductService{
    @Autowired
    ProductModel productModel;

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

    @RequestMapping(value = "/get-product", method = RequestMethod.GET)
    public ServiceResponse getProduct(HttpServletRequest request,
                                      @RequestParam ("limit") int limit,
                                      @RequestParam ("offset") int offset){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<RentalProduct> rentalProducts = productModel.getRentalProduct(limit, offset);
        serviceResponse.setResponseData(rentalProducts,"No product found");
        return serviceResponse;
    }

    @RequestMapping(value = "/get-product/{id}", method = RequestMethod.GET)
    public ServiceResponse getProductSearchById(HttpServletRequest request,
                                                @PathVariable("id") int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        serviceResponse.setResponseData(productModel.getProductSearchById(id),"Product not found");
       return serviceResponse;
    }

    @RequestMapping(value = "/get-searched-product", method = RequestMethod.GET)
    public List<SearchedProduct> getSearchedProduct(HttpServletRequest request,
                                                    @RequestParam ("limit") int limit,
                                                    @RequestParam ("offset") int offset){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<SearchedProduct> searchedProducts = productModel.getSearchedProduct(limit, offset);
        return searchedProducts;
    }

}

