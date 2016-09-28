package controller.service.admin;

import helper.ServiceResponse;
import model.AdminGlobalNotificationModel;
import model.entity.admin.AdminGlobalNotification;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 9/28/16.
 */
@RestController
@RequestMapping("/api-admin")
public class AdminGlobalNotificationService {

    @Autowired
    AdminGlobalNotificationModel adminGlobalNotificationModel;

    @RequestMapping(value = "/get-all-unread-notification", method = RequestMethod.GET)
    public ServiceResponse getAllNotification(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getAllUnreadNotification();
        serviceResponse.setResponseData(adminGlobalNotifications);
        return serviceResponse;
    }
    @RequestMapping(value = "/get-unread-notification", method = RequestMethod.GET)
    public ServiceResponse getNotification(HttpServletRequest request, @RequestParam Map<String, String> allRequestParams){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AppCredential appCredential = (AppCredential) request.getAttribute("appCredential");

        int limit = Integer.parseInt(allRequestParams.get("limit"));
        int offset = Integer.parseInt(allRequestParams.get("offset"));
        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }
        List<AdminGlobalNotification> adminGlobalNotifications = adminGlobalNotificationModel.getUnreadNotification(limit, offset);
        serviceResponse.setResponseData(adminGlobalNotifications);
        return serviceResponse;
    }
}
