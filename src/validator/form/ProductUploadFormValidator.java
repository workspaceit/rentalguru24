package validator.form;


import helper.DateHelper;
import helper.ImageHelper;
import model.CategoryModel;
import model.RentTypeModel;
import model.TempFileModel;
import model.entity.app.Category;
import model.entity.app.TempFile;
import model.entity.app.product.rentable.RentalProductEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validator.form.class_file.ProductUploadForm;

/**
 * Created by mi on 8/8/16.
 */
public class ProductUploadFormValidator implements Validator {
    private CategoryModel categoryModel;
    private TempFileModel tempFileModel;
    private RentTypeModel rentTypeModel;

    public ProductUploadFormValidator(CategoryModel categoryModel, TempFileModel tempFileModel,RentTypeModel rentTypeMode) {
        this.categoryModel = categoryModel;
        this.tempFileModel = tempFileModel;
        this.rentTypeModel = rentTypeMode;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RentalProductEntity.class.equals(aClass);
    }
    private static String DATE_FORMAT = "dd-MM-yyyy";
    @Override
    public void validate(Object object, Errors errors) {

        ProductUploadForm productUploadForm = (ProductUploadForm)object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "Product name required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description", "Description required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"profileImageToken", "profile Image  required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"stateId", "State required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"rentFee", "Rent fee required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableFrom", "Available from date required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availableTill", "Available till date required");

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "City is required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "State is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "Zip is required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lat", "Lat is required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lng", "Lng is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formattedAddress", "Formatted address required");

        if(productUploadForm.getStateId()!=null && productUploadForm.getStateId()>0){
            errors.rejectValue("stateId","State required");
        }
        if(productUploadForm.getAvailableFrom()!=null && !productUploadForm.getAvailableFrom().isEmpty()){

            if(!DateHelper.isDateValid(productUploadForm.getAvailableFrom(), DATE_FORMAT)){
                errors.rejectValue("availableFrom","Available from date in invalid format");
            }
        }
        if(productUploadForm.getAvailableTill()!=null && !productUploadForm.getAvailableTill().isEmpty()){
            if(!DateHelper.isDateValid(productUploadForm.getAvailableTill(), DATE_FORMAT)){
                errors.rejectValue("availableTill","Available till date in invalid format");
            }
        }
        if(productUploadForm.getCategoryIdArray().length>0){
            try{
                Integer[] catArray = productUploadForm.getCategoryIdArray();


                for(int catId : catArray){
                    Category category = categoryModel.getById(catId);
                    if(category==null){
                        errors.rejectValue("categoryIdArray","Category not found for id = "+catId);
                        break;
                    }
                    if(!category.getIsSubcategory()){
                        if(category.getSubcategory()!=null && category.getSubcategory().size()>0) {
                            errors.rejectValue("subcategoryId","Please select subcategory");
                            break;
                        }
                    }
                }
            }catch (Exception ex){
                errors.rejectValue("categoryIdArray","Category not in valid format");
            }

        }


          /*----- Other images token validation check ---- */
        if(productUploadForm.getOtherImagesTokenArray()!=null){
            for(long otherImageToken : productUploadForm.getOtherImagesTokenArray()){
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

        if(productUploadForm.getRentTypeId() != null){
            if(rentTypeModel.getById(productUploadForm.getRentTypeId())==null){
                errors.rejectValue("rentTypeId", "No rent type found by id  "+productUploadForm.getRentTypeId());
            }
        }




    }
}
