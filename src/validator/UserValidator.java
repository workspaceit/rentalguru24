package validator;

import model.entity.app.User;
import model.entity.app.UserAddress;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by mi on 8/2/16.
 */
public class UserValidator implements Validator {
    private final UserAddressValidator userAddressValidator;

    public UserValidator() {
        this.userAddressValidator = new UserAddressValidator();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User)object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","First name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","Last name required");

        try {
            errors.pushNestedPath("userAddress");
            ValidationUtils.invokeValidator(this.userAddressValidator, user.getUserAddress(), errors);
        } finally {
            errors.popNestedPath();
        }


        if(errors.hasErrors()){
            return;
        }

    }
}
