package model.entity.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.convert.PictureConverter;
import model.nonentity.photo.Picture;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 7/25/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user_inf", schema = "") //, catalog = "rentguru24"
public class UserInf {
    private int id;
    private Integer addressId;
    private String firstName;
    private String lastName;
    private Picture profilePicture;
    private String identityDocUrl;
    private Timestamp createdDate;
    private UserAddress userAddress;
    private IdentityType identityType;

    public UserInf() {
        this.id = 0;
        this.addressId = 0;
        this.firstName = "";
        this.lastName = "";
        this.identityDocUrl = "";
        this.createdDate = null;
        this.userAddress = new UserAddress();
        this.identityType = new IdentityType();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "user_address_id")
//    public int getAddressId() {
//        return addressId;
//    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "profile_pic")
    @Convert(converter = PictureConverter.class)
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Basic
    @Column(name="identity_doc_path")
    public String getIdentityDocUrl() {
        return identityDocUrl;
    }

    public void setIdentityDocUrl(String identityDocUrl) {
        this.identityDocUrl = identityDocUrl;
    }




    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_address_id", referencedColumnName = "id", nullable = true)
    public UserAddress getUserAddress() {
        return userAddress;
    }


    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identity_type_id", referencedColumnName = "id", nullable = false)
    public IdentityType getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType;
    }
}
