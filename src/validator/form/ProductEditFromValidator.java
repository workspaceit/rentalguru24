package validator.form;

import helper.DateHelper;
import helper.ImageHelper;
import model.CategoryModel;
import model.RentTypeModel;
import model.TempFileModel;
import model.entity.app.TempFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.form.class_file.ProductEditFrom;


/**
 * Created by omar on 9/21/16.
 */
public class ProductEditFromValidator implements Validator {
    private CategoryModel categoryModel;
    private TempFileModel tempFileModel;
    private RentTypeModel rentTypeModel;

    public ProductEditFromValidator(CategoryModel categoryModel, TempFileModel tempFileModel,RentTypeModel rentTypeMode) {
        this.categoryModel = categoryModel;
        this.tempFileModel = tempFileModel;
        this.rentTypeModel = rentTypeMode;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductEditFrom.class.equals(aClass);
    }
    private static String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public void validate(Object object, Errors errors){
        ProductEditFrom productEditFrom = (ProductEditFrom)object;


        if(productEditFrom.getName().isEmpty()){
            errors.rejectValue("name", "Product title required");
        }
        if(productEditFrom.getDescription().isEmpty()){
            errors.rejectValue("description", "Product description required");
        }

        if(productEditFrom.getAvailableFrom().isEmpty() || productEditFrom.getAvailableFrom() == null){
            errors.rejectValue("availableFrom", "Product available from date required");
            if(!DateHelper.isDateValid(productEditFrom.getAvailableFrom(), DATE_FORMAT)){
                errors.rejectValue("availableFrom","Available from date in invalid format");
            }
        }

        if(productEditFrom.getAvailableTill().isEmpty() || productEditFrom.getAvailableTill() == null){
            errors.rejectValue("availableTill", "Product available till date required");
            if(!DateHelper.isDateValid(productEditFrom.getAvailableTill(), DATE_FORMAT)){
                errors.rejectValue("availableTill","Available till date in invalid format");
            }
        }

        if(productEditFrom.getFormattedAddress().isEmpty()){
            errors.rejectValue("fromattedAddress", "Product location required");
        }

        if(productEditFrom.getZip().isEmpty()){
            errors.rejectValue("zip", "Product zip code required");
        }

        if(productEditFrom.getCity().isEmpty()){
            errors.rejectValue("city", "Product city required");
        }

        if(productEditFrom.getCurrentValue() == null || productEditFrom.getCurrentValue() == -1){
            errors.rejectValue("currentValue", "Product current value required");
        }

        if(productEditFrom.getRentFee() == null || productEditFrom.getRentFee() == -1){
            errors.rejectValue("rentFee", "Product rent Fee required");
        }

        if(productEditFrom.getProfileImageToken() == null || productEditFrom.getProfileImageToken() == -1){
            errors.rejectValue("profileImagetoken", "Product profile image required");

        }

        if(productEditFrom.getCategoryIdArray().length>0){
            try{
                Integer[] catArray = productEditFrom.getCategoryIdArray();
                for(int catId : catArray){
                    if(this.categoryModel.getById(catId)==null){
                        errors.rejectValue("categoryIdArray","Category not found for id = "+catId);
                        break;
                    }
                }
            }catch (Exception ex){
                errors.rejectValue("categoryIdArray","Category not in valid format");
            }

        }

        if(productEditFrom.getOtherImagesTokenArray()!=null){
            for(long otherImageToken : productEditFrom.getOtherImagesTokenArray()){
                TempFile tempFile = this.tempFileModel.getByToken(otherImageToken);
                if(tempFile ==null){
                    errors.rejectValue("otherImagesTokenArray", "Other image token is not valid");
                    break;
                }

                if(!ImageHelper.isFileExist(tempFile.getPath())){
                    errors.rejectValue("otherImagesTokenArray", "No file found associated with the token");
                    break;
                }
            }
        }

        if(productEditFrom.getRentTypeId()!=null){
            if(rentTypeModel.getById(productEditFrom.getRentTypeId())==null){
                errors.rejectValue("rentTypeId", "No rent type found by id  "+productEditFrom.getRentTypeId());
            }
        }
    }
}
