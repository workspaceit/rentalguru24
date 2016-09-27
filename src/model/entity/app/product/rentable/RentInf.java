package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.entity.app.product.view.ProductView;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/1/16.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "rent_inf", schema = "")
public class RentInf {
    private int id;
    private RentRequest rentRequest;
    private RentalProduct rentalProduct;
    private AppCredential rentee;
    private Date startDate;
    private Date endsDate;
    private boolean isRentComplete;
    private boolean expired;
    private boolean productReturned;
    private boolean productReceived;

    public boolean hasReturnRequest = false;
    public boolean hasReceiveConfirmation = false;

    private List<RentalProductReturnRequest> rentalProductReturnRequestList;
    private List<RentalProductReturned> rentalProductReturnedList;

    @JsonView({ProductView.MyRentalProductView.class,ProductView.MyRentedProductView.class})
    public RentalProductReturnRequest rentalProductReturnRequest;
    @JsonView({ProductView.MyRentalProductView.class,ProductView.MyRentedProductView.class})
    public RentalProductReturned rentalProductReturned;

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
    @OneToOne(fetch = FetchType.LAZY,targetEntity=RentalProductEntity.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }

    public void setRentalProduct(RentalProduct rentalProduct) {
        this.rentalProduct = rentalProduct;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rentee_id",referencedColumnName = "id",nullable = false)
    public AppCredential getRentee() {
        return rentee;
    }

    public void setRentee(AppCredential rentee) {
        this.rentee = rentee;
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

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "rent_request_id", referencedColumnName = "id", nullable = false)
    public RentRequest getRentRequest() {
        return rentRequest;
    }

    public void setRentRequest(RentRequest rentRequest) {
        this.rentRequest = rentRequest;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="rentInf")
    @Fetch(value = FetchMode.SUBSELECT)
    @Where(clause = "expired=false")
    public List<RentalProductReturnRequest> getRentalProductReturnRequestList() {
        return rentalProductReturnRequestList;
    }

    public void setRentalProductReturnRequestList(List<RentalProductReturnRequest> rentalProductReturnRequestList) {
        if(rentalProductReturnRequestList !=null && rentalProductReturnRequestList.size()>0){
            this.hasReturnRequest = true;
            rentalProductReturnRequest = rentalProductReturnRequestList.get(0);
        }
        this.rentalProductReturnRequestList = rentalProductReturnRequestList;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "rentInf")
    @Where(clause="expired=false")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<RentalProductReturned> getRentalProductReturnedList() {
        return rentalProductReturnedList;
    }

    public void setRentalProductReturnedList(List<RentalProductReturned> rentalProductReturnedList) {
        if(rentalProductReturnedList!=null && rentalProductReturnedList.size()>0){
            this.hasReceiveConfirmation = true;
            rentalProductReturned = rentalProductReturnedList.get(0);
        }
        this.rentalProductReturnedList = rentalProductReturnedList;
    }

    @Transient
    public RentalProductReturnRequest getRentalProductReturnRequest() {
        return rentalProductReturnRequest;
    }

    public void setRentalProductReturnRequest(RentalProductReturnRequest rentalProductReturnRequest) {
        this.rentalProductReturnRequest = rentalProductReturnRequest;
    }

    @Transient
    public RentalProductReturned getRentalProductReturned() {
        return rentalProductReturned;
    }

    public void setRentalProductReturned(RentalProductReturned rentalProductReturned) {
        this.rentalProductReturned = rentalProductReturned;
    }
    @JsonIgnore
    @Basic
    @Column(name = "rent_complete")
    public boolean getIsRentComplete() {
        return isRentComplete;
    }

    public void setIsRentComplete(boolean isRentComplete) {
        this.isRentComplete = isRentComplete;
    }

    @Override
    public String toString() {
        return "RentInf{" +
                "id=" + id +
                ", rentRequest=" + rentRequest +
                ", rentalProduct=" + rentalProduct +
                ", rentee=" + rentee +
                ", startDate=" + startDate +
                ", endsDate=" + endsDate +
                ", isRentComplete=" + isRentComplete +
                ", expired=" + expired +
                ", productReturned=" + productReturned +
                ", productReceived=" + productReceived +
                ", hasReturnRequest=" + hasReturnRequest +
                ", hasReceiveConfirmation=" + hasReceiveConfirmation +
                ", rentalProductReturnRequestList=" + rentalProductReturnRequestList +
                ", rentalProductReturnedList=" + rentalProductReturnedList +
                ", rentalProductReturnRequest=" + rentalProductReturnRequest +
                ", rentalProductReturned=" + rentalProductReturned +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentInf rentInf = (RentInf) o;

        if (id != rentInf.id) return false;
        if (isRentComplete != rentInf.isRentComplete) return false;
        if (expired != rentInf.expired) return false;
        if (productReturned != rentInf.productReturned) return false;
        if (productReceived != rentInf.productReceived) return false;
        if (hasReturnRequest != rentInf.hasReturnRequest) return false;
        if (hasReceiveConfirmation != rentInf.hasReceiveConfirmation) return false;
        if (rentRequest != null ? !rentRequest.equals(rentInf.rentRequest) : rentInf.rentRequest != null) return false;
        if (rentalProduct != null ? !rentalProduct.equals(rentInf.rentalProduct) : rentInf.rentalProduct != null)
            return false;
        if (rentee != null ? !rentee.equals(rentInf.rentee) : rentInf.rentee != null) return false;
        if (startDate != null ? !startDate.equals(rentInf.startDate) : rentInf.startDate != null) return false;
        if (endsDate != null ? !endsDate.equals(rentInf.endsDate) : rentInf.endsDate != null) return false;
        if (rentalProductReturnRequestList != null ? !rentalProductReturnRequestList.equals(rentInf.rentalProductReturnRequestList) : rentInf.rentalProductReturnRequestList != null)
            return false;
        if (rentalProductReturnedList != null ? !rentalProductReturnedList.equals(rentInf.rentalProductReturnedList) : rentInf.rentalProductReturnedList != null)
            return false;
        if (rentalProductReturnRequest != null ? !rentalProductReturnRequest.equals(rentInf.rentalProductReturnRequest) : rentInf.rentalProductReturnRequest != null)
            return false;
        if (rentalProductReturned != null ? !rentalProductReturned.equals(rentInf.rentalProductReturned) : rentInf.rentalProductReturned != null)
            return false;
        return !(createdDate != null ? !createdDate.equals(rentInf.createdDate) : rentInf.createdDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rentRequest != null ? rentRequest.hashCode() : 0);
        result = 31 * result + (rentalProduct != null ? rentalProduct.hashCode() : 0);
        result = 31 * result + (rentee != null ? rentee.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endsDate != null ? endsDate.hashCode() : 0);
        result = 31 * result + (isRentComplete ? 1 : 0);
        result = 31 * result + (expired ? 1 : 0);
        result = 31 * result + (productReturned ? 1 : 0);
        result = 31 * result + (productReceived ? 1 : 0);
        result = 31 * result + (hasReturnRequest ? 1 : 0);
        result = 31 * result + (hasReceiveConfirmation ? 1 : 0);
        result = 31 * result + (rentalProductReturnRequestList != null ? rentalProductReturnRequestList.hashCode() : 0);
        result = 31 * result + (rentalProductReturnedList != null ? rentalProductReturnedList.hashCode() : 0);
        result = 31 * result + (rentalProductReturnRequest != null ? rentalProductReturnRequest.hashCode() : 0);
        result = 31 * result + (rentalProductReturned != null ? rentalProductReturned.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
