package validator.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

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
    @JsonSerialize(using=ISerializer.class)
    public String getABC() {
        return "ABC";
    }

    @Override
    @JsonSerialize(using=JSerializer.class)
    public String getEFG() {
        return "EFG";
    }
}
class B implements I{
    @Override
    public String getABC() {
        return null;
    }
}

interface I{
    String getABC();
}

interface J extends I{
    String getEFG();
}

class ISerializer extends JsonSerializer<I> {
    @Override
    public void serialize(I value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {

        jgen.writeStartObject();
        jgen.writeStringField("abc", value.getABC());
        jgen.writeEndObject();
    }
}
class JSerializer extends JsonSerializer<J> {
    @Override
    public void serialize(J value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {

        jgen.writeStartObject();
        jgen.writeStringField("efg", value.getABC());
        jgen.writeEndObject();
    }
}
