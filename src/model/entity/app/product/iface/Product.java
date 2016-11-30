package model.entity.app.product.iface;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.AppCredential;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.ProductLiked;
import model.entity.app.product.rentable.ProductLocation;
import model.nonentity.photo.Picture;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/22/16.
 */
 public interface Product {

     int getId();
     String getName() ;
     String getDescription() ;
     float getAverageRating();
     Picture getProfileImage();
     List<Picture> getOtherImages();
     boolean isActive();
     double getRentFee();
     AppCredential getOwner() ;
     ProductLocation getProductLocation();
     List<ProductCategory> getProductCategories();
     boolean getIsLiked();
     //ProductLiked getProductLiked();
     @JsonIgnore
     Timestamp getCreatedDate();


     void setId(int id);
     void setName(String name);
     void setDescription(String description);
     void setAverageRating(float averageRating);
     void setProfileImage(Picture profileImage);
     void setOtherImages(List<Picture> otherImages);
     void setActive(boolean active);
     void setReviewStatus(boolean reviewStatus);
     void setOwner(AppCredential owner);
     void setProductLocation(ProductLocation productLocation);
     void setProductCategories(List<ProductCategory> productCategories);
     //void setProductLiked(ProductLiked productLiked);
     void setIsLiked(boolean isLiked);


    void setCreatedDate(Timestamp createdDate);

}
