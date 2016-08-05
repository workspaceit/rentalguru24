package validator.entity;

import model.IdentityTypeModel;
import model.entity.app.IdentityType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by mi on 8/3/16.
 */
public class IdentityTypeValidator  implements Validator {
    IdentityTypeModel identityTypeModel;

    public IdentityTypeValidator(IdentityTypeModel identityTypeModel) {
        this.identityTypeModel = identityTypeModel;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return IdentityType.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        IdentityType identityType =(IdentityType) object;
        if(identityType.getId()!=0){
            if(!this.identityTypeModel.isExist(identityType.getId())){
                errors.rejectValue("id", "Identity type id is not valid");
            }
        }

    }
}
