package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/12/16.
 */
@Entity
@Table(name = "product_rating", schema = "", catalog = "rentguru24")
public class ProductRating {
    private int id;
    private int productId;
    private int appCredentialId;
    private int rateValue;
    private Timestamp createdDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "app_credential_id")
    public int getAppCredentialId() {
        return appCredentialId;
    }

    public void setAppCredentialId(int appCredentialId) {
        this.appCredentialId = appCredentialId;
    }

    @Basic
    @Column(name = "rate_value")
    public int getRateValue() {
        return rateValue;
    }

    public void setRateValue(int rateValue) {
        this.rateValue = rateValue;
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

        ProductRating that = (ProductRating) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (appCredentialId != that.appCredentialId) return false;
        if (rateValue != that.rateValue) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + appCredentialId;
        result = 31 * result + rateValue;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
