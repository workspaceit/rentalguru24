package model;


import model.entity.app.AppCredential;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mi on 7/20/16.
 */
public class AppLoginCredentialModel extends BaseModel {
    public AppCredential getById(){
        Session session = this.sessionFactory.openSession();
        return session.get(AppCredential.class,1);
    }
    public AppCredential getByPassword(){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppCredential where password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("password", "123456789");
        return (AppCredential)query.uniqueResult();
    }

    public void insert(AppCredential appCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        //session.save(appCredential.getUser());
        session.saveOrUpdate(appCredential);
        session.getTransaction().commit();
        session.close();
    }


}
