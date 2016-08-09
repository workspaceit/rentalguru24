package model.entity.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "rent_request", schema = "", catalog = "rentguru24")
public class RentRequest {
    private int id;
    private int productId;
    private int requestedBy;
    private List<RentRequest> requestExtension;
    private boolean requestCancel;
    private Date startDate;
    private Date endDate;
    private Boolean approve;
    private boolean extension;
    private Timestamp createdDate;

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
    @Column(name = "requested_by")
    public int getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(int requestedBy) {
        this.requestedBy = requestedBy;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
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

        RentRequest that = (RentRequest) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (requestedBy != that.requestedBy) return false;
        if (requestCancel != that.requestCancel) return false;
        if (approve != that.approve) return false;
        if (extension != that.extension) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + requestedBy;
        result = 31 * result + (requestCancel ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (approve ? 1 : 0);
        result = 31 * result + (extension ? 1 : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
