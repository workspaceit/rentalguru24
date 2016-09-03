package validator.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RentalProductReturnRequestModel;
import org.springframework.web.client.RestTemplate;
public class Test {
    public static void main(String args[]){
//        RestTemplate restTemplate = new RestTemplate();
//        String accessToken = "EAACAvVskhygBAFvTxyhTGZCVw4SdNUDrZB6DPLQ0GNVlCKwJEK5osLo1IsZAwF4Ca7ueZAozjIjfuBACAp5mcmqwRnZBHDeF9nJQauOjtTN0Btuqu56pAFSbJAirZAKcbRR4ODfoq8uNyGRF3ZA976sEZBrhECHlfk1JDtS3tkVSwz3OqAH16dWV";
//        String fields = "&fields=name,email,user_location";
//        //
//        try{
//            FaceBookInf fbInf = restTemplate.getForObject("https://graph.facebook.com/me?access_token="+accessToken+"&"+fields,FaceBookInf.class);
//            System.out.println(fbInf.email);
//            System.out.println(fbInf.name);
//            System.out.println(fbInf.id);
//            FaceBookProfilePicture profilePic = restTemplate.getForObject("http://graph.facebook.com/"+fbInf.id+"/picture?redirect=false&?width=720&height=720",FaceBookProfilePicture.class);
//            System.out.println(profilePic.url);
//        }catch (org.springframework.web.client.HttpClientErrorException ex){
//            System.out.println(ex.getMessage()+" "+ex.getResponseBodyAsString());
//        }
        String[] a = "a s f".split(" ");
        System.out.print(a.length);
    }
}

class FaceBookInf{
    public String name = "";
    public String email ="";
    public String id="";
}
class FaceBookProfilePicture{
    public String data = "";
        public int  height = 0;
        public boolean  is_silhouette = false;
        public String   url ="";
        public int width =0;
}
