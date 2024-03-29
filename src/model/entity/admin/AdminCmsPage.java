package model.entity.admin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 9/8/16.
 */
@Entity
@Table(name = "admin_cms_page", schema = "")
public class AdminCmsPage {
    private int id;
    private String pageKey;
    private String pageName;
    private String pageContent;
    private int sortedOrder;
    private Timestamp createdDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "page_key")
    public String getPageKey() {
        return pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    @Basic
    @Column(name = "page_name")
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Basic
    @Column(name = "page_content")
    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
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
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
