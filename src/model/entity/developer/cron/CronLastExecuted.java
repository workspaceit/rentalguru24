package model.entity.developer.cron;

import javax.persistence.*;

/**
 * Created by mi on 10/13/16.
 */
@Entity
@Table(name = "cron_last_executed")
public class CronLastExecuted {
    private int id;
    private int rentRequestId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rent_request_id")
    public int getRentRequestId() {
        return rentRequestId;
    }

    public void setRentRequestId(int rentRequest) {
        this.rentRequestId = rentRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CronLastExecuted that = (CronLastExecuted) o;

        if (id != that.id) return false;
        if (rentRequestId != that.rentRequestId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rentRequestId;
        return result;
    }
}
