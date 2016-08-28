package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Where;

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
    private Timestamp created;
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
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
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
