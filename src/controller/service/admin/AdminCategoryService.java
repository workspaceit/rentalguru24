package controller.service.admin;

import helper.ServiceResponse;
import model.CategoryModel;
import model.entity.app.AppCredential;
import model.entity.app.Category;
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
    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    private ServiceResponse setCategory(HttpServletRequest request, @RequestParam String categoryName){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        if(categoryName == null){
            serviceResponse.setRequestError("categoryName", "Category Name Required");
            return serviceResponse;
        }
        categoryName = categoryName.trim();
        if(categoryName.isEmpty()){
            serviceResponse.setRequestError("categoryName", "Category Name Required");
            return serviceResponse;
        }
        int lastSortedOrder = categoryModel.maxSortOrder();
        Category category = new Category();
        category.setName(categoryName);
        category.setCreatedBy(appCredential.getId());
        category.setSortedOrder((lastSortedOrder+1));
        categoryModel.insert(category);
        serviceResponse.getResponseStat().setMsg("Category Add successful");
        return serviceResponse;
    }

    @RequestMapping(value = "/add-sub-category", method = RequestMethod.POST)
    private ServiceResponse setSubCategory(HttpServletRequest request, @RequestParam int categoryId, @RequestParam String subCategoryName){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(subCategoryName == null){
            serviceResponse.setRequestError("subCategoryName", "Category Name Required");
            return serviceResponse;
        }
        subCategoryName = subCategoryName.trim();
        if(subCategoryName.isEmpty()){
            serviceResponse.setRequestError("subCategoryName", "Category Name Required");
            return serviceResponse;
        }

        int lastSortedOrder = categoryModel.maxSortOrder();

        Category category = categoryModel.getById(categoryId);

        Category subCategory = new Category();
        subCategory.setName(subCategoryName);
        subCategory.setIsSubcategory(true);
        subCategory.setCreatedBy(appCredential.getId());
        subCategory.setSortedOrder((lastSortedOrder + 1));

        Set<Category> subCategoryArray = new HashSet<Category>();
        subCategoryArray.add(subCategory);

        category.setSubcategory(subCategoryArray);

        categoryModel.insert(category);
        serviceResponse.getResponseStat().setMsg("Subcategory Add successful");
        return serviceResponse;
    }
}
