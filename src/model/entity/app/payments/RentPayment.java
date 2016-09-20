package model.entity.app.payments;

import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentInf;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/20/16.
 */
@Entity
@Table(name="rent_payment")
public class RentPayment {
    private long id;
    private AppCredential appCredential;
    private RentInf rentInf;
    private double rentFee;
    private double refundAmount;
    private double totalAmount;
    private String paypalPayerId;
    private String paypalPayId;
    private String paypalSaleId;
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
    @Column(name = "rent_fee")
    public double getRentFee() {
        return rentFee;
    }

    public void setRentFee(double rentFee) {
        this.rentFee = rentFee;
    }

    @Basic
    @Column(name = "refund_amount")
    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
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
    @Column(name = "paypal_sale_id")
    public String getPaypalSaleId() {
        return paypalSaleId;
    }

    public void setPaypalSaleId(String paypalSaleId) {
        this.paypalSaleId = paypalSaleId;
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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_credential_id",referencedColumnName = "id")
    public AppCredential getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(AppCredential appCredential) {
        this.appCredential = appCredential;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rent_inf_id",referencedColumnName = "id")
    public RentInf getRentInf() {
        return rentInf;
    }

    public void setRentInf(RentInf rentInf) {
        this.rentInf = rentInf;
    }

    @Override
    public String toString() {
        return "RentPayment{" +
                "id=" + id +
                ", appCredential=" + appCredential +
                ", rentInf=" + rentInf +
                ", rentFee=" + rentFee +
                ", refundAmount=" + refundAmount +
                ", totalAmount=" + totalAmount +
                ", paypalPayerId='" + paypalPayerId + '\'' +
                ", paypalPayId='" + paypalPayId + '\'' +
                ", paypalSaleId='" + paypalSaleId + '\'' +
                ", paypalPaymentDate=" + paypalPaymentDate +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentPayment that = (RentPayment) o;

        if (id != that.id) return false;
        if (Double.compare(that.rentFee, rentFee) != 0) return false;
        if (Double.compare(that.refundAmount, refundAmount) != 0) return false;
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (appCredential != null ? !appCredential.equals(that.appCredential) : that.appCredential != null)
            return false;
        if (!rentInf.equals(that.rentInf)) return false;
        if (!paypalPayerId.equals(that.paypalPayerId)) return false;
        if (!paypalPayId.equals(that.paypalPayId)) return false;
        if (!paypalSaleId.equals(that.paypalSaleId)) return false;
        if (!paypalPaymentDate.equals(that.paypalPaymentDate)) return false;
        return createdDate.equals(that.createdDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (appCredential != null ? appCredential.hashCode() : 0);
        result = 31 * result + rentInf.hashCode();
        temp = Double.doubleToLongBits(rentFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(refundAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + paypalPayerId.hashCode();
        result = 31 * result + paypalPayId.hashCode();
        result = 31 * result + paypalSaleId.hashCode();
        result = 31 * result + paypalPaymentDate.hashCode();
        result = 31 * result + createdDate.hashCode();
        return result;
    }
}
