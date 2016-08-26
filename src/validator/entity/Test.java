package validator.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.RequestProductReturnModel;
import model.entity.app.product.RequestProductReturn;

import java.io.IOException;

public class Test {
    public static void main(String args[]){
        RequestProductReturnModel requestProductReturnModel = new RequestProductReturnModel();
        ObjectMapper om = new ObjectMapper();

        try {
            System.out.println(om.writeValueAsString(requestProductReturnModel.getById(1)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}


