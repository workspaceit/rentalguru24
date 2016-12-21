package validator.form;

import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.IdentityType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validator.entity.UserValidator;
import validator.form.class_file.SignUpForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mi on 8/2/16.
 */
public class SignUpFormValidator implements Validator {
    private AppLoginCredentialModel appLoginCredentialModel;
    private IdentityTypeModel identityTypeModel;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public SignUpFormValidator(AppLoginCredentialModel appLoginCredentialModel, IdentityTypeModel identityTypeModel) {
        this.appLoginCredentialModel = appLoginCredentialModel;
        this.identityTypeModel = identityTypeModel;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SignUpForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","First name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","Last name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber","Phone Number required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address","Address required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"city","City required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"state","State required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"zip","Zip required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"gender","Gender required");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors,"identityDocToken", "Identity doc token required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"identityTypeId", "Identity type id required");
        if(errors.getFieldErrorCount("password")==0 && signUpForm.getPassword().length()<6){
            errors.rejectValue("password","Password at least 6 character required");
        }

        if(errors.hasErrors()){
            return;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(signUpForm.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "Email is not in valid format");
        }

        if(appLoginCredentialModel.isEmailExist(signUpForm.getEmail())){
            errors.rejectValue("email", "Email is already been used");
        }


//        if(!this.identityTypeModel.isExist(signUpForm.getIdentityTypeId())){
//            errors.rejectValue("id", "Identity type id does not exist");
//        }


    }
}
