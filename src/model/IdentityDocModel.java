package model;

import model.entity.app.IdentityDoc;
import org.hibernate.Session;

/**
 * Created by omar on 8/2/16.
 */
public class IdentityDocModel extends BaseModel {
    public void insert(IdentityDoc identityDoc){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(identityDoc);
        session.getTransaction().commit();
        session.close();
    }
}
