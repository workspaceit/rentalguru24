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
@Table(name = "rent_product", schema = "")
public class RentProduct {
    private int id;
    private RentRequest rentRequest;
    private int productId;
    private int renteeId;
    private Date startDate;
    private Date endsDate;
    private boolean expired;
    private boolean productReturned;
    private boolean productReceived;
    private Timestamp createdDate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    @JsonIgnore
    @Basic
    @Column(name = "rentee_id")
    public int getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(int renteeId) {
        this.renteeId = renteeId;
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
    @Column(name = "product_returned")
    public boolean isProductReturned() {
        return productReturned;
    }

    public void setProductReturned(boolean productReturned) {
        this.productReturned = productReturned;
    }

    @Basic
    @Column(name = "product_received")
    public boolean isProductReceived() {
        return productReceived;
    }

    public void setProductReceived(boolean productReceived) {
        this.productReceived = productReceived;
    }

    @JsonIgnore
    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
