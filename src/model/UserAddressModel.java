package model;

import model.entity.app.UserAddress;
import org.hibernate.Session;

/**
 * Created by omar on 10/24/16.
 */
public class UserAddressModel extends BaseModel {
    public void insert(UserAddress userAddress){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(userAddress);
        session.getTransaction().commit();
        session.close();
    }
}
