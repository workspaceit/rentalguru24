package model.entity.app;

import javax.persistence.*;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "attribute_values", schema = "", catalog = "rentguru24")
public class AttributeValues {
    private int id;
    private int attributesId;
    private String name;
    private int createdBy;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "attributes_id")
    public int getAttributesId() {
        return attributesId;
    }

    public void setAttributesId(int attributesId) {
        this.attributesId = attributesId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeValues that = (AttributeValues) o;

        if (id != that.id) return false;
        if (attributesId != that.attributesId) return false;
        if (createdBy != that.createdBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + attributesId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + createdBy;
        return result;
    }
}
