package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import helper.ServiceResponse;
import model.*;
import model.entity.app.product.view.ProductView;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @JsonView(ProductView.RentalProductView.class)
    @RequestMapping(value = "/get-product", method = RequestMethod.GET)
    public ServiceResponse getProduct(HttpServletRequest request,
                                      @RequestParam ("limit") int limit,
                                      @RequestParam ("offset") int offset){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<RentalProduct> rentalProducts = productModel.getRentalProduct(limit, offset);
        serviceResponse.setResponseData(rentalProducts,"No product found");
        return serviceResponse;
    }
    @JsonView(ProductView.RentalProductView.class)
    @RequestMapping(value = "/get-product/{id}", method = RequestMethod.GET)
    public ServiceResponse getProductSearchById(HttpServletRequest request,
                                                @PathVariable("id") int id){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        serviceResponse.setResponseData(productModel.getProductSearchById(id),"Product not found");
       return serviceResponse;
    }
    @JsonView(ProductView.RentalProductView.class)
    @RequestMapping(value = "/get-searched-product", method = RequestMethod.GET)
    public List<SearchedProduct> getSearchedProduct(HttpServletRequest request,
                                                    @RequestParam ("limit") int limit,
                                                    @RequestParam ("offset") int offset){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<SearchedProduct> searchedProducts = productModel.getSearchedProduct(limit, offset);
        return searchedProducts;
    }


    @RequestMapping(value = "/get-only-rental-product-attributs", method = RequestMethod.GET)
    @JsonView(ProductView.RentalProductView.class)
    public ServiceResponse getOnlyRentalProductAttributes(HttpServletRequest request,
                                                          @RequestParam ("limit") int limit,
                                                          @RequestParam ("offset") int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");


        serviceResponse.setResponseData(productModel.getMyRentalProductList(40, limit, offset), "No record found");
        return serviceResponse;

    }

    @RequestMapping(value = "/get-my-rental-product-attributs", method = RequestMethod.GET)
    @JsonView(ProductView.MyRentalProductView.class)
    public ServiceResponse getOnlyMyRentalProductAttributes(HttpServletRequest request,
                                                          @RequestParam ("limit") int limit,
                                                          @RequestParam ("offset") int offset){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");


        serviceResponse.setResponseData(productModel.getMyRentalProductList(40, limit, offset), "No record found");
        return serviceResponse;

    }

}

