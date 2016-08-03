package model;

import model.entity.app.RentRequest;
import org.hibernate.Session;

/**
 * Created by omar on 8/3/16.
 */
public class RentRequestModel extends BaseModel {
    public void insert(RentRequest rentRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentRequest);
        session.getTransaction().commit();
        session.close();
    }
}
