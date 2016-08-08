package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.javafx.css.converters.ColorConverter;
import model.entity.app.convert.PictureConverter;
import model.nonentity.photo.Picture;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/8/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "product", schema = "", catalog = "rentguru24")
public class Product extends AbstractProduct{

}
