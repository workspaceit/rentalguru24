package model;

import model.entity.app.IdentityDoc;
import model.entity.app.IdentityType;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

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
    public List<IdentityDoc> getByToken(int token){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM IdentityDoc ID WHERE ID.token="+token;
        Query query = session.createQuery(hql);
        return (List<IdentityDoc>)query.list();
//        List results = query.list();
//        return results;
    }
}
