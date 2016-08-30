package model.entity.app.product.rentable.iface;

import model.entity.app.product.iface.Product;
import model.entity.app.product.rentable.RentInf;

import java.util.List;

/**
 * Created by mi on 8/30/16.
 */
public interface MyRentedProduct extends Product{
    List<RentInf> getRentInf();
    void setRentInf(List<RentInf> rentInf);
}
