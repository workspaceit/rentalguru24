package controller.service.admin;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.TempFileModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.TempFile;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by omar on 9/5/16.
 */
@RestController
@RequestMapping("/api-admin/category")
public class AdminCategoryService {
    @Autowired
    CategoryModel categoryModel;

    @Autowired
    ProductModel productModel;

    @Autowired
    TempFileModel tempFileModel;

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    private ServiceResponse setCategory(HttpServletRequest request,
                                        @RequestParam String categoryName,
                                        @RequestParam(required = false) Long categoryImgToken){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        categoryName = categoryName.trim();

        List<Category> categoriList = categoryModel.getAll();
        for (Category category: categoriList){
            if(category.getName().equalsIgnoreCase(categoryName)){
                serviceResponse.setRequestError("categoryName", "Category name already exist");
                return serviceResponse;
            }
        }

        if(categoryName == null){
            serviceResponse.setRequestError("categoryName", "Category name Required");
            return serviceResponse;
        }

        Category category = new Category();

        if(categoryImgToken!=null){
            Picture picture = null;
            TempFile tempOtherFile = this.tempFileModel.getByToken(categoryImgToken);
            try {
                picture = ImageHelper.moveCategoryImage( tempOtherFile.getPath());

                if(picture.getOriginal().getPath().isEmpty()) {
                    serviceResponse.setRequestError("categoryImgToken", "Unable to save profile image");
                }
                category.setPicture(picture);

            } catch (Exception e) {
                //e.printStackTrace();
                serviceResponse.setRequestError("categoryImgToken", "Unable to save image.."+e.getMessage());
                return serviceResponse;
            }
        }
        categoryName = categoryName.trim();
        if(categoryName.isEmpty()){
            serviceResponse.setRequestError("categoryName", "Category name Required");
            return serviceResponse;
        }
        int lastSortedOrder = categoryModel.maxSortOrder();

        category.setName(categoryName);
        category.setCreatedBy(appCredential.getId());
        category.setSortedOrder((lastSortedOrder+1));
        categoryModel.insert(category);
        serviceResponse.getResponseStat().setMsg("Category add successful");
        return serviceResponse;
    }

    @RequestMapping(value = "/add-sub-category", method = RequestMethod.POST)
    private ServiceResponse setSubCategory(HttpServletRequest request, @RequestParam int categoryId, @RequestParam String subCategoryName){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        subCategoryName = subCategoryName.trim();
        List<Category> subCategoriesList = categoryModel.getByParentId(categoryId);
        for(Category category : subCategoriesList){
            if(category.getSubcategory().size() >= 0){
                for(Category subCategory : category.getSubcategory()){
                    if(subCategory.getName().equalsIgnoreCase(subCategoryName)){
                        serviceResponse.setRequestError("subCategoryName", "Subcategory name already exist");
                        return serviceResponse;
                    }
                }
            }
        }

        if(categoryId <= 0){
            serviceResponse.setRequestError("categoryName", "Please select category");
            return serviceResponse;
        }
        if(subCategoryName == null){
            serviceResponse.setRequestError("subCategoryName", "Subcategory name Required");
            return serviceResponse;
        }
        subCategoryName = subCategoryName.trim();
        if(subCategoryName.isEmpty()){
            serviceResponse.setRequestError("subCategoryName", "Subategory name Required");
            return serviceResponse;
        }

        int lastSortedOrder = categoryModel.maxSortOrder();

        Category category = categoryModel.getById(categoryId);
        if(category == null){
            serviceResponse.setRequestError("category", "Can't fiend category by this name");
            return serviceResponse;
        }

        Category subCategory = new Category();
        subCategory.setName(subCategoryName);
        subCategory.setIsSubcategory(true);
        subCategory.setCreatedBy(appCredential.getId());
        subCategory.setSortedOrder((lastSortedOrder + 1));

        Set<Category> subCategoryArray = new HashSet<Category>();
        subCategoryArray.add(subCategory);

        category.setSubcategory(subCategoryArray);

        categoryModel.insert(category);
        serviceResponse.getResponseStat().setMsg("Subcategory add successful");
        return serviceResponse;
    }
    @RequestMapping(value = "/edit-category", method = RequestMethod.POST)
    public ServiceResponse editCategory(HttpServletRequest request, @RequestParam int categoryId,
                                        @RequestParam String categoryName,
                                        @RequestParam(required = false) Long categoryImgToken){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        if(categoryId < 0){
            serviceResponse.setRequestError("category", "can't fiend category by this name");
            return serviceResponse;
        }
        if(categoryName == null){
            serviceResponse.setRequestError("category", "category name required");
            return serviceResponse;
        }
        categoryName = categoryName.trim();
        if(categoryName.isEmpty()){
            serviceResponse.setRequestError("categoryName", "Category name required");
            return serviceResponse;
        }

        Category category = categoryModel.getById(categoryId);

        if(categoryImgToken!=null){
            Picture picture = null;
            TempFile tempOtherFile = this.tempFileModel.getByToken(categoryImgToken);
            try {
                picture = ImageHelper.moveCategoryImage( tempOtherFile.getPath());

                if(picture.getOriginal().getPath().isEmpty()) {
                    serviceResponse.setRequestError("categoryImgToken", "Unable to save profile image");
                    return serviceResponse;
                }
                category.setPicture(picture);

            } catch (Exception e) {
                //e.printStackTrace();
                serviceResponse.setRequestError("categoryImgToken", "Unable to save image.."+e.getMessage());
                return serviceResponse;
            }
        }


        category.setName(categoryName);
        categoryModel.insert(category);
        serviceResponse.getResponseStat().setMsg("Category successfully updated");
        return serviceResponse;
    }
    @RequestMapping(value = "/delete-category", method = RequestMethod.POST)
    public ServiceResponse deleteCategory(HttpServletRequest request, @RequestParam int categoryId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        List<Category> categoryList = categoryModel.getByParentId(categoryId);
        for(Category category : categoryList){
            List<RentalProduct> rentalProduct = productModel.getProductByCategoryId(category.getId(),1,0);
            if(rentalProduct!=null && rentalProduct.size() > 0){
                serviceResponse.setRequestError("categoryId", "Can not delete category, there is category \'"+category.getName()+"\' which has product");
                return serviceResponse;
            }
            for(Category subCategory : category.getSubcategory()){
                List<RentalProduct> rentalProductSub = productModel.getProductByCategoryId(subCategory.getId(),1,0);
                if(rentalProductSub!=null && rentalProductSub.size() > 0){
                    serviceResponse.setRequestError("categoryId", "Can not delete category, there is subcategory \'"+subCategory.getName()+"\' which has product");
                    return serviceResponse;
                }
            }
        }
        if(categoryList == null || categoryList.size() == 0){
            Category category = categoryModel.getById(categoryId);
            if(category == null){
                serviceResponse.setRequestError("categoryId", "Category not found");
                return serviceResponse;
            }
            List<RentalProduct> rentalProduct = productModel.getProductByCategoryId(category.getId(),1,0);
            if(rentalProduct!=null && rentalProduct.size() > 0){
                serviceResponse.setRequestError("categoryId", "Can not delete category, there is category \'"+category.getName()+"\' which has product");
                return serviceResponse;
            }
            categoryModel.delete(categoryId);
        }else{
            categoryModel.delete(categoryList);
        }
        serviceResponse.getResponseStat().setMsg("Category successfully deleted");
        return serviceResponse;
    }

    @RequestMapping(value = "/delete-subcategory", method = RequestMethod.POST)
    public ServiceResponse deleteSubcategory(HttpServletRequest request, @RequestParam int subCategoryid){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        Category subCategory = categoryModel.getById(subCategoryid);
        if(subCategory == null){
            serviceResponse.setRequestError("subCategoryid", "Subcategory not found");
            return serviceResponse;
        }
        List<RentalProduct> rentalProduct = productModel.getProductByCategoryId(subCategory.getId(),1,0);
        if(rentalProduct!=null && rentalProduct.size() > 0){
            serviceResponse.setRequestError("subCategoryid", "Can not delete subcategory, there is subcategory \'"+subCategory.getName()+"\' which has product");
            return serviceResponse;
        }
        serviceResponse.getResponseStat().setMsg("subcategory successfully deleted");
        categoryModel.delete(subCategory);
        return serviceResponse;
    }
}
