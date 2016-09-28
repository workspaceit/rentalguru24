package model.entity.admin;

import javax.persistence.*;

/**
 * Created by mi on 9/28/16.
 */
@Entity
@Table(name = "admin_unread_alert_count")
public class AdminUnreadAlertCount {
    private int id;
    private int globalNotification;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "global_notification")
    public int getGlobalNotification() {
        return globalNotification;
    }

    public void setGlobalNotification(int globalNotification) {
        this.globalNotification = globalNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminUnreadAlertCount that = (AdminUnreadAlertCount) o;

        if (id != that.id) return false;
        if (globalNotification != that.globalNotification) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + globalNotification;
        return result;
    }
}
