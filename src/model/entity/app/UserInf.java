package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String phoneNumber;
    private String gender;
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
        this.phoneNumber = "";
        this.gender = "";
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
    @Column(name = "phone_number")
    @JsonIgnore
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "gender")
    @JsonIgnore
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_address_id", referencedColumnName = "id", nullable = false)
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

    @Override
    public String toString() {
        return "UserInf{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", profilePicture=" + profilePicture +
                ", identityDocUrl='" + identityDocUrl + '\'' +
                ", createdDate=" + createdDate +
                ", userAddress=" + userAddress +
                ", identityType=" + identityType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInf userInf = (UserInf) o;

        if (id != userInf.id) return false;
        if (addressId != null ? !addressId.equals(userInf.addressId) : userInf.addressId != null) return false;
        if (firstName != null ? !firstName.equals(userInf.firstName) : userInf.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userInf.lastName) : userInf.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(userInf.phoneNumber) : userInf.phoneNumber != null) return false;
        if (gender != null ? !gender.equals(userInf.gender) : userInf.gender != null) return false;
        if (profilePicture != null ? !profilePicture.equals(userInf.profilePicture) : userInf.profilePicture != null)
            return false;
        if (identityDocUrl != null ? !identityDocUrl.equals(userInf.identityDocUrl) : userInf.identityDocUrl != null)
            return false;
        if (createdDate != null ? !createdDate.equals(userInf.createdDate) : userInf.createdDate != null) return false;
        if (userAddress != null ? !userAddress.equals(userInf.userAddress) : userInf.userAddress != null) return false;
        return !(identityType != null ? !identityType.equals(userInf.identityType) : userInf.identityType != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        result = 31 * result + (identityDocUrl != null ? identityDocUrl.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (identityType != null ? identityType.hashCode() : 0);
        return result;
    }
}
