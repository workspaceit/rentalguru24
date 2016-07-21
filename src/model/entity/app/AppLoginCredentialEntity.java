package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by mi on 7/20/16.
 */
@Entity
@Table(name = "app_login_credential", schema = "", catalog = "rentguru24")
public class AppLoginCredentialEntity {
    private int id;
    private int userInfId;
    private String email;
    private String password;
    private String accessToken;
    private Timestamp createdDate;
    private UserInfEntity userInfByUserInfId;
    private Collection<DeviceInfoEntity> deviceInfosById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_inf_id")
    public int getUserInfId() {
        return userInfId;
    }

    public void setUserInfId(int userInfId) {
        this.userInfId = userInfId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @JsonIgnore
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

        AppLoginCredentialEntity that = (AppLoginCredentialEntity) o;

        if (id != that.id) return false;
        if (userInfId != that.userInfId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userInfId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_inf_id", referencedColumnName = "id",  insertable=false, updatable=false)
    public UserInfEntity getUserInfByUserInfId() {
        return userInfByUserInfId;
    }

    public void setUserInfByUserInfId(UserInfEntity userInfByUserInfId) {
        this.userInfByUserInfId = userInfByUserInfId;
    }

    @OneToMany
    @JoinColumn(name = "app_credential_id",  insertable=false, updatable=false)
    public Collection<DeviceInfoEntity> getDeviceInfosById() {
        return deviceInfosById;
    }

    public void setDeviceInfosById(Collection<DeviceInfoEntity> deviceInfosById) {
        this.deviceInfosById = deviceInfosById;
    }
}
