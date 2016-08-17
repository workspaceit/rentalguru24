package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.product.rentable.RentalProductEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/12/16.
 */
@Entity
@Table(name = "product_liked", schema = "")
public class ProductLiked {
    private int id;
    private int appCredentialId;
    private Timestamp createdDate;
    private RentalProductEntity product;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id",insertable = false,updatable = false)
    public RentalProductEntity getProduct() {
        return product;
    }

    public void setProduct(RentalProductEntity product) {
        this.product = product;
    }

}
