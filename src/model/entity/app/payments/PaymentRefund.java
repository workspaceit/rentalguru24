package model.entity.app.payments;

import model.entity.app.AppCredential;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name = "payment_refund")
public class PaymentRefund {
    private long id;
    private AppCredential appCredential;
    private double totalAmount;
    private String paypalPayerId;
    private String paypalPayId;
    private String paypalTransection;
    private Timestamp paypalPaymentDate;
    private Timestamp createdDate;
    private RentPayment paymentByRentPaymentId;

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
    @Column(name = "paypal_payer_id")
    public String getPaypalPayerId() {
        return paypalPayerId;
    }

    public void setPaypalPayerId(String paypalPayerId) {
        this.paypalPayerId = paypalPayerId;
    }

    @Basic
    @Column(name = "paypal_pay_id")
    public String getPaypalPayId() {
        return paypalPayId;
    }

    public void setPaypalPayId(String paypalPayId) {
        this.paypalPayId = paypalPayId;
    }

    @Basic
    @Column(name = "paypal_transection")
    public String getPaypalTransection() {
        return paypalTransection;
    }

    public void setPaypalTransection(String paypalTransection) {
        this.paypalTransection = paypalTransection;
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


    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    public RentPayment getPaymentByRentPaymentId() {
        return paymentByRentPaymentId;
    }

    public void setPaymentByRentPaymentId(RentPayment paymentByRentPaymentId) {
        this.paymentByRentPaymentId = paymentByRentPaymentId;
    }
    @ManyToOne
    @JoinColumn(name = "app_credential_id", referencedColumnName = "id", nullable = false)
    public AppCredential getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(AppCredential appCredential) {
        this.appCredential = appCredential;
    }
}
