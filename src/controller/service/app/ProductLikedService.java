package controller.service.app;

import helper.ServiceResponse;
import model.ProductLikedModel;
import model.ProductModel;
import model.entity.app.AppCredential;
import model.entity.app.product.ProductLiked;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by omar on 8/22/16.
 */
@RestController
@RequestMapping("/api/auth/productliked")
public class ProductLikedService {

    @Autowired
    ProductModel productModel;

    @Autowired
    ProductLikedModel productLikedModel;

    @RequestMapping(value = "/liked-product/{product_id}", method = RequestMethod.POST)
    public ServiceResponse giveProductLick(HttpServletRequest request, @PathVariable("product_id") int productId){

        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        RentalProduct rentalProduct = productModel.getEntityById(productId);

        if(rentalProduct.getOwner().getId() == appCredential.getId()){
            serviceResponse.setRequestError("productId","You can not like your own product");
            return serviceResponse;
        }

        if(rentalProduct == null){
            serviceResponse.setRequestError("productId","Product does not exist by this id");
            return serviceResponse;
        }

        int appCridentialId = appCredential.getId();

        boolean isLiked = productLikedModel.isLiked(productId, appCridentialId);

        if(isLiked != true){

            ProductLiked productLiked = new ProductLiked();
            productLiked.setRentalProduct(rentalProduct);
            productLiked.setAppCredential(appCredential);

            serviceResponse.setResponseData(productLikedModel.insert(productLiked));
            return serviceResponse;
        }else{
            serviceResponse.setResponseData(productLikedModel.delete(productId, appCridentialId));
            return serviceResponse;
        }

    }
}
