package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by MI on 08-Aug-16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "product_location" ) // catalog = "rentguru24"
@Indexed
@Spatial(name ="home")
public class ProductLocation {
    private int id;
    private int productId;
    private String city;
    private String state;
    private String formattedAddress;
    private String zip;
    private Double latitude = null;
    private Double longitude = null;
    private Timestamp createdDate;

    private RentalProduct rentalProduct;

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
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "formatted_address")
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    @Basic
    @Column(name = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    @Basic
    @Column(name = "lat")
    @Latitude(of="home")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double lat) {
        this.latitude = lat;
    }

    @Basic
    @Column(name = "lng")
    @Longitude(of="home")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double lng) {
        this.longitude = lng;
    }

    @JsonIgnore
    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    @OneToOne(targetEntity = RentalProductEntity.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public RentalProduct getRentalProduct() {
        return rentalProduct;
    }

    public void setRentalProduct(RentalProduct rentalProduct) {
        this.rentalProduct = rentalProduct;
    }
}
