package validator.form;


import helper.DateHelper;
import model.entity.app.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validator.form.class_file.ProductUploadForm;

/**
 * Created by mi on 8/8/16.
 */
public class ProductUploadFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }
    private static String DATE_FORMAT = "dd-MM-yyyy";
    @Override
    public void validate(Object object, Errors errors) {

        ProductUploadForm productUploadForm = (ProductUploadForm)object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "Product name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description", "Description name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"profileImage", "profile Image  required");
        //errors.rejectValue("otherImages","Other Images  required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"rentFee", "Rent fee required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableFrom", "Available from date required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableTill", "Available till date required");


        if(productUploadForm.getAvailableFrom()!=null){
            if(!DateHelper.isDateValid(productUploadForm.getAvailableFrom(), DATE_FORMAT)){
                errors.rejectValue("availableFrom","Available from date in invalid format");
            }
        }
        if(productUploadForm.getAvailableFrom()!=null){
            if(!DateHelper.isDateValid(productUploadForm.getAvailableTill(), DATE_FORMAT)){
                errors.rejectValue("availableTill","Available till date in invalid format");
            }
        }

    }
}
