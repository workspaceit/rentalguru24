package validator.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RentalProductReturnRequestModel;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String args[]){
        List<String> contentTypeList = new ArrayList<String>(){
            {
                add("application/pdf");
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };
        System.out.println(contentTypeList.contains("image/jpeg") + " " + contentTypeList.contains("image/**"));
    }
}

