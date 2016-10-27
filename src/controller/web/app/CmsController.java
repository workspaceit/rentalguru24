package controller.web.app;


import helper.ServiceResponse;
import model.CategoryModel;
import model.ProductModel;
import model.admin.AdminCmsPageModel;
import model.entity.admin.AdminCmsPage;
import model.entity.app.AppCredential;
import model.entity.app.Category;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mi on 8/3/16.
 */

@Controller
@RequestMapping("/static")
public class CmsController {
    @Autowired
    AdminCmsPageModel adminCmsPageModel;

    @Autowired
    ProductModel productModel;
    @RequestMapping(value = "/{pageKey}",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,@PathVariable String pageKey) {
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        ModelAndView modelAndView = new ModelAndView("cms/cmsPages");
        AdminCmsPage adminCmsPage = adminCmsPageModel.getByPageKey(pageKey);
        if(adminCmsPage==null){
            return new ModelAndView("redirect:/home");
        }
        modelAndView.addObject("adminCmsPage", adminCmsPage);

        return modelAndView;
    }

}
