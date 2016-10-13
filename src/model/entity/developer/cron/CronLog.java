package model.entity.developer.cron;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 10/13/16.
 */
@Entity
@Table(name = "cron_log")
public class CronLog {
    private int id;
    private String description;
    private Timestamp createdDate;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        CronLog cronLog = (CronLog) o;

        if (id != cronLog.id) return false;
        if (description != null ? !description.equals(cronLog.description) : cronLog.description != null) return false;
        return !(createdDate != null ? !createdDate.equals(cronLog.createdDate) : cronLog.createdDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
