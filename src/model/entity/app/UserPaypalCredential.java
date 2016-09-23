package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/23/16.
 */
@Entity
@Table(name = "user_paypal_credential")
public class UserPaypalCredential {
    private int id;
    private AppCredential appCredential;
    private String email;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        UserPaypalCredential that = (UserPaypalCredential) o;

        if (id != that.id) return false;
        if (appCredential != null ? !appCredential.equals(that.appCredential) : that.appCredential != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return !(createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (appCredential != null ? appCredential.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
