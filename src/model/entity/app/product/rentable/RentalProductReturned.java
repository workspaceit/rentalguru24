package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/27/16.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "rental_product_returned", schema = "")
public class RentalProductReturned {
    private int id;
    private boolean confirm;
    private boolean dispute;
    private boolean isExpired;
    private Timestamp createdDate;
    private RentInf rentInf;
    private String renteeRemarks;
    private String renterRemarks;
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
    public boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean expired) {
        this.isExpired = expired;
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
    public List<RentalProductReturnedHistory> getRentalProductReturnedHistories() {
        return rentalProductReturnedHistories;
    }

    public void setRentalProductReturnedHistories(List<RentalProductReturnedHistory> rentalProductReturnedHistories) {
        this.rentalProductReturnedHistories = rentalProductReturnedHistories;
    }

    @Basic
    @Column(name = "rentee_remarks")
    public String getRenteeRemarks() {
        return renteeRemarks;
    }

    public void setRenteeRemarks(String remarks) {
        this.renteeRemarks = remarks;
    }

    @Basic
    @Column(name = "renter_remarks")
    public String getRenterRemarks() {
        return renterRemarks;
    }

    public void setRenterRemarks(String renterRemarks) {
        this.renterRemarks = renterRemarks;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="rent_inf_id",referencedColumnName = "id",nullable = false)
    public RentInf getRentInf() {
        return rentInf;
    }

    public void setRentInf(RentInf rentInf) {
        this.rentInf = rentInf;
    }


}
