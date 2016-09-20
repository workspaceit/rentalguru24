package model.entity.app.payments;

import model.entity.app.AppCredential;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name = "transaction")
public class Transaction {
    private long id;
  //  private Long payoutId;
    public enum  TransactionType{
      Deposite,Payment,Payout,Refund,ReceivedRentFee
    }
    @Transient
    TransactionType transectionType;
    private String type;
    private String details;
    private double dr;
    private double cr;
    private double cumulativeDr;
    private double cumulativeCr;
    private Timestamp createdDate;
    private RentPayment paymentByRentPaymentId;
    private PaymentRefund paymentRefundByRefundId;
    private AppCredential appCredential;
 //   private Long paymentId;
//    private Long refundId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "dr")
    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

    @Basic
    @Column(name = "cr")
    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    @Basic
    @Column(name = "cumulative_dr")
    public double getCumulativeDr() {
        return cumulativeDr;
    }

    public void setCumulativeDr(double cumulativeDr) {
        this.cumulativeDr = cumulativeDr;
    }

    @Basic
    @Column(name = "cumulative_cr")
    public double getCumulativeCr() {
        return cumulativeCr;
    }

    public void setCumulativeCr(double cumulativeCr) {
        this.cumulativeCr = cumulativeCr;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }



    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    public RentPayment getPaymentByRentPaymentId() {
        return paymentByRentPaymentId;
    }

    public void setPaymentByRentPaymentId(RentPayment paymentByRentPaymentId) {
        this.paymentByRentPaymentId = paymentByRentPaymentId;
    }


}
