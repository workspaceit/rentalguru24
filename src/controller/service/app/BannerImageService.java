package controller.service.app;

import com.fasterxml.jackson.annotation.JsonView;
import helper.ServiceResponse;
import model.*;
import model.entity.BannerImage;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.entity.app.product.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 8/8/16.
 */
@RestController
@RequestMapping("/api/banner-image")
public class BannerImageService {
    @Autowired
    BannerImageModel bannerImageModel;

    @JsonView(ProductView.RentalProductView.class)
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ServiceResponse getProduct(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<BannerImage> bannerImages = bannerImageModel.getAll();
        serviceResponse.setResponseData(bannerImages,"No product found");
        return serviceResponse;
    }

}

