package controller.service.admin;

import helper.ServiceResponse;
import model.admin.AdminCmsPageModel;
import model.entity.admin.AdminCmsPage;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
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

        if(pageName.isEmpty() || pageName == null) {
            serviceResponse.setRequestError("pageName", "Page name required");
        }

        if(pageKey.isEmpty() || pageKey == null){
            serviceResponse.setRequestError("pageKey", "Page url required");
        }

        if(pageContent.isEmpty() || pageContent == null){
            serviceResponse.setRequestError("pageContent", "Page content required");
        }
        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        Pattern pattern = Pattern.compile("[^a-z0-9A-z ]", Pattern.CASE_INSENSITIVE);
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
                }else if(adminCmsPage.getPageKey().equalsIgnoreCase(pageKey)){
                    serviceResponse.setRequestError("pageKey", "A page url exist in this name");
                }
            }
        }



        if(serviceResponse.hasErrors()){
            return serviceResponse;
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
        serviceResponse.getResponseStat().setMsg("Page added successful");
        return serviceResponse;
    }
    @RequestMapping(value = "/edit-page/{pageId}", method = RequestMethod.POST)
    public ServiceResponse setAddPage(HttpServletRequest request,
                                      @PathVariable int pageId,
                                      @RequestParam Map<String , String> allRequestParams){
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
        AdminCmsPage adminCmsPage = adminCmsPageModel.getById(pageId);


        if(adminCmsPageModel.isPageNameExitButById(adminCmsPage.getId(),pageName)){
            serviceResponse.setRequestError("pageName", "A page name exist in this name");
            return serviceResponse;
        }

        if(adminCmsPageModel.isPageKeyExitButById(adminCmsPage.getId(), pageName)){
            serviceResponse.setRequestError("pageKey", "A page url exist in this key");
            return serviceResponse;
        }

        adminCmsPage.setPageKey(pageKey);
        adminCmsPage.setPageName(pageName);
        adminCmsPage.setPageContent(pageContent);
        adminCmsPageModel.insert(adminCmsPage);
        serviceResponse.getResponseStat().setMsg("Page updated successful");
        return serviceResponse;
    }
    @RequestMapping(value = "/delete-page", method = RequestMethod.POST)
    public ServiceResponse deletePage(HttpServletRequest request, @RequestParam int cmsPageId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");
        adminCmsPageModel.delete(cmsPageId);
        serviceResponse.getResponseStat().setMsg("Page delete successful");
        return serviceResponse;
    }
}
