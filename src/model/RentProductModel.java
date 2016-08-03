package model;

import model.entity.app.RentProduct;
import org.hibernate.Session;

/**
 * Created by omar on 8/3/16.
 */
public class RentProductModel extends BaseModel {
    public void insert(RentProduct rentProduct){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentProduct);
        session.getTransaction().commit();
        session.close();
    }
}
