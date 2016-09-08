package controller.service.admin;

import helper.ServiceResponse;
import model.admin.AdminCmsPageModel;
import model.entity.admin.AdminCmsPage;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by omar on 9/8/16.
 */
@RestController
@RequestMapping("/api-admin/cms")
public class AdminCMSService {
    @Autowired
    AdminCmsPageModel adminCmsPageModel;
    @RequestMapping(value = "/add-page", method = RequestMethod.POST)
    public ServiceResponse setAddPage(HttpServletRequest request, @RequestParam Map<String , String> allRequestParams){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        String pageKey = allRequestParams.get("pageKey");
        String pageName = allRequestParams.get("pageName");
        String pageContent = allRequestParams.get("pageContent");

        pageKey = pageKey.trim();
        pageKey = pageKey.toLowerCase();

        pageName = pageName.trim();

        Pattern pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pageName);
        boolean hasSpecialCharacter = matcher.find();
        if(hasSpecialCharacter){
            serviceResponse.setRequestError("pageName", "Page name can't have special character");
            return serviceResponse;
        }

        List<AdminCmsPage> adminCmsPageList = adminCmsPageModel.getAll();
        if(adminCmsPageList != null){
            for(AdminCmsPage adminCmsPage: adminCmsPageList){
                if(adminCmsPage.getPageName().equalsIgnoreCase(pageName)){
                    serviceResponse.setRequestError("pageName", "A page already exist in this name");
                    return serviceResponse;
                }else if(adminCmsPage.getPageKey().equalsIgnoreCase(pageKey)){
                    serviceResponse.setRequestError("pageKey", "A page key exist in this name");
                    return serviceResponse;
                }
            }
        }
        Integer lastSortedOrder = adminCmsPageModel.maxSortOrder();
        if(lastSortedOrder == null){
            lastSortedOrder = 0;
        }

        AdminCmsPage adminCmsPage = new AdminCmsPage();

        adminCmsPage.setPageKey(pageKey);
        adminCmsPage.setPageName(pageName);
        adminCmsPage.setPageContent(pageContent);
        adminCmsPage.setSortedOrder((lastSortedOrder+1));
        adminCmsPageModel.insert(adminCmsPage);
        serviceResponse.getResponseStat().setMsg("page add successful");
        return serviceResponse;
    }
}
