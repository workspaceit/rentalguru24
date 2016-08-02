package validator;

import model.AppCredentialModel;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mi on 8/2/16.
 */
public class AppCredentialValidator implements Validator {
    private final UserValidator userValidator;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private AppCredentialModel appCredentialModel;
    public AppCredentialValidator(AppCredentialModel appCredentialModel) {
        this.appCredentialModel = appCredentialModel;
        this.userValidator = new UserValidator();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AppCredential.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        AppCredential appCredential = (AppCredential)object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Password is required");


        try {
            errors.pushNestedPath("user");
            ValidationUtils.invokeValidator(this.userValidator, appCredential.getUser(), errors);
        }finally {
            errors.popNestedPath();
        }

        if(errors.hasErrors()){
            return;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(appCredential.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "Email is not valid format");
        }


        if(appCredentialModel.isEmailExist(appCredential.getEmail())){
            errors.rejectValue("email", "Email is already been used");
        }

    }
}
