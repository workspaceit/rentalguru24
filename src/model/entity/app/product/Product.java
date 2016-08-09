package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by mi on 8/8/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "product", schema = "", catalog = "rentguru24")
public class Product extends AbstractProduct{

}
