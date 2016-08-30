package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.AppCredential;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;
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
    private boolean expired;
    private boolean productReturned;
    private boolean productReceived;

    public boolean hasReturnRequest = false;
    public boolean hasReceiveConfirmation = false;

    private List<RentalProductReturnRequest> rentalProductReturnRequestList;
    public RentalProductReturnRequest rentalProductReturnRequest;

    private List<RentalProductReturned> rentalProductReturnedList;
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
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
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
    @Override
    public String toString(){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
