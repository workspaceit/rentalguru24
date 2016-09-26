package model;

import model.entity.app.UserPaypalCredential;
import org.hibernate.Session;

/**
 * Created by mi on 9/23/16.
 */
public class UserPaypalCredentialModel extends BaseModel {
    public void insert(UserPaypalCredential userPaypalCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(userPaypalCredential);
        session.getTransaction().commit();
        session.close();
    }
    public UserPaypalCredential getByAppCredentialId(int appCredentialId){
        Session session = this.sessionFactory.openSession();
        try{
            return (UserPaypalCredential)session.createQuery("FROM UserPaypalCredential where appCredential.id=:appCredentialId  ")
                    .setParameter("appCredentialId",appCredentialId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
}
