package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.product.rentable.RentalProductEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "rent_product", schema = "") //, catalog = "rentguru24"
public class RentProduct {
    private int id;
    private int productId;
    private Date startDate;
    private Date endsDate;
    private boolean expired;
    private Timestamp createdDate;
    private RentRequest rentRequest;
    private RentalProductEntity product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "ends_date")
    public Date getEndsDate() {
        return endsDate;
    }

    public void setEndsDate(Date endsDate) {
        this.endsDate = endsDate;
    }

    @Basic
    @Column(name = "expired")
    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
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

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "rent_request_id", referencedColumnName = "id", nullable = false)
    public RentRequest getRentRequest() {
        return rentRequest;
    }

    public void setRentRequest(RentRequest rentRequest) {
        this.rentRequest = rentRequest;
    }

}
