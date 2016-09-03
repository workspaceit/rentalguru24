package controller.service.app.social_media;

import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.IdentityType;
import model.entity.app.UserInf;
import model.nonentity.photo.Picture;
import model.nonentity.social_media.FaceBookInf;
import model.nonentity.social_media.FaceBookProfilePicture;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mi on 9/2/16.
 */
@RestController
@RequestMapping("/api/facebook/")
public class FaceBookService {

    private final static String ApiBaseURL = "https://graph.facebook.com";
    private String fields = "&fields=name,email";

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    IdentityTypeModel identityTypeModel;



    @RequestMapping(value = "/signup-user/by-fb-access-token",method = RequestMethod.POST)
    public ServiceResponse signUpByAccessToken(HttpServletRequest request ,@RequestParam String accessToken){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        RestTemplate restTemplate = new RestTemplate();

        //
        FaceBookInf fbInf = new FaceBookInf();
        try{
            System.out.println(accessToken);
            fbInf = restTemplate.getForObject(ApiBaseURL+"/me?access_token="+accessToken+"&"+fields,FaceBookInf.class);
            System.out.println(fbInf.getEmail());
            System.out.println(fbInf.getName());
            System.out.println(fbInf.getId());

        }catch (org.springframework.web.client.HttpClientErrorException ex){
            System.out.println(ex.getMessage()+" "+ex.getResponseBodyAsString());
            serviceResponse.getResponseStat().setErrorMsg(ex.getMessage() + " " + ex.getResponseBodyAsString());
            serviceResponse.setRequestError("access_token","Access token expired");
            return serviceResponse;
        }

        FaceBookProfilePicture profilePic = new FaceBookProfilePicture();


        try {
            profilePic = restTemplate.getForObject(ApiBaseURL + "/" + fbInf.getId() + "/picture?redirect=false&?width=720&height=720", FaceBookProfilePicture.class);
        }catch  (org.springframework.web.client.HttpClientErrorException ex){
            serviceResponse.setRequestError("profile_picture", ex.getMessage() + " " + ex.getResponseBodyAsString());
            return serviceResponse;
        }




        if(appLoginCredentialModel.isEmailExist(fbInf.getEmail())){
            serviceResponse.getResponseStat().setMsg("Login Success");

            serviceResponse.setResponseData(appLoginCredentialModel.getByEmail(fbInf.getEmail()));
            AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(fbInf.getEmail());
            SessionManagement.setAppCredentialInSession(request, serviceResponse,appCredential);
            return serviceResponse;
        }

        AuthCredential authCredential = new AuthCredential();
        UserInf userInf = new UserInf();
        Picture profileImage = new Picture();
//        user.setUserAddress(userAddress);
        userInf.setFirstName(fbInf.getFirstName());
        userInf.setLastName(fbInf.getLastName());
        userInf.setIdentityType(identityTypeModel.getFirst());
        authCredential.setUserInf(userInf);
        authCredential.setRole(-1);
        authCredential.setEmail(fbInf.getEmail());
        authCredential.setPassword(fbInf.getId()+accessToken);
        authCredential.setVerified(true);
        authCredential.setBlocked(false);

        appLoginCredentialModel.insert(authCredential);

        try {
            URL url = new URL(profilePic.getData().getUrl());
            profileImage = ImageHelper.saveProfileImage(authCredential.getId(), url.openStream());
            System.out.println(profilePic.getData().getUrl());
        }catch  (MalformedURLException ex){
            serviceResponse.setRequestError("profile_picture", ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            profileImage = new Picture();
        }

        authCredential.getUserInf().setProfilePicture(profileImage);
        appLoginCredentialModel.update(authCredential);
        serviceResponse.setResponseData(appLoginCredentialModel.getById(authCredential.getId()));

        /*Creating Session */
        SessionManagement.setAppCredentialInSession(request,serviceResponse,appLoginCredentialModel.getAppCredentialById(authCredential.getId()));

        return serviceResponse;
    }
}
