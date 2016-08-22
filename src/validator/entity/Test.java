package validator.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Test {
    public static void main(String args[]){

        ObjectMapper objectMapper = new ObjectMapper();

        A a = new A();
        I i = new A();
        J j = new A();
        try{

            System.out.println(objectMapper.writeValueAsString(a));
            System.out.println(objectMapper.writeValueAsString(i));
            System.out.println(objectMapper.writeValueAsString(j));
        }catch (Exception ex){

        }
    }
}

class A implements I,J{
    @Override
    public String getABC() {
        return "ABC";
    }

    @Override
    public String getEFG() {
        return "EFG";
    }
}

interface I{
    String getABC();
}
@JsonSerialize(as=J.class)
interface J extends I{
    String getEFG();
}