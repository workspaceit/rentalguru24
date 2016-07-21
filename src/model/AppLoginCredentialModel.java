package model;

import model.entity.app.AppLoginCredentialEntity;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by mi on 7/20/16.
 */
public class AppLoginCredentialModel extends BaseModel {
    public AppLoginCredentialEntity getById(){
        Session session = this.sessionFactory.openSession();



        return session.get(AppLoginCredentialEntity.class,1);
    }
    public AppLoginCredentialEntity getByPassword(){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppLoginCredentialEntity where password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("password","123456789");
        return (AppLoginCredentialEntity)query.uniqueResult();
    }

    public void insert(AppLoginCredentialEntity appLoginCredentialEntity){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
//        System.out.println(appLoginCredentialEntity.getUserInfByUserInfId().getId());
      //  System.out.println(appLoginCredentialEntity.getUserInfByUserInfId().getId());
        //    appLoginCredentialEntity.getUserInfByUserInfId().setId(userid);
        session.save(appLoginCredentialEntity.getUserInfByUserInfId());
        System.out.println(appLoginCredentialEntity.getUserInfByUserInfId().getId());
        appLoginCredentialEntity.setUserInfId(appLoginCredentialEntity.getUserInfByUserInfId().getId());
        session.save(appLoginCredentialEntity);
        session.getTransaction().commit();
        session.close();
    }

}
