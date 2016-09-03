package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/3/16.
 */
@Entity
@Table(name = "password_resets", schema = "")
public class PasswordResetsEntity {
    private int id;
    private String token;
    private Timestamp createdAt;
//    private AppCredential appCredential;
    private AuthCredential authCredential;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @OneToOne
    @JoinColumn(name = "app_credential_id", referencedColumnName = "id", nullable = false)
//    public AppCredential getAppCredential(){return appCredential;}
    public AuthCredential getAuthCredential(){return authCredential;}

//    public void setAppCredential(AppCredential appCredential){this.appCredential = appCredential;}
    public void setAuthCredential(AuthCredential authCredential){this.authCredential = authCredential;}
}
