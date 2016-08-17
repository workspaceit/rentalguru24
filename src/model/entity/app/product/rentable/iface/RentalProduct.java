package model.entity.app.product.rentable.iface;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.AppCredential;
import model.entity.app.RentType;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.ProductLiked;
import model.entity.app.product.rentable.ProductLocation;
import model.nonentity.photo.Picture;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MI on 14-Aug-16.
 */
@JsonSerialize(as=RentalProduct.class)
public interface RentalProduct {

    public int getId();
    public String getName() ;
    public String getDescription() ;
    public float getAverageRating();
    public Picture getProfileImage();
    public List<Picture> getOtherImages();
    public double getCurrentValue();
    public double getRentFee();
    public boolean isActive();
    public Timestamp getAvailableFrom();
    public Timestamp getAvailableTill();
    public Timestamp getCreatedDate();
    public AppCredential getOwner() ;
    public ProductLocation getProductLocation();
    public List<ProductCategory> getProductCategories();
    public RentType getRentType();
    public ProductLiked getProductLiked();
    public boolean getIsLiked();

    public void setId(int id);
    public void setName(String name);
    public void setDescription(String description);
    public void setAverageRating(float averageRating);
    public void setProfileImage(Picture profileImage);
    public void setOtherImages(List<Picture> otherImages);
    public void setCurrentValue(double currentValue);
    public void setRentFee(double rentFee);
    public void setActive(boolean active);
    public boolean isCurrentlyAvailable();
    public void setCurrentlyAvailable(boolean currentlyAvailable);
    public void setAvailableFrom(Timestamp availableFrom);
    public void setAvailableTill(Timestamp availableTill);
    public boolean isReviewStatus();
    public void setReviewStatus(boolean reviewStatus);
    public void setCreatedDate(Timestamp createdDate);
    public void setOwner(AppCredential owner);
    public void setProductLocation(ProductLocation productLocation);
    public void setProductCategories(List<ProductCategory> productCategories);
    public void setRentType(RentType rentType);
    public void setProductLiked(ProductLiked productLiked);
    public void setIsLiked(boolean isLiked);
}
