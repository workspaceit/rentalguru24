package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 10/19/16.
 */
@Entity
@Table(name = "email_confirmation")
public class EmailConfirmation {
    private int id;
    private AppCredential appCredential;
    private String token;
    private Boolean alreadyUsed;
    private Timestamp createdDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "app_credential_id",referencedColumnName = "id")
    public AppCredential getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(AppCredential appCredential) {
        this.appCredential = appCredential;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "already_used")
    public Boolean getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(Boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }
}
