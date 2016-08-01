package model.entity.app;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "rent_product", schema = "", catalog = "rentguru24")
public class RentProduct {
    private int id;
    private int productId;
    private Date startDate;
    private Date endsDate;
    private boolean expired;
    private Timestamp createdDate;
    private Collection<RentRequest> rentRequestsById;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentProduct that = (RentProduct) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (expired != that.expired) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endsDate != null ? !endsDate.equals(that.endsDate) : that.endsDate != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endsDate != null ? endsDate.hashCode() : 0);
        result = 31 * result + (expired ? 1 : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "rentProductByBookingId")
//    public Collection<RentRequest> getRentRequestsById() {
//        return rentRequestsById;
//    }

    public void setRentRequestsById(Collection<RentRequest> rentRequestsById) {
        this.rentRequestsById = rentRequestsById;
    }
}
