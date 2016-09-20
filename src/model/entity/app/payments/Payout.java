package model.entity.app.payments;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
public class Payout {
    private long id;
    private double totalAmount;
    private String paypalPayerId;
    private String paypalPayId;
    private String paypalTransection;
    private Timestamp paypalPaymentDate;
    private Timestamp createdDate;

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

        Payout payout = (Payout) o;

        if (id != payout.id) return false;
        if (Double.compare(payout.totalAmount, totalAmount) != 0) return false;
        if (paypalPayerId != null ? !paypalPayerId.equals(payout.paypalPayerId) : payout.paypalPayerId != null)
            return false;
        if (paypalPayId != null ? !paypalPayId.equals(payout.paypalPayId) : payout.paypalPayId != null) return false;
        if (paypalTransection != null ? !paypalTransection.equals(payout.paypalTransection) : payout.paypalTransection != null)
            return false;
        if (paypalPaymentDate != null ? !paypalPaymentDate.equals(payout.paypalPaymentDate) : payout.paypalPaymentDate != null)
            return false;
        if (createdDate != null ? !createdDate.equals(payout.createdDate) : payout.createdDate != null) return false;

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
}
