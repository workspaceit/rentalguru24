package model.entity.app.product;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mi on 8/9/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "product", schema = "", catalog = "rentguru24")
public class SearchedProduct extends AbstractProduct{

    private List<ProductAvailability> productAvailability;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    public List<ProductAvailability> getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(List<ProductAvailability> productAvailability) {
        this.productAvailability = productAvailability;
    }
}