package model.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.nonentity.photo.Picture;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 8/8/16.
 */
public class PictureArrayConverter implements AttributeConverter<List<Picture>, String> {
    @Override
    public String convertToDatabaseColumn(List<Picture> picture) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.writeValueAsString(picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Picture> convertToEntityAttribute(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        if(s==null){
            return new ArrayList<>();
        }
        try {
            Picture[] pictures = objectMapper.readValue(s, Picture[].class);
            List<Picture> pictureList = new ArrayList<>();

            for(Picture picture: pictures){
                pictureList.add(picture);
            }
            return pictureList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
