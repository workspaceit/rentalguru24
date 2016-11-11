package validator.form;


import helper.DateHelper;

import model.CategoryModel;
import model.RentTypeModel;
import model.TempFileModel;

import model.entity.app.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.form.class_file.ProductEditFrom;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.stream.Stream;


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




        if( productEditFrom.getAvailableFrom() != null && !productEditFrom.getAvailableFrom().isEmpty() ){
            if(!DateHelper.isDateValid(productEditFrom.getAvailableFrom(), DATE_FORMAT)){
                errors.rejectValue("availableFrom","Available from date is not in valid format");
            }
        }

        if(productEditFrom.getAvailableTill()  != null  &&  !productEditFrom.getAvailableTill().isEmpty()){
            if(!DateHelper.isDateValid(productEditFrom.getAvailableTill(), DATE_FORMAT)){
                errors.rejectValue("availableTill","Available till date is not in valid format");
            }
        }



        if( productEditFrom.getRentTypeId()!= null){
            if(rentTypeModel.getById( productEditFrom.getRentTypeId())==null){
                errors.rejectValue("rentTypeId", "Rent type not found for id "+ productEditFrom.getRentTypeId());
            }
        }


        if(productEditFrom.getCategoryIdArray()!=null && productEditFrom.getCategoryIdArray().length>0){
            try{
               Integer[] catArray = productEditFrom.getCategoryIdArray();


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
                ex.printStackTrace();
                errors.rejectValue("categoryIdArray","Category not in valid format");
            }
        }



    }

    public boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
