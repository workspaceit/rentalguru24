package controller.service.admin;


import helper.ServiceResponse;
import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.IdentityType;
import model.entity.app.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by omar on 8/24/16.
 */


@RestController
@RequestMapping("/api-admin")
public class AdminUserService {
    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @RequestMapping(value = "/app-user/active-app-user/{app_user_id}/{varified}", method = RequestMethod.POST)
    public ServiceResponse setActiveAppUser(HttpServletRequest request, @PathVariable("app_user_id") int appUserId, @PathVariable("varified") int varified) {

        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        if (serviceResponse.hasErrors()) {
            return serviceResponse;
        }
        appLoginCredentialModel.appUserStatusUpdate(appUserId, varified);
        return serviceResponse;
    }


    @RequestMapping(value = "/admin/active-admin/{admin_id}", method = RequestMethod.POST)
    public ServiceResponse adminActivate(HttpServletRequest request, @PathVariable("admin_id") int appUserId) {
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        AuthCredential adminUser = appLoginCredentialModel.getById(appUserId);

        if (adminUser == null) {
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("No admin Exist");
            return serviceResponse;
        }


        adminUser.setVerified(true);
        appLoginCredentialModel.update(adminUser);
        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Admin activated succesfully");
        return serviceResponse;

    }

    @RequestMapping(value = "/admin/deactive-admin/{admin_id}", method = RequestMethod.POST)
    public ServiceResponse adminDeactivate(HttpServletRequest request, @PathVariable("admin_id") int appUserId) {
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        AuthCredential adminUser = appLoginCredentialModel.getById(appUserId);

        if (adminUser == null) {
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("No admin Exist");
            return serviceResponse;
        }


        adminUser.setVerified(false);
        appLoginCredentialModel.update(adminUser);
        serviceResponse.getResponseStat().setStatus(true);
        serviceResponse.getResponseStat().setMsg("Admin activated succesfully");
        return serviceResponse;

    }


    @RequestMapping(value = "/admin/update-admin", method = RequestMethod.POST)
    public ServiceResponse adminEdit(HttpServletRequest request,@RequestParam Map<String, String> allRequestParams,
                                        @Valid AuthCredential authCredential) {
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        int id=Integer.parseInt( allRequestParams.get("id"));
        String firstName = allRequestParams.get("firstName");
        String lastName = allRequestParams.get("lastName");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        UserInf userInf = new UserInf();



        AuthCredential admin=appLoginCredentialModel.getById(id);

        if (!admin.getEmail().equals(email)) {
            if (appLoginCredentialModel.isEmailUsedByOtherButMe(id, email)) {
                serviceResponse.getResponseStat().setStatus(false);
                serviceResponse.getResponseStat().setMsg("This email is already Taken by other user");
                return serviceResponse;
            }
        }

        boolean flag=false;

        if (admin==null){
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("Your request is not valid");
            return serviceResponse;
        }

        if (!firstName.equals("")){
            admin.getUserInf().setFirstName(firstName);
        }
        if (!lastName.equals("")){
            admin.getUserInf().setLastName(lastName);
        }

        if (!email.equals("")){
            admin.setEmail(email);
        }

        if (!password.equals("")){
            admin.setPassword(password);
            flag=true;

        }

        if (flag)
        appLoginCredentialModel.updateWithNewPassword(admin);
        else
        appLoginCredentialModel.update(admin);

        serviceResponse.getResponseStat().setMsg("Admin Information Updated Successfully");
        serviceResponse.getResponseStat().setStatus(true);

        return serviceResponse;

    }


    @RequestMapping(value = "/admin/create-new", method = RequestMethod.POST)
    public ServiceResponse adminSignup(HttpServletRequest request,
                                       @RequestParam Map<String, String> allRequestParams,
                                       @Valid AuthCredential authCredential
    ) {
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");
        String firstName = allRequestParams.get("firstName");
        String lastName = allRequestParams.get("lastName");
        String email = allRequestParams.get("email");
        String password = allRequestParams.get("password");
        UserInf userInf = new UserInf();
//        user.setUserAddress(userAddress);

        if (appLoginCredentialModel.isEmailExist(email)){
            serviceResponse.getResponseStat().setStatus(false);
            serviceResponse.getResponseStat().setMsg("This email is already taken");
            return serviceResponse;
        }

        userInf.setFirstName(firstName);
        userInf.setLastName(lastName);
        authCredential.setUserInf(userInf);
        authCredential.setRole(1);
        authCredential.setVerified(true);
        authCredential.setEmail(email);
        authCredential.setPassword(password);
        IdentityType identityType = new IdentityType();
        identityType.setId(1);
        authCredential.getUserInf().setIdentityType(identityType);
        appLoginCredentialModel.insert(authCredential);
        serviceResponse.getResponseStat().setMsg("Signup successful");
        serviceResponse.setResponseData(authCredential);
        return serviceResponse;

    }
}
