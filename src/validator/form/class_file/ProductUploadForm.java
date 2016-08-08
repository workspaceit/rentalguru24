package validator.form.class_file;

/**
 * Created by mi on 8/8/16.
 */
public class ProductUploadForm {
    private String name;
    private String description;
    private long profileImageToken;
    private String otherImages;
    private double currentValue;
    private double rentFee;
    private boolean active;
    private boolean currentlyAvailable;
    private String availableFrom;
    private String availableTill;
    private boolean reviewStatus;
    private Long[] categoryIdArray;
    private String createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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

    public String getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(String otherImages) {
        this.otherImages = otherImages;
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
        this.availableFrom = availableFrom;
    }

    public String getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(String availableTill) {
        this.availableTill = availableTill;
    }

    public boolean isReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Long[] getCategoryIdArray() {
        return categoryIdArray;
    }

    public void setCategoryIdArray(Long[] categoryIdArray) {
        this.categoryIdArray = categoryIdArray;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
