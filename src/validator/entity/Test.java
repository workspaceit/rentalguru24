package validator.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RentalProductReturnRequestModel;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String args[]){


        RestTemplate restTemplate = new RestTemplate();
        String accessToken = "ya29.Ci9TAwhUJhesa3LA2WbS2mJS0_XT250Hi-YElfm-jRX5MhY_svvm-qy6fRS2BbEt-g";
        String fields = "&fields=name,email,user_location";
        String response = "";
        try {
             response = restTemplate.getForObject("https://www.googleapis.com/plus/v1/people/me?access_token="+accessToken, String.class);
           // System.out.println(response);
        }catch ( org.springframework.web.client.HttpClientErrorException ex){
            System.out.println(ex.getMessage()+" "+ex.getResponseBodyAsString());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode=mapper.valueToTree(response);
        System.out.println(response);
        try {
            String email = "";
            String firstName = "";
            String lastName = "";
            Boolean verified = null;
            String profileImageUrl = "";
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
            System.out.println(profileImageUrl);
            System.out.println(email);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        String[] email  = jsonNode.findValuesAsText("kind");
//       // System.out.print(jsonNode.r);
//        if(email!=null)
//            System.out.print(email);

    }
}

