package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/4/16.
 */
@MappedSuperclass
public class AbstractCredential {
    protected int id;
    protected int userId;
    protected int role;
    protected String email;

    protected Timestamp createdDate;
    protected UserInf userInf;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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


    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_inf_id", referencedColumnName = "id", nullable = false)
    public UserInf getUserInf() {
        return userInf;
    }

    public void setUserInf(UserInf userInf) {
        this.userInf = userInf;
    }

    @Override
    public String toString() {
        return "AbstractCredential{" +
                "id=" + id +
                ", userId=" + userId +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", userInf=" + userInf +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCredential that = (AbstractCredential) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (role != that.role) return false;
        if (!email.equals(that.email)) return false;
        if (!createdDate.equals(that.createdDate)) return false;
        return !(userInf != null ? !userInf.equals(that.userInf) : that.userInf != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + role;
        result = 31 * result + email.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + (userInf != null ? userInf.hashCode() : 0);
        return result;
    }
}
