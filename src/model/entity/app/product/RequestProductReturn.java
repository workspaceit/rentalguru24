package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.RentInf;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/26/16.
 */
@Entity
@Table(name = "request_product_return", schema = "")
public class RequestProductReturn {
    private int id;
    private RentalProduct rentalProduct;
    private RentInf rentInf;
    private String remarks;
    private Boolean isExpired;
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
    @OneToOne(fetch = FetchType.EAGER,targetEntity = RentalProductEntity.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }

    public void setRentalProduct(RentalProduct rentalProduct) {
        this.rentalProduct = rentalProduct;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rent_product_id", referencedColumnName = "id", nullable = false)
    public RentInf getRentInf() {
        return rentInf;
    }

    public void setRentInf(RentInf rentInf) {
        this.rentInf = rentInf;
        this.setRentalProduct(rentInf.getRentalProduct());
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "expired")
    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


}
