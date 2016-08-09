package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.convert.PictureArrayConverter;
import model.convert.PictureConverter;
import model.entity.app.AppCredential;
import model.nonentity.photo.Picture;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MI on 09-Aug-16.
 */
@MappedSuperclass
public class AbstractProduct {
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
    protected List<ProductCategory> productCategories;
    protected ProductLocation productLocation;

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

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "product_id", nullable = false)
    public ProductLocation getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(ProductLocation productLocation) {
        this.productLocation = productLocation;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Product that = (Product) o;
//
//        if (id != that.id) return false;
//        if (Double.compare(that.currentValue, currentValue) != 0) return false;
//        if (Double.compare(that.rentFee, rentFee) != 0) return false;
//        if (active != that.active) return false;
//        if (currentlyAvailable != that.currentlyAvailable) return false;
//        if (reviewStatus != that.reviewStatus) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (description != null ? !description.equals(that.description) : that.description != null) return false;
//        if (profileImage != null ? !profileImage.equals(that.profileImage) : that.profileImage != null) return false;
//        if (otherImages != null ? !otherImages.equals(that.otherImages) : that.otherImages != null) return false;
//        if (availableFrom != null ? !availableFrom.equals(that.availableFrom) : that.availableFrom != null)
//            return false;
//        if (availableTill != null ? !availableTill.equals(that.availableTill) : that.availableTill != null)
//            return false;
//        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = id;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + (profileImage != null ? profileImage.hashCode() : 0);
//        result = 31 * result + (otherImages != null ? otherImages.hashCode() : 0);
//        temp = Double.doubleToLongBits(currentValue);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(rentFee);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + (active ? 1 : 0);
//        result = 31 * result + (currentlyAvailable ? 1 : 0);
//        result = 31 * result + (availableFrom != null ? availableFrom.hashCode() : 0);
//        result = 31 * result + (availableTill != null ? availableTill.hashCode() : 0);
//        result = 31 * result + (reviewStatus ? 1 : 0);
//        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
//        return result;
//    }

}
