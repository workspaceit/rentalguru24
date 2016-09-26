package model.entity.app.payments;

import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name = "payout")
public class Payout {
    private long id;
    private RentInf rentInf;
    private double totalAmount;
    private String transactionId;
    private String transactionStatus;
    private String payoutBatchId;
    private Timestamp paypalPaymentDate;
    private Timestamp createdDate;
    private AppCredential appCredential;
    private String state;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "total_amount")
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "transaction_status")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Basic
    @Column(name = "payout_batch_id")
    public String getPayoutBatchId() {
        return payoutBatchId;
    }

    public void setPayoutBatchId(String payoutBatchId) {
        this.payoutBatchId = payoutBatchId;
    }

    @Basic
    @Column(name = "paypal_payment_date")
    public Timestamp getPaypalPaymentDate() {
        return paypalPaymentDate;
    }

    public void setPaypalPaymentDate(Timestamp paypalPaymentDate) {
        this.paypalPaymentDate = paypalPaymentDate;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }



    @OneToOne
    @JoinColumn(name = "app_credential_id",referencedColumnName = "id")
    public AppCredential getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(AppCredential appCredential) {
        this.appCredential = appCredential;
    }




    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @OneToOne
    @JoinColumn(name = "rent_inf_id",referencedColumnName = "id")
    public RentInf getRentInf() {
        return rentInf;
    }

    public void setRentInf(RentInf rentInf) {
        this.rentInf = rentInf;
    }
}
