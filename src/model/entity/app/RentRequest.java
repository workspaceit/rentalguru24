package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/1/16.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "rent_request", schema = "") //, catalog = "rentguru24"
public class RentRequest {
    private int id;
    private RentalProduct rentalProduct;
    private AppCredential requestedBy;
    private List<RentRequest> requestExtension;
    private Double rentFee;
    private Double advanceAmount;
    private boolean requestCancel;
    private Date startDate;
    private Date endDate;
    private Boolean approve;
    private Boolean disapprove;
    private Boolean isExpired;
    private boolean isExtension;
    private String remark;
    private boolean isPaymentComplete;
    private boolean isRentComplete;
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


    @ManyToOne(targetEntity=RentalProductEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }



    public void setRentalProduct(RentalProduct rentalProduct) {
        this.rentalProduct = rentalProduct;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "requested_by", referencedColumnName = "id", nullable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public AppCredential getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(AppCredential requestedBy) {
        this.requestedBy = requestedBy;
    }


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = true)
    public List<RentRequest> getRequestExtension() {
        return requestExtension;
    }

    public void setRequestExtension(List<RentRequest> requestExtension) {
        this.requestExtension = requestExtension;
    }

    @JsonIgnore
    @Basic
    @Column(name = "rent_fee")
    public Double getRentFee() {
        return rentFee;
    }

    public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }
    @JsonIgnore
    @Basic
    @Column(name = "advance_amount")
    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @Basic
    @Column(name = "request_cancel")
    public boolean getRequestCancel() {
        return requestCancel;
    }

    public void setRequestCancel(boolean requestCancel) {
        this.requestCancel = requestCancel;
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
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "approve")
    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    @Basic
    @Column(name = "disapprove")
    public Boolean getDisapprove() {
        return disapprove;
    }

    public void setDisapprove(Boolean disApprove) {
        this.disapprove = disApprove;
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
    @Column(name = "extension")
    public boolean getIsExtension() {
        return isExtension;
    }

    public void setIsExtension(boolean extension) {
        this.isExtension = extension;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remarks) {
        if(remarks!=null){
            remarks = remarks.trim();
        }else{
            remarks = "";
        }

        if(remarks.isEmpty()){
            remarks = null;
        }
        this.remark = remarks;
    }

    @Basic
    @Column(name = "payment_complete")
    public boolean getIsPaymentComplete() {
        return isPaymentComplete;
    }

    public void setIsPaymentComplete(boolean isPaymentComplete) {
        this.isPaymentComplete = isPaymentComplete;
    }



   // @JsonIgnore
    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
}
