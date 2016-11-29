package validator.form.class_file;

import helper.UtilityHelper;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Arrays;

/**
 * Created by omar on 9/21/16.
 */
public class ProductEditFrom {

    private String name;
    private String description;
    private Float currentValue;
    private Float rentFee;
    private String availableFrom;
    private String availableTill;
    private Integer[] categoryIdArray;
    private Integer  subcategoryId;
    private Integer rentTypeId;

    private String city;
    private Integer stateId;
    private String formattedAddress;
    private String zip;
    private Double lat;
    private Double lng;


    public ProductEditFrom() {
        this.subcategoryId = 0;
    }

    public String getName() {
        name = (name!=null)?name.trim():"";
        return StringEscapeUtils.escapeHtml3(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        description = (description!=null)?description.trim():"";
        return StringEscapeUtils.escapeHtml3(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public Float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Float currentValue) {
        this.currentValue = currentValue;
    }

    public Float getRentFee() {
        return rentFee;
    }

    public void setRentFee(Float rentFee) {
        this.rentFee = rentFee;
    }

    public Integer getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(Integer rentTypeId) {
        this.rentTypeId = rentTypeId;
    }


    public String getAvailableFrom() {
        availableFrom = (availableFrom!=null)?availableFrom.trim():"";
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTill() {
        availableTill = (availableTill!=null)?availableTill.trim():"";
        return availableTill;
    }

    public void setAvailableTill(String availableTill) {
        this.availableTill = availableTill;
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
        city = (city!=null)?city.trim():"";
        return StringEscapeUtils.escapeHtml3(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getFormattedAddress() {
        formattedAddress = (formattedAddress!=null) ? formattedAddress.trim():"";
        return StringEscapeUtils.escapeHtml3(formattedAddress);
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getZip() {
        zip = (zip!=null)?zip.trim():"";
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


    @Override
    public String toString() {
        return "ProductEditFrom{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentValue=" + currentValue +
                ", rentFee=" + rentFee +
                ", availableFrom='" + availableFrom + '\'' +
                ", availableTill='" + availableTill + '\'' +
                ", categoryIdArray=" + Arrays.toString(categoryIdArray) +
                ", subcategoryId=" + subcategoryId +
                ", rentTypeId=" + rentTypeId +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
