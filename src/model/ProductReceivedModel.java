package model;

import model.entity.app.product.ProductReceived;
import org.hibernate.Session;

/**
 * Created by mi on 8/24/16.
 */
public class ProductReceivedModel extends BaseModel {
    public void insert(ProductReceived productReceived){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(productReceived);
        session.getTransaction().commit();
        session.close();
    }
}
