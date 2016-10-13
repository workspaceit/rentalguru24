package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.convert.PictureConverter;
import model.nonentity.photo.Picture;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mi on 8/1/16.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "category" ) //catalog = "rentguru24"
public class Category {
    private int id;
    private String name;
    private Integer parentId;
    private int sortedOrder;
    private Picture picture;
    private int createdBy;
    private boolean isSubcategory;
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

    @JsonIgnore
    @Basic
    @Column(name = "is_subcategory")
    public boolean getIsSubcategory() {
        return isSubcategory;
    }

    public void setIsSubcategory(boolean isSubcategory) {
        this.isSubcategory = isSubcategory;
    }

    @Basic
    @Column(name = "sorted_order")
    public int getSortedOrder() {
        return sortedOrder;
    }

    public void setSortedOrder(int sortedOrder) {
        this.sortedOrder = sortedOrder;
    }

    @Basic
    @Column(name = "picture")
    @Convert(converter = PictureConverter.class)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "created_by")
    @JsonIgnore
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    @JsonIgnore
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
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

}
