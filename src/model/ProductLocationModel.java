package model;

import model.entity.app.product.rentable.ProductLocation;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.Session;

/**
 * Created by mi on 8/29/16.
 */
public class ProductLocationModel extends BaseModel {
    public void insert(ProductLocation productLocation) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(productLocation);
        session.getTransaction().commit();
        session.close();
    }
}
