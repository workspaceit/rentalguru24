package model.entity.admin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/28/16.
 */
@Entity
@Table(name = "admin_global_notification")
public class AdminGlobalNotification {
    private int id;
    private String details;
    private String type;
    private boolean isRead;
    private Timestamp createdDate;
    private AdminGlobalNotificationTemplate NotificationTemplate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "is_read")
    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
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
    @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false)
    public AdminGlobalNotificationTemplate getNotificationTemplate() {
        return NotificationTemplate;
    }

    public void setNotificationTemplate(AdminGlobalNotificationTemplate notificationTemplate) {
        NotificationTemplate = notificationTemplate;
    }
}
