package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/12/16.
 */
@Entity
@Table(name = "product_liked", schema = "", catalog = "rentguru24")
public class ProductLiked {
    private int id;
//    private int appCredentialId;
    private Timestamp createdDate;
    private RentalProduct rentalProduct;
    private AppCredential appCredential;
//    private int productId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "app_credential_id")
//    public int getAppCredentialId() {
//        return appCredentialId;
//    }

//    public void setAppCredentialId(int appCredentialId) {
//        this.appCredentialId = appCredentialId;
//    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    
    @OneToOne(targetEntity = RentalProductEntity.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id",insertable = false,updatable = false)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }

    public void setRentalProduct(RentalProduct rentalProduct) {
        this.rentalProduct = rentalProduct;
    }

//    @Basic
//    @Column(name = "product_id")
//    public int getProductId() {
//        return productId;
//    }

//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

    @OneToOne
    @JoinColumn(name = "app_credential_id", referencedColumnName = "id", nullable = false)
    public AppCredential getAppCredential(){return appCredential;}

    public void setAppCredential(AppCredential appCredential){this.appCredential = appCredential;}

//    @OneToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
//    public RentalProductEntity getProduct(){return  product;}
//
//    public void setProduct(RentalProductEntity product){this.product = product;}

}
