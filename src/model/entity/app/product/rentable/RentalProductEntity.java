package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.convert.PictureArrayConverter;
import model.convert.PictureConverter;
import model.entity.app.AppCredential;
import model.entity.app.RentType;
import model.entity.app.product.ProductCategory;
import model.entity.app.product.ProductLiked;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.nonentity.photo.Picture;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/8/16.
 */

//@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "product", schema = "" ) //catalog = "rentguru24"
public class RentalProductEntity implements RentalProduct {

    private int id;

    private String name;
    private String description;
    private float averageRating;
    private Picture profileImage;
    private List<Picture> otherImages;
    private double currentValue;
    private double rentFee;
    private boolean active;
    private boolean currentlyAvailable;
    private Timestamp availableFrom;
    private Timestamp availableTill;
    private boolean reviewStatus;
    private Timestamp createdDate;
    private AppCredential owner;
    private ProductLocation productLocation;
    private List<ProductCategory> productCategories;
    private RentType rentType;
    private ProductLiked productLiked;
    private boolean isLiked;

    /* Not ready to to deploy in develop server */


//    private List<ProductAvailability> productAvailability;
//    private List<RentRequest> rentRequests;
//    private RentProduct rentProduct;



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Basic
    @Column(name = "profile_image")
    @Convert(converter = PictureConverter.class)
    public Picture getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Picture profileImage) {
        this.profileImage = profileImage;
    }

    @Basic
    @Column(name = "other_images")
    @Convert(converter = PictureArrayConverter.class)
    public List<Picture> getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(List<Picture> otherImages) {
        this.otherImages = otherImages;
    }

    @Basic
    @Column(name = "current_value")
    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    @Basic
    @Column(name = "rent_fee")
    public double getRentFee() {
        return rentFee;
    }

    public void setRentFee(double rentFee) {
        this.rentFee = rentFee;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "currently_available")
    public boolean isCurrentlyAvailable() {
        return currentlyAvailable;
    }

    public void setCurrentlyAvailable(boolean currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    @Basic
    @Column(name = "available_from")
    public Timestamp getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Timestamp availableFrom) {
        this.availableFrom = availableFrom;
    }

    @Basic
    @Column(name = "available_till")
    public Timestamp getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(Timestamp availableTill) {
        this.availableTill = availableTill;
    }

    @Basic
    @Column(name = "review_status")
    public boolean isReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Basic
    @Column(name = "created_date")
    @JsonIgnore
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    public AppCredential getOwner(){
        return this.owner;
    }

    public void setOwner(AppCredential owner){
        this.owner = owner;
    }



    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "product_id", nullable = false)
    public ProductLocation getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(ProductLocation productLocation) {
        this.productLocation = productLocation;
    }



    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public List<ProductCategory> getProductCategories() {

        return productCategories;
    }


    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rent_type_id", referencedColumnName = "id", nullable = false)
    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }


    @Basic
    @Column(name = "average_rating")
    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "product")
    @Where(clause = "product_id = id")
    public ProductLiked getProductLiked() {
        return productLiked;
    }

    public void setProductLiked(ProductLiked productLiked) {
        boolean isLiked = false;

        if(productLiked!=null && productLiked.getId()!=0) isLiked = true;

        this.setIsLiked(isLiked);
        this.productLiked = productLiked;
        System.out.println("isLiked "+isLiked);
        System.out.println("this.productLiked "+this.productLiked);
    }


    @Transient
    @JsonSerialize
    @JsonDeserialize
    public boolean getIsLiked() {
        return isLiked;
    }


    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
    /* Not ready to to deploy in develop server */
//
//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SUBSELECT)
//    @JoinColumn(name="product_id",referencedColumnName = "id")
//    public List<ProductAvailability> getProductAvailability() {
//        return productAvailability;
//    }
//
//    public void setProductAvailability(List<ProductAvailability> productAvailability) {
//        this.productAvailability = productAvailability;
//    }
//
//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SUBSELECT)
//    @JoinColumn(name="product_id",referencedColumnName = "id")
//    public List<RentRequest> getRentRequests() {
//        return rentRequests;
//    }
//
//    public void setRentRequests(List<RentRequest> rentRequests) {
//        this.rentRequests = rentRequests;
//    }
//
//    @OneToOne(mappedBy = "product")
//    public RentProduct getRentProduct() {
//        return rentProduct;
//    }
//
//    public void setRentProduct(RentProduct rentProduct) {
//        this.rentProduct = rentProduct;
//    }
}
