package model.entity.app.payments;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name = "payment_refund")
public class PaymentRefund {
    private long id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentRefund that = (PaymentRefund) o;

        if (id != that.id) return false;
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (paypalPayerId != null ? !paypalPayerId.equals(that.paypalPayerId) : that.paypalPayerId != null)
            return false;
        if (paypalPayId != null ? !paypalPayId.equals(that.paypalPayId) : that.paypalPayId != null) return false;
        if (paypalTransection != null ? !paypalTransection.equals(that.paypalTransection) : that.paypalTransection != null)
            return false;
        if (paypalPaymentDate != null ? !paypalPaymentDate.equals(that.paypalPaymentDate) : that.paypalPaymentDate != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (paypalPayerId != null ? paypalPayerId.hashCode() : 0);
        result = 31 * result + (paypalPayId != null ? paypalPayId.hashCode() : 0);
        result = 31 * result + (paypalTransection != null ? paypalTransection.hashCode() : 0);
        result = 31 * result + (paypalPaymentDate != null ? paypalPaymentDate.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    public RentPayment getPaymentByRentPaymentId() {
        return paymentByRentPaymentId;
    }

    public void setPaymentByRentPaymentId(RentPayment paymentByRentPaymentId) {
        this.paymentByRentPaymentId = paymentByRentPaymentId;
    }
}
