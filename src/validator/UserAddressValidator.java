package validator;

import model.entity.app.UserAddress;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by mi on 7/26/16.
 */
public class UserAddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserAddress.class.equals(aClass);
    }
    @Override
    public void validate(Object object, Errors errors) {
        UserAddress userAddress = (UserAddress)object;
        ValidationUtils.rejectIfEmpty(errors,"city","City is required");
        ///errors.rejectValue("city","City is required");
    }
}
