package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.convert.PictureArrayConverter;
import model.convert.PictureConverter;
import model.entity.app.AppCredential;
import model.nonentity.photo.Picture;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MI on 09-Aug-16.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@MappedSuperclass
public abstract class AbstractProduct {


}
