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
import java.util.Map;

public class Test {
    public static void main(String args[]){


        Instant instant = Instant.now();
        System.out.println(instant.getEpochSecond());

        System.out.println(instant.getNano());
//        String[] email  = jsonNode.findValuesAsText("kind");
//       // System.out.print(jsonNode.r);
//        if(email!=null)
//            System.out.print(email);

    }
}

