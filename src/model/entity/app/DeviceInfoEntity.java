package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 7/20/16.
 */
@Entity
@Table(name = "device_info", schema = "", catalog = "rentguru24")
public class DeviceInfoEntity {
    private int id;
    private int appCredentialId;
    private String deviceId;
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
    @Column(name = "app_credential_id")
    public int getAppCredentialId() {
        return appCredentialId;
    }

    public void setAppCredentialId(int appCredentialId) {
        this.appCredentialId = appCredentialId;
    }

    @Basic
    @Column(name = "device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

        DeviceInfoEntity that = (DeviceInfoEntity) o;

        if (id != that.id) return false;
        if (appCredentialId != that.appCredentialId) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + appCredentialId;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }


}
