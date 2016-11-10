package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import helper.ServiceResponse;
import model.*;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.product.view.ProductView;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/get-product-with-category-title", method = RequestMethod.GET)
    public ServiceResponse getProductWithCategoryTitle(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(allRequestParameter.get("categoryId") == null || allRequestParameter.get("categoryId").isEmpty()){
            serviceResponse.setRequestError("category","Category require");
            return serviceResponse;
        }
        if(allRequestParameter.get("limit") == null || allRequestParameter.get("limit").isEmpty()){
            serviceResponse.setRequestError("limit","Limit require");
            return serviceResponse;
        }

        if(allRequestParameter.get("offset") == null || allRequestParameter.get("offset").isEmpty()){
            serviceResponse.setRequestError("offset","Offset require");
            return serviceResponse;
        }



        int categoryId = Integer.parseInt(allRequestParameter.get("categoryId").trim());
        int limit = Integer.parseInt(allRequestParameter.get("limit").trim());
        int offset = Integer.parseInt(allRequestParameter.get("offset").trim());
        String title = allRequestParameter.get("title");

        if(title != null && !title.isEmpty()){
            title = title.trim();
        }

        List<RentalProduct> rentalProduct = productModel.getRentalProductByCategoryIdTitle(categoryId, title, limit, offset);

        if(rentalProduct == null || rentalProduct.isEmpty()){
            serviceResponse.setRequestError("product","No product found by this name");
            return serviceResponse;
        }

        serviceResponse.setResponseData(rentalProduct);
        return serviceResponse;
    }
    @RequestMapping(value = "/get-product-by-category", method = RequestMethod.GET)
    public ServiceResponse getProductByCategoryId(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(allRequestParameter.get("categoryId") == null || allRequestParameter.get("categoryId").isEmpty()){
            serviceResponse.setRequestError("category","Category require");
        }
        if(allRequestParameter.get("limit") == null || allRequestParameter.get("limit").isEmpty()){
            serviceResponse.setRequestError("limit","Limit require");
        }

        if(allRequestParameter.get("offset") == null || allRequestParameter.get("offset").isEmpty()){
            serviceResponse.setRequestError("offset","Offset require");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        int categoryId = Integer.parseInt(allRequestParameter.get("categoryId").trim());
        int limit = Integer.parseInt(allRequestParameter.get("limit").trim());
        int offset = Integer.parseInt(allRequestParameter.get("offset").trim());

        Category category = this.categoryModel.getById(categoryId);
        if(category==null){
            serviceResponse.setRequestError("category","Category not found");
        }
        List<RentalProduct> rentalProduct = productModel.getRentalProductByCategory(category, limit, offset);


        serviceResponse.setResponseData(rentalProduct,"No product found by this name");
        return serviceResponse;
    }
    @RequestMapping(value = "/get-product-with-title", method = RequestMethod.GET)
    public ServiceResponse getProductWithTitle(HttpServletRequest request, @RequestParam Map<String, String> allRequestParameter){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(allRequestParameter.get("title") == null || allRequestParameter.get("title").isEmpty()){
            serviceResponse.setRequestError("title","Title require");
            return serviceResponse;
        }

        if(allRequestParameter.get("limit") == null || allRequestParameter.get("limit").isEmpty()){
            serviceResponse.setRequestError("limit","Limit require");
            return serviceResponse;
        }

        if(allRequestParameter.get("offset") == null || allRequestParameter.get("offset").isEmpty()){
            serviceResponse.setRequestError("offset","Offset require");
            return serviceResponse;
        }

        String title = allRequestParameter.get("title").trim();
        int limit = Integer.parseInt(allRequestParameter.get("limit").trim());
        int offset = Integer.parseInt(allRequestParameter.get("offset").trim());

        List<RentalProduct> rentalProduct = productModel.getRentalProductByTitle(title, limit, offset);

        if(rentalProduct == null || rentalProduct.isEmpty()){
            serviceResponse.setRequestError("product","No product found by this name");
            return serviceResponse;
        }

        serviceResponse.setResponseData(rentalProduct);
        return serviceResponse;
    }
}

