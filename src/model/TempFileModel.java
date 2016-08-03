package model;

import model.entity.app.TempFile;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by omar on 8/2/16.
 */
public class TempFileModel extends BaseModel {
    public void insert(TempFile tempFile){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(tempFile);
        session.getTransaction().commit();
        session.close();
    }
    public model.entity.app.TempFile getByToken(long token){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM TempFile ID WHERE ID.token="+token;
        Query query = session.createQuery(hql);
        return (model.entity.app.TempFile)query.uniqueResult();
    }
    public void delete(TempFile tempFile){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(tempFile);
        session.getTransaction().commit();
        session.close();
    }
}
