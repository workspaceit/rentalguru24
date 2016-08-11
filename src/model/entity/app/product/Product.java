package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.convert.PictureArrayConverter;
import model.convert.PictureConverter;
import model.entity.app.AppCredential;
import model.entity.app.RentProduct;
import model.entity.app.RentRequest;
import model.nonentity.photo.Picture;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/8/16.
 */

//@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "product", schema = "" ) //catalog = "rentguru24"
public class Product{

    protected int id;

    protected String name;
    protected String description;
    protected Picture profileImage;
    protected List<Picture> otherImages;
    protected double currentValue;
    protected double rentFee;
    protected boolean active;
    protected boolean currentlyAvailable;
    protected Timestamp availableFrom;
    protected Timestamp availableTill;
    protected boolean reviewStatus;
    protected Timestamp createdDate;
    protected AppCredential owner;
    protected ProductLocation productLocation;
    protected List<ProductCategory> productCategories;
    protected List<ProductAvailability> productAvailability;
    protected List<RentRequest> rentRequests;
    protected RentProduct rentProduct;



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



    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    public List<ProductAvailability> getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(List<ProductAvailability> productAvailability) {
        this.productAvailability = productAvailability;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    public List<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(List<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }

    @OneToOne(mappedBy = "product")
    public RentProduct getRentProduct() {
        return rentProduct;
    }

    public void setRentProduct(RentProduct rentProduct) {
        this.rentProduct = rentProduct;
    }
}
