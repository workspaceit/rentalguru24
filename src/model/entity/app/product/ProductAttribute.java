package model.entity.app.product;

import model.entity.app.AttributeValues;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "product_attribute", schema = "") //, catalog = "rentguru24"
public class ProductAttribute {
    private int id;
    private int productId;
    private int attributeValuesId;
    private Timestamp createdDate;
    private String description;
    private AttributeValues attributeValues;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "attribute_values_id")
    public int getAttributeValuesId() {
        return attributeValuesId;
    }

    public void setAttributeValuesId(int attributeValuesId) {
        this.attributeValuesId = attributeValuesId;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductAttribute that = (ProductAttribute) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (attributeValuesId != that.attributeValuesId) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + attributeValuesId;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "attribute_values_id", referencedColumnName = "id", nullable = false)
//    public AttributeValues getAttributeValues() {
//        return attributeValues;
//    }

    public void setAttributeValues(AttributeValues attributeValues) {
        this.attributeValues = attributeValues;
    }
}
