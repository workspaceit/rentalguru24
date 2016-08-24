package model.entity.app.product;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 8/24/16.
 */
@Entity
@Table(name = "product_received", schema = "")
public class ProductReceived {
    private int id;
    private int rentProductId;
    private boolean isReceived;
    private Timestamp receivedDate;
    private Timestamp created;

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
    @Column(name = "rent_product_id")
    public int getRentProductId() {
        return rentProductId;
    }

    public void setRentProductId(int rentProductId) {
        this.rentProductId = rentProductId;
    }

    @Basic
    @Column(name = "is_received")
    public boolean getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(boolean isReceived) {
        this.isReceived = isReceived;
    }

    @Basic
    @Column(name = "received_date")
    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }


}
