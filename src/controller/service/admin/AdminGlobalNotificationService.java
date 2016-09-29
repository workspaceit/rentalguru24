package controller.service.admin;

import helper.ServiceResponse;
import model.AdminGlobalNotificationModel;
import model.AdminUnreadAlertCounterModel;
import model.entity.admin.AdminGlobalNotification;
import model.entity.admin.AdminUnreadAlertCount;
import model.entity.app.AppCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    AdminUnreadAlertCounterModel adminUnreadAlertCounterModel;

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

    @RequestMapping(value = "/get-notification-count", method = RequestMethod.GET)
    public ServiceResponse getNotificationCount(HttpServletRequest request){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AdminUnreadAlertCount adminUnreadAlertCount = adminUnreadAlertCounterModel.getAllUnreadAlertCount();
        serviceResponse.setResponseData(adminUnreadAlertCount);
        return serviceResponse;
    }

    @RequestMapping(value = "/notification-read/{notification_id}", method = RequestMethod.POST)
    public ServiceResponse notificationRead(HttpServletRequest request, @PathVariable("notification_id") int notificationId){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        AdminGlobalNotification adminGlobalNotification = adminGlobalNotificationModel.getById(notificationId);
        if(adminGlobalNotification == null){
            serviceResponse.setRequestError("notification", "No notification found");
            return serviceResponse;
        }
        if(adminGlobalNotification.getIsRead() == true){
            serviceResponse.setRequestError("notification", "Notification already read");
            return serviceResponse;
        }
        AdminUnreadAlertCount adminUnreadAlertCount = adminUnreadAlertCounterModel.getAllUnreadAlertCount();
        int unreadMessage = adminUnreadAlertCount.getGlobalNotification();
        if(unreadMessage < 0){
            serviceResponse.setRequestError("notification", "No notification found");
            return serviceResponse;
        }
        unreadMessage = (unreadMessage - 1);

        adminUnreadAlertCount.setGlobalNotification(unreadMessage);
        adminUnreadAlertCounterModel.update(adminUnreadAlertCount);

        adminGlobalNotification.setIsRead(true);
        adminGlobalNotificationModel.update(adminGlobalNotification);

        serviceResponse.getResponseStat().setMsg("Message Read !!!");

        return serviceResponse;
    }
}
