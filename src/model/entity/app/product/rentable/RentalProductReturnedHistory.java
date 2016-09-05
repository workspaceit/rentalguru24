package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/27/16.
 */
@Entity
@Table(name = "rental_product_returned_history", schema = "")
public class RentalProductReturnedHistory {
    private int id;
    private boolean confirm;
    private boolean dispute;
    private Timestamp createdDate;
    private RentalProductReturned rentalProductReturned;

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
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        if(createdDate!=null){

            System.out.println("createdDate.getTime() "+createdDate.getTime());
        }
        return createdDate;
    }

    public void setCreatedDate(Timestamp created) {
        this.createdDate = created;
    }


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_returned_id",referencedColumnName = "id",nullable = false)
    public RentalProductReturned getRentalProductReturned() {
        return rentalProductReturned;
    }

    public void setRentalProductReturned(RentalProductReturned rentalProductReturned) {
        this.rentalProductReturned = rentalProductReturned;
    }
}
