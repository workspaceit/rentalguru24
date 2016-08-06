package model.entity.app;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mi on 8/1/16.
 */
@Entity
public class Category {
    private int id;
    private String name;
    private Integer parentId;
    private int sortedOrder;
    private int createdBy;
    private Timestamp createdDate;
    private Collection<Category> subcategory;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Basic
//    @Column(name = "parent_id")
//    public Category getParentId() {
//        return parentId;
//    }
//
//    public void setParent(Category parent) {
//        this.parentId = parent;
//    }

    @Basic
    @Column(name = "sorted_order")
    public int getSortedOrder() {
        return sortedOrder;
    }

    public void setSortedOrder(int sortedOrder) {
        this.sortedOrder = sortedOrder;
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

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    public Collection<Category> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Collection<Category> subcategory) {
        this.subcategory = subcategory;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (sortedOrder != category.sortedOrder) return false;
        if (createdBy != category.createdBy) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (parentId != null ? !parentId.equals(category.parentId) : category.parentId != null) return false;
        if (createdDate != null ? !createdDate.equals(category.createdDate) : category.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + sortedOrder;
        result = 31 * result + createdBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }



//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//
//    @OneToMany(mappedBy = "Category")
//    public Collection<Category> getSubcategory() {
//        return subcategory;
//    }
//
//    public void setSubcategory(Collection<Category> subcategory) {
//        this.subcategory = subcategory;
//    }

//    @OneToMany(mappedBy = "categoryByParentId")
//    public Collection<Category> getSubcategory() {
//        return subcategory;
//    }
//
//    public void setSubcategory(Collection<Category> subcategory) {
//        this.subcategory = subcategory;
//    }


}
