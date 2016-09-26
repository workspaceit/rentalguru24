package model.entity.app.payments;

import model.entity.app.AppCredential;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name = "payment_refund", schema = "", catalog = "rentguru24")
public class PaymentRefund {
    private long id;
    private AppCredential appCredential;
    private double totalAmount;
    private Timestamp paypalPaymentDate;
    private Timestamp createdDate;
    private RentPayment rentPayment;
    private String parentPayId;
    private String paypalSaleId;
    private String paypalRefundId;

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
    public RentPayment getRentPayment() {
        return rentPayment;
    }

    public void setRentPayment(RentPayment paymentByRentPaymentId) {
        this.rentPayment = paymentByRentPaymentId;
    }

    @ManyToOne
    @JoinColumn(name = "app_credential_id", referencedColumnName = "id", nullable = false)
    public AppCredential getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(AppCredential appCredential) {
        this.appCredential = appCredential;
    }

    @Basic
    @Column(name = "parent_pay_id")
    public String getParentPayId() {
        return parentPayId;
    }

    public void setParentPayId(String parentPayId) {
        this.parentPayId = parentPayId;
    }

    @Basic
    @Column(name = "paypal_sale_id")
    public String getPaypalSaleId() {
        return paypalSaleId;
    }

    public void setPaypalSaleId(String paypalSaleId) {
        this.paypalSaleId = paypalSaleId;
    }

    @Basic
    @Column(name = "paypal_refund_id")
    public String getPaypalRefundId() {
        return paypalRefundId;
    }

    public void setPaypalRefundId(String paypalRefundId) {
        this.paypalRefundId = paypalRefundId;
    }


}
