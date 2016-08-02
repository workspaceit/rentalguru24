package model;

import model.entity.app.IdentityType;
import org.hibernate.Session;

/**
 * Created by omar on 8/2/16.
 */
public class IdentityTypeModel extends BaseModel {
    public void insert(IdentityType identityType){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(identityType);
        session.getTransaction().commit();
        session.close();
    }
}
