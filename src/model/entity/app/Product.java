package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "product", schema = "", catalog = "rentguru24")
public class Product {
    private int id;
    private int ownerId;
    private String name;
    private String description;
    private String images;
    private double currentValue;
    private double fee;
    private boolean available;
    private boolean reviewStatus;
    private Timestamp createdDate;
    private Collection<ProductAttribute> productAttributes;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_id")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
    @Column(name = "images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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
    @Column(name = "fee")
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "available")
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (id != that.id) return false;
        if (ownerId != that.ownerId) return false;
        if (Double.compare(that.currentValue, currentValue) != 0) return false;
        if (Double.compare(that.fee, fee) != 0) return false;
        if (available != that.available) return false;
        if (reviewStatus != that.reviewStatus) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (images != null ? !images.equals(that.images) : that.images != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + ownerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        temp = Double.doubleToLongBits(currentValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (reviewStatus ? 1 : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "productByProductId")
//    public Collection<ProductAttribute> getProductAttributes() {
//        return productAttributes;
//    }

    public void setProductAttributes(Collection<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}
