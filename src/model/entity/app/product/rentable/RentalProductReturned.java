package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.entity.app.product.rentable.iface.RentalProduct;

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
    private Timestamp created;
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
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalProductReturned that = (RentalProductReturned) o;

        if (id != that.id) return false;
        if (confirm != that.confirm) return false;
        if (dispute != that.dispute) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (confirm ? 1 : 0);
        result = 31 * result + (dispute ? 1 : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rentalProductReturned",cascade = CascadeType.ALL)
    //@JoinColumn(name = "product_returned_id",referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    public List<RentalProductReturnedHistory> getRentalProductReturnedHistories() {
        return rentalProductReturnedHistories;
    }

    public void setRentalProductReturnedHistories(List<RentalProductReturnedHistory> rentalProductReturnedHistoriesById) {
        this.rentalProductReturnedHistories = rentalProductReturnedHistoriesById;
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
