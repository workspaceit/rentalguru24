package model.entity.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private boolean requestCancel;
    private Date startDate;
    private Date endDate;
    private Boolean approve;
    private boolean extension;
    private String remark;
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


    @ManyToOne(targetEntity=RentalProductEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }



    public void setRentalProduct(RentalProduct rentalProduct) {
        System.out.println("rentalProductEntity " + rentalProduct.getId());
        this.rentalProduct = rentalProduct;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by", referencedColumnName = "id", nullable = false)
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

    @Basic
    @Column(name = "request_cancel")
    public boolean isRequestCancel() {
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
    @Column(name = "extension")
    public boolean isExtension() {
        return extension;
    }

    public void setExtension(boolean extension) {
        this.extension = extension;
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
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }




}
