package validator.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RentalProductReturnRequestModel;

public class Test {
    public static void main(String args[]){
        RentalProductReturnRequestModel rentalProductReturnRequestModel = new RentalProductReturnRequestModel();
        ObjectMapper om = new ObjectMapper();

        try {
            System.out.println(om.writeValueAsString(rentalProductReturnRequestModel.getById(1)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}


