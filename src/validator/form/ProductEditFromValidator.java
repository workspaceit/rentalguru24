package validator.form;


import helper.DateHelper;

import model.CategoryModel;
import model.RentTypeModel;
import model.TempFileModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.form.class_file.ProductEditFrom;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * Created by omar on 9/21/16.
 */
public class ProductEditFromValidator implements Validator {
    private CategoryModel categoryModel;
    private TempFileModel tempFileModel;
    private RentTypeModel rentTypeModel;

    public ProductEditFromValidator(CategoryModel categoryModel, TempFileModel tempFileModel,RentTypeModel rentTypeMode) {
        this.categoryModel = categoryModel;
        this.tempFileModel = tempFileModel;
        this.rentTypeModel = rentTypeMode;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductEditFrom.class.equals(aClass);
    }
    private static String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public void validate(Object object, Errors errors){
        ProductEditFrom productEditFrom = (ProductEditFrom)object;

        if(productEditFrom.getProfileImageToken().isEmpty()){
            errors.rejectValue("profileImage", "Product profile Image required");
        }

        if(productEditFrom.getName().isEmpty()){
            errors.rejectValue("name", "Product title required");
        }
        if(productEditFrom.getDescription().isEmpty()){
            errors.rejectValue("description", "Product description required");
        }

        if(productEditFrom.getAvailableFrom().isEmpty() || productEditFrom.getAvailableFrom() == null){
            errors.rejectValue("availableFrom", "Product available from date required");
            if(!DateHelper.isDateValid(productEditFrom.getAvailableFrom(), DATE_FORMAT)){
                errors.rejectValue("availableFrom","Available from date in invalid format");
            }
        }

        if(productEditFrom.getAvailableTill().isEmpty() || productEditFrom.getAvailableTill() == null){
            errors.rejectValue("availableTill", "Product available till date required");
            if(!DateHelper.isDateValid(productEditFrom.getAvailableTill(), DATE_FORMAT)){
                errors.rejectValue("availableTill","Available till date in invalid format");
            }
        }

        if(productEditFrom.getFormattedAddress().isEmpty()){
            errors.rejectValue("fromattedAddress", "Product location required");
        }

        if(productEditFrom.getZip().isEmpty()){
            errors.rejectValue("zip", "Product zip code required");
        }

        if(productEditFrom.getCity().isEmpty()){
            errors.rejectValue("city", "Product city required");
        }

        if(productEditFrom.getCurrentValue() == null || productEditFrom.getCurrentValue() == -1){
            errors.rejectValue("currentValue", "Product current value required");
        }

        if(productEditFrom.getRentFee() == null || productEditFrom.getRentFee() == -1){
            errors.rejectValue("rentFee", "Product rent Fee required");
        }

        if(productEditFrom.getCategoryIdArray().isEmpty()){
            errors.rejectValue("categoryIdArray", "Product category required");
        }else if(this.isJSONValid(productEditFrom.getCategoryIdArray()) == false){
            errors.rejectValue("categoryIdArray", "Product category required");
        }

    }

    public boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
