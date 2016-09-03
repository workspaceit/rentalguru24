package controller.service.app.social_media;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.ImageHelper;
import helper.ServiceResponse;
import helper.SessionManagement;
import model.AppLoginCredentialModel;
import model.IdentityTypeModel;
import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import model.entity.app.UserInf;
import model.nonentity.photo.Picture;
import model.nonentity.social_media.FaceBookInf;
import model.nonentity.social_media.FaceBookProfilePicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mi on 9/2/16.
 */
@RestController
@RequestMapping("/api/social-media")
public class SocialMediaService {

    private final static String FaceBookApiBaseURL = "https://graph.facebook.com";
    private final static String GoogleApiBaseURL = "https://www.googleapis.com";
    private String fields = "&fields=name,email";

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;

    @Autowired
    IdentityTypeModel identityTypeModel;


   /* ******** Facebook ***************/
    @RequestMapping(value = "/facebook/login/by-facebook-access-token",method = RequestMethod.POST)
    public ServiceResponse signUpByFacebookAccessToken(HttpServletRequest request ,@RequestParam String accessToken){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        RestTemplate restTemplate = new RestTemplate();

        //
        FaceBookInf fbInf = new FaceBookInf();
        try{
            System.out.println(accessToken);
            fbInf = restTemplate.getForObject(FaceBookApiBaseURL +"/me?access_token="+accessToken+"&"+fields,FaceBookInf.class);
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
            profilePic = restTemplate.getForObject(FaceBookApiBaseURL + "/" + fbInf.getId() + "/picture?redirect=false&?width=720&height=720", FaceBookProfilePicture.class);
        }catch  (org.springframework.web.client.HttpClientErrorException ex){
            serviceResponse.setRequestError("profile_picture", ex.getMessage() + " " + ex.getResponseBodyAsString());
            return serviceResponse;
        }

        AuthCredential authCredential = new AuthCredential();
        UserInf userInf = new UserInf();
//        user.setUserAddress(userAddress);
        userInf.setFirstName(fbInf.getFirstName());
        userInf.setLastName(fbInf.getLastName());
        userInf.setIdentityType(identityTypeModel.getFirst());
        authCredential.setUserInf(userInf);
        authCredential.setRole(-1);
        authCredential.setEmail(fbInf.getEmail());
        authCredential.setPassword(fbInf.getId() + accessToken);
        authCredential.setVerified(true);
        authCredential.setBlocked(false);

        serviceResponse = this.signupOrLoginWithSocialMedia(request, serviceResponse, authCredential, profilePic.getData().getUrl());


        return serviceResponse;
    }
    /* ******** Google ***************/

    @RequestMapping(value = "/google/login/by-google-access-token",method = RequestMethod.POST)
    public ServiceResponse signUpByGoogleAccessToken(HttpServletRequest request ,@RequestParam String accessToken) {
        ServiceResponse serviceResponse = (ServiceResponse) request.getAttribute("serviceResponse");

        RestTemplate restTemplate = new RestTemplate();
        String response = "";
        try {
            response = restTemplate.getForObject(GoogleApiBaseURL+"/plus/v1/people/me?access_token="+accessToken, String.class);
        }catch ( org.springframework.web.client.HttpClientErrorException ex){
            System.out.println(ex.getMessage()+" "+ex.getResponseBodyAsString());
            serviceResponse.setRequestError("accessToken","You google access token expired");
            return serviceResponse;
        }
        System.out.println(response);
        String email = "";
        String firstName = "";
        String lastName = "";
        Boolean verified = null;
        String profileImageUrl = "";
        String googleId = "";

        ObjectMapper mapper = new ObjectMapper();

        try {

            Map<String, Object> mp = mapper.readValue(response,new TypeReference<Map<String, Object>>() {});
            ArrayList<HashMap<String,String>> emails = (ArrayList<HashMap<String,String>>)mp.get("emails");
            if(emails!=null && emails.size()>0){
                email = emails.get(0).get("value");
            }
            HashMap<String,String> fullName  = (HashMap<String,String>)mp.get("name");
            firstName = (String)fullName.get("givenName");
            lastName = (String)fullName.get("familyName");
            verified = (Boolean)mp.get("verified");
            HashMap<String,String> profileImage  = (HashMap<String,String>) mp.get("image");
            profileImageUrl = (String)profileImage.get("url");
            profileImageUrl = profileImageUrl.replaceAll("sz.*","");
            googleId = (String)mp.get("id");
            System.out.println(googleId);
            System.out.println(profileImageUrl);
            System.out.println(email);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            serviceResponse.getResponseStat().setErrorMsg("Unable to fetch any data from google api");
            return serviceResponse;
        }


        AuthCredential authCredential = new AuthCredential();
        UserInf userInf = new UserInf();
//        user.setUserAddress(userAddress);
        userInf.setFirstName(firstName);
        userInf.setLastName(lastName);
        userInf.setIdentityType(identityTypeModel.getFirst());
        authCredential.setUserInf(userInf);
        authCredential.setRole(-1);
        authCredential.setEmail(email);
        authCredential.setPassword(googleId+accessToken);
        authCredential.setVerified(true);
        authCredential.setBlocked(false);

        this.signupOrLoginWithSocialMedia(request, serviceResponse, authCredential, profileImageUrl);

        return serviceResponse;
    }
    private ServiceResponse signupOrLoginWithSocialMedia(HttpServletRequest request,
                                                         ServiceResponse serviceResponse,
                                                         AuthCredential authCredential,
                                                         String profileImageUrl){

        if(authCredential.getEmail() == null || authCredential.getEmail().isEmpty()){
            serviceResponse.getResponseStat().setErrorMsg("Did not find any email");
            return serviceResponse;
        }
        if(appLoginCredentialModel.isEmailExist(authCredential.getEmail())){
            serviceResponse.getResponseStat().setMsg("Login Success");

            serviceResponse.setResponseData(appLoginCredentialModel.getByEmail(authCredential.getEmail()));
            AppCredential appCredential = appLoginCredentialModel.getAppcredentialByEmail(authCredential.getEmail());
            SessionManagement.setAppCredentialInSession(request, serviceResponse,appCredential);
            return serviceResponse;
        }



        appLoginCredentialModel.insert(authCredential);
        Picture profileImage = new Picture();
        try {
            URL url = new URL(profileImageUrl);
            profileImage = ImageHelper.saveProfileImage(authCredential.getId(), url.openStream());
            System.out.println(profileImageUrl);
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
