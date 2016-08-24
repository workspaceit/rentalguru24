package model.entity.app.product;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/24/16.
 */
@Entity
@Table(name = "product_returned", schema = "")
public class ProductReturned {
    private int id;
    private int rentProductId;
    private boolean isReturned;
    private Timestamp returnedDate;
    private Timestamp created;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rent_product_id")
    public int getRentProductId() {
        return rentProductId;
    }

    public void setRentProductId(int rentProductId) {
        this.rentProductId = rentProductId;
    }

    @Basic
    @Column(name = "is_returned")
    public boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Basic
    @Column(name = "returned_date")
    public Timestamp getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Timestamp returnedDate) {
        this.returnedDate = returnedDate;
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

        ProductReturned that = (ProductReturned) o;

        if (id != that.id) return false;
        if (rentProductId != that.rentProductId) return false;
        if (isReturned != that.isReturned) return false;
        if (returnedDate != null ? !returnedDate.equals(that.returnedDate) : that.returnedDate != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rentProductId;
        result = 31 * result + (isReturned ? 1 : 0);
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
