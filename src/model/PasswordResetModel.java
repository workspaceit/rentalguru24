package model;

import model.entity.app.PasswordResetsEntity;
import org.hibernate.Session;

/**
 * Created by mi on 9/3/16.
 */
public class PasswordResetModel extends BaseModel {
    public PasswordResetsEntity insert(PasswordResetsEntity passwordResetsEntity){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(passwordResetsEntity);
        session.getTransaction().commit();
        session.close();
        return passwordResetsEntity;
    }

    public PasswordResetsEntity delete(PasswordResetsEntity passwordResetsEntity){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(passwordResetsEntity);
        session.getTransaction().commit();
        session.close();
        return passwordResetsEntity;
    }
    public boolean isExist(int appCridentialId){
        PasswordResetsEntity passwordResetsEntity = this.getByAppCredentialId(appCridentialId);
        return (passwordResetsEntity!=null)?true:false;
    }
    public PasswordResetsEntity getByAppCredentialId(int appCridentialId){
        Session session = this.sessionFactory.openSession();
        try{
            PasswordResetsEntity passwordResetsEntity = (PasswordResetsEntity)
                                                        session.createQuery(" From PasswordResetsEntity passwordResetsEntity" +
                                                                " WHERE passwordResetsEntity.authCredential.id =:appCridentialId")
                                                                .setParameter("appCridentialId", appCridentialId)
                                                                .setMaxResults(1)
                                                                .uniqueResult();
            return passwordResetsEntity;
        }finally {
            session.close();
        }
    }

}
