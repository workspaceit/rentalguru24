package model.entity.app.product.rentable.iface;

import model.entity.app.RentInf;

import java.util.List;

/**
 * Created by MI on 14-Aug-16.
 */

public interface MyRentalProduct {

//    List<RentRequest> getRentRequests();
//    void setRentRequests(List<RentRequest> rentRequests);

    List<RentInf> getRentInf();
    void setRentInf(List<RentInf> rentInf);
}