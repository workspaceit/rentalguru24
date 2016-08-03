package validator;

import model.IdentityTypeModel;
import model.entity.app.UserInf;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by mi on 8/2/16.
 */
public class UserValidator implements Validator {
    private final UserAddressValidator userAddressValidator;
    private final IdentityTypeValidator identityTypeValidator;
    private IdentityTypeModel identityTypeModel;

    public UserValidator(IdentityTypeModel identityTypeModel) {
        this.userAddressValidator = new UserAddressValidator();
        this.identityTypeModel = identityTypeModel;
        this.identityTypeValidator = new IdentityTypeValidator(this.identityTypeModel);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserInf.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserInf userInf = (UserInf)object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","First name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","Last name required");

//        try {
//            errors.pushNestedPath("userAddress");
//            ValidationUtils.invokeValidator(this.userAddressValidator, user.getUserAddress(), errors);
//        } finally {
//            errors.popNestedPath();
//        }
        userInf.getIdentityType();
        try {
            errors.pushNestedPath("identityType");
            ValidationUtils.invokeValidator(this.identityTypeValidator, userInf.getIdentityType(), errors);
        }finally {
            errors.popNestedPath();
        }
        if(errors.hasErrors()){
            return;
        }

    }
}
