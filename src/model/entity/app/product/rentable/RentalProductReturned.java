package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/27/16.
 */
@Entity
@Table(name = "rental_product_returned", schema = "")
public class RentalProductReturned {
    private int id;
    private boolean confirm;
    private boolean dispute;
    private boolean expired;
    private Timestamp createdDate;
    private RentInf rentInf;
    private List<RentalProductReturnedHistory> rentalProductReturnedHistories;

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
    @Column(name = "confirm")
    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    @Basic
    @Column(name = "dispute")
    public boolean isDispute() {
        return dispute;
    }

    public void setDispute(boolean dispute) {
        this.dispute = dispute;
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

    public void setCreatedDate(Timestamp created) {
        this.createdDate = created;
    }


    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL,mappedBy = "rentalProductReturned")
   // @JoinColumn(name = "product_returned_id",referencedColumnName = "id",nullable = false)
    public List<RentalProductReturnedHistory> getRentalProductReturnedHistories() {
        return rentalProductReturnedHistories;
    }

    public void setRentalProductReturnedHistories(List<RentalProductReturnedHistory> rentalProductReturnedHistories) {
        this.rentalProductReturnedHistories = rentalProductReturnedHistories;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="rent_product_id",referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    public RentInf getRentInf() {
        return rentInf;
    }

    public void setRentInf(RentInf rentInf) {
        this.rentInf = rentInf;
    }


}
