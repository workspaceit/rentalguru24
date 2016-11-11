package validator.form.class_file;

import helper.UtilityHelper;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Arrays;

/**
 * Created by mi on 8/8/16.
 */
public class ProductUploadForm {
    private String name;
    private String description;
    private long profileImageToken;
    private double currentValue;
    private double rentFee;
    private Integer rentTypeId;
    private boolean active;
    private boolean currentlyAvailable;
    private String availableFrom;
    private String availableTill;
    private boolean reviewStatus;
    private Integer[] categoryIdArray;
    private int subcategoryId; // No functionality , only using for send error in parameter
    private Long[] otherImagesTokenArray;



    private String city;
    private String state;
    private String formattedAddress;
    private String zip;
    private Double lat;
    private Double lng;

    public String getName() {

        return StringEscapeUtils.escapeHtml3(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return StringEscapeUtils.escapeHtml3(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProfileImageToken() {
        return profileImageToken;
    }

    public void setProfileImageToken(long profileImageToken) {
        this.profileImageToken = profileImageToken;
    }



    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getRentFee() {
        return rentFee;
    }

    public void setRentFee(double rentFee) {
        this.rentFee = rentFee;
    }

    public Integer getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(Integer rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCurrentlyAvailable() {
        return currentlyAvailable;
    }

    public void setCurrentlyAvailable(boolean currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        availableFrom = (availableFrom!=null)?availableFrom.trim():availableFrom;
        this.availableFrom = availableFrom;
    }

    public String getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(String availableTill) {
        availableTill = (availableTill!=null)?availableTill.trim():availableTill;
        this.availableTill = availableTill;
    }

    public boolean isReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Integer[] getCategoryIdArray() {
        return categoryIdArray;
    }

    public void setCategoryIdArray(Integer[] categoryIdArray) {
        this.categoryIdArray = categoryIdArray;
    }


    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getCity() {
        return StringEscapeUtils.escapeHtml3(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormattedAddress() {
        return StringEscapeUtils.escapeHtml3(formattedAddress);
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getZip() {
        return StringEscapeUtils.escapeHtml3(zip);
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLat() {
        return (lat!=null)? UtilityHelper.round(lat, 13):null;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return (lng!=null)? UtilityHelper.round(lng, 13):null;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Long[] getOtherImagesTokenArray() {
        return otherImagesTokenArray;
    }

    public void setOtherImagesTokenArray(Long[] otherImagesTokenArray) {
        this.otherImagesTokenArray = otherImagesTokenArray;
    }


    @Override
    public String toString() {
        return "ProductUploadForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", profileImageToken=" + profileImageToken +
                ", currentValue=" + currentValue +
                ", rentFee=" + rentFee +
                ", rentTypeId=" + rentTypeId +
                ", active=" + active +
                ", currentlyAvailable=" + currentlyAvailable +
                ", availableFrom='" + availableFrom + '\'' +
                ", availableTill='" + availableTill + '\'' +
                ", reviewStatus=" + reviewStatus +
                ", categoryIdArray=" + Arrays.toString(categoryIdArray) +
                ", otherImagesTokenArray=" + Arrays.toString(otherImagesTokenArray) +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + getLat() +
                ", lng=" + getLng() +
                '}';
    }
}
