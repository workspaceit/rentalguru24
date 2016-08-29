package model.entity.admin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/29/16.
 */
@Entity
@Table(name = "admin_site_fees", schema = "")
public class AdminSiteFeesEntity {
    private int id;
    private boolean fixed;
    private boolean percentage;
    private float fixedValue;
    private float percentageValue;
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
    @Column(name = "fixed")
    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    @Basic
    @Column(name = "percentage")
    public boolean isPercentage() {
        return percentage;
    }

    public void setPercentage(boolean percentage) {
        this.percentage = percentage;
    }

    @Basic
    @Column(name = "fixed_value")
    public float getFixedValue() {
        return fixedValue;
    }

    public void setFixedValue(float fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Basic
    @Column(name = "percentage_value")
    public float getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(float percentageValue) {
        this.percentageValue = percentageValue;
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

        AdminSiteFeesEntity that = (AdminSiteFeesEntity) o;

        if (id != that.id) return false;
        if (fixed != that.fixed) return false;
        if (percentage != that.percentage) return false;
        if (Float.compare(that.fixedValue, fixedValue) != 0) return false;
        if (Float.compare(that.percentageValue, percentageValue) != 0) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fixed ? 1 : 0);
        result = 31 * result + (percentage ? 1 : 0);
        result = 31 * result + (fixedValue != +0.0f ? Float.floatToIntBits(fixedValue) : 0);
        result = 31 * result + (percentageValue != +0.0f ? Float.floatToIntBits(percentageValue) : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
