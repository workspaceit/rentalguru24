package validator.entity;

import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mi on 8/2/16.
 */
public class AuthCredentialValidator implements Validator {
    private final UserValidator userValidator;
    private AppLoginCredentialModel appLoginCredentialModel;
    private boolean insertValidation;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public AuthCredentialValidator(AppLoginCredentialModel appLoginCredentialModel, IdentityTypeModel identityTypeModel, boolean insertValidation) {
        this.appLoginCredentialModel = appLoginCredentialModel;
        this.userValidator = new UserValidator(identityTypeModel);
        this.insertValidation = insertValidation;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AppCredential.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        AuthCredential authCredential = (AuthCredential)object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password is required");


        if(errors.getFieldErrorCount("password")==0 && authCredential.getPassword().length()<6){
            errors.rejectValue("password","Password at least 6 character required");
        }

        try {
            errors.pushNestedPath("userInf");
            ValidationUtils.invokeValidator(this.userValidator, authCredential.getUserInf(), errors);
        }finally {
            errors.popNestedPath();
        }

        if(errors.hasErrors()){
            return;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(authCredential.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "Email is not valid format");
        }

        if(this.insertValidation){
            if(appLoginCredentialModel.isEmailExist(authCredential.getEmail())){
                errors.rejectValue("email", "Email is already been used");
            }
        }


    }
}
