package model.entity.app.product.rentable.iface;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.RentProduct;
import model.entity.app.RentRequest;

import java.util.List;

/**
 * Created by MI on 14-Aug-16.
 */

public interface MyRentalProduct {

//    List<RentRequest> getRentRequests();
//    void setRentRequests(List<RentRequest> rentRequests);

    List<RentProduct> getRentProduct();
    void setRentProduct(List<RentProduct> rentProduct);
}