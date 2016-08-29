package validator.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validator.form.class_file.ProfileForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mi on 8/25/16.
 */
public class ProfileValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ProfileForm profileForm = (ProfileForm)object;
        if(!profileForm.getEmail().isEmpty()){
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(profileForm.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "Email is not in valid format");
            }
        }

        if(!profileForm.getNewPassword().isEmpty()){
            if(profileForm.getNewPassword().length()<6){
                errors.rejectValue("newPassword", "Password length must be at least 6 digit");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"oldPassword", "Old password required");
        }



    }
}
