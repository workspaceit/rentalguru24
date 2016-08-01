package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by mi on 8/1/16.
 */
@Entity
public class Attributes {
    private int id;
    private String name;
    private int createdBy;
    private Timestamp createdDate;
    private Collection<AttributeValues> attributeValuesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "created_by")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

        Attributes that = (Attributes) o;

        if (id != that.id) return false;
        if (createdBy != that.createdBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "id", nullable = false)
    public Collection<AttributeValues> getAttributeValuesById() {
        return attributeValuesById;
    }

    public void setAttributeValuesById(Collection<AttributeValues> attributeValuesById) {
        this.attributeValuesById = attributeValuesById;
    }
}
