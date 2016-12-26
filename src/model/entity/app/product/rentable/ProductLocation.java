package model.entity.app.product.rentable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import model.entity.Cities;
import model.entity.State;
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
    private String formattedAddress;
    private String zip;
    private Double lat = null;
    private Double lng = null;
    private State state;
    private Cities city;
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



    @OneToOne
    @JoinColumn(name = "state_id",referencedColumnName = "id")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
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
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lng")
    @Longitude(of="home")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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
