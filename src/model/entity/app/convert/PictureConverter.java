package model.entity.app.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.nonentity.photo.Picture;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * Created by mi on 8/8/16.
 */
public class PictureConverter implements AttributeConverter<Picture, String> {
    @Override
    public String convertToDatabaseColumn(Picture picture) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.writeValueAsString(picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Picture convertToEntityAttribute(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        if(s==null){
            return null;
        }
        try {
            return objectMapper.readValue(s, Picture.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
