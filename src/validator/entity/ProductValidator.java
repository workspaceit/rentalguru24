package validator.entity;


import model.entity.app.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mi on 8/8/16.
 */
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Product product = (Product)object;

        errors.rejectValue("name","Product name required");
        errors.rejectValue("description","Description name required");
        errors.rejectValue("profileImage","profile Image  required");
        //errors.rejectValue("otherImages","Other Images  required");
        errors.rejectValue("rentFee","Rent fee required");
        errors.rejectValue("availableFrom","product name required");
        errors.rejectValue("availableTill","product name required");


    }
}
