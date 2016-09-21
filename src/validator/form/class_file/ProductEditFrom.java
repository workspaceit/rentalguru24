package validator.form.class_file;

/**
 * Created by omar on 9/21/16.
 */
public class ProductEditFrom {

    private String name;
    private String description;
    private Long profileImageToken;
    private Double currentValue;
    private Double rentFee;
    private Integer rentTypeId;
    private String availableFrom;
    private String availableTill;
    private boolean reviewStatus;
    private String categoryIdArray;
    private String otherImagesTokenArray;

    private String city;
    private String state;
    private String formattedAddress;
    private String zip;
    private Float lat;
    private Float lng;

    public String getName() {
        return (name==null)?"":name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return (description==null)?"":description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProfileImageToken() {
        return (profileImageToken==null)?-1:profileImageToken;
    }

    public void setProfileImageToken(Long profileImageToken) {
        this.profileImageToken = profileImageToken;
    }

    public Double getCurrentValue() {
        return (currentValue==null)?-1:currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getRentFee() {
        return (rentFee==null)?-1:rentFee;
    }

    public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }

    public Integer getRentTypeId() {
        return (rentTypeId==null)?-1:rentTypeId;
    }

    public void setRentTypeId(Integer rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public String getAvailableFrom() {
        return (availableFrom==null)?"":availableFrom.trim();
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTill() {
        return (availableTill==null)?"":availableTill;
    }

    public void setAvailableTill(String availableTill) {
        this.availableTill = availableTill;
    }

    public boolean isReviewStatus() {
        return (reviewStatus==false)?false:reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getCategoryIdArray() {
        return (categoryIdArray==null)?"":categoryIdArray;
    }

    public void setCategoryIdArray(String categoryIdArray) {
        this.categoryIdArray = categoryIdArray;
    }

    public String getOtherImagesTokenArray() {
        return (otherImagesTokenArray==null)?"":otherImagesTokenArray;
    }

    public void setOtherImagesTokenArray(String otherImagesTokenArray) {
        this.otherImagesTokenArray = otherImagesTokenArray;
    }

    public String getCity() {
        return (city==null)?"":city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return (state==null)?"":state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormattedAddress() {
        return (formattedAddress==null)?"":formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getZip() {
        return (zip==null)?"":zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Float getLat() {
        return (lat==null)?-1:lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return (lng==null)?-1:lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
