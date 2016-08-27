package validator.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RequestProductReturnModel;

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


