package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/1/16.
 */
@Entity
@Table(name = "product_category", schema = "", catalog = "rentguru24")
public class ProductCategory {
    private int id;
    private int productId;
    private int categoryId;
    private Timestamp createdDate;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "product_id")
//    public int getProductId() {
//        return productId;
//    }

    public void setProductId(int productId) {
        this.productId = productId;
    }




    @Basic
    @JsonIgnore
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

        ProductCategory that = (ProductCategory) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (categoryId != that.categoryId) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + categoryId;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
