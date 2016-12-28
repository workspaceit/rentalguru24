package model.nonentity.rent_payment;

/**
 * Created by mi on 12/28/16.
 */
public class RentPaymentSummary {
    private Long totalOrderCount = 0l;
    private Double companyEarning = 0d;
    private Double renteerEarning = 0d;

    public Long getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Long totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Double getCompanyEarning() {
        return companyEarning;
    }

    public void setCompanyEarning(Double companyEarning) {
        this.companyEarning = companyEarning;
    }

    public Double getRenteerEarning() {
        return renteerEarning;
    }

    public void setRenteerEarning(Double renteerEarning) {
        this.renteerEarning = renteerEarning;
    }

    @Override
    public String toString() {
        return "RentPaymentSummary{" +
                "totalOrderCount=" + totalOrderCount +
                ", companyEarning=" + companyEarning +
                ", renteerEarning=" + renteerEarning +
                '}';
    }
}
