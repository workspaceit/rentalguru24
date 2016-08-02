package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/2/16.
 */
@Entity
@Table(name = "temp_identity_doc", schema = "", catalog = "rentguru24")
public class IdentityDoc {
    private int id;
    private int token;
    private String path;
    private Timestamp createdDate;

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
    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

        IdentityDoc that = (IdentityDoc) o;

        if (id != that.id) return false;
        if (token != that.token) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + token;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
