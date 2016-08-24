package model.entity.app.product.rentable.iface;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.AppCredential;
import model.entity.app.RentType;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.ProductLiked;
import model.entity.app.product.iface.Product;
import model.entity.app.product.rentable.ProductLocation;
import model.nonentity.photo.Picture;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MI on 14-Aug-16.
 */
//

public interface RentalProduct extends Product{



    RentType getRentType();
    double getCurrentValue();
    Timestamp getAvailableFrom();
    Timestamp getAvailableTill();
    boolean isCurrentlyAvailable();
    boolean isReviewStatus();


    void setCurrentValue(double currentValue);
    void setRentFee(double rentFee);
    void setRentType(RentType rentType);
    void setCurrentlyAvailable(boolean currentlyAvailable);
    void setAvailableFrom(Timestamp availableFrom);
    void setAvailableTill(Timestamp availableTill);
}
