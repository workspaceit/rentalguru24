package model;


import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mi on 7/20/16.
 */
public class AuthCredentialModel extends BaseModel {
    public AuthCredential getById(int id){
        Session session = this.sessionFactory.openSession();
        return session.get(AuthCredential.class,id);
    }
    public AppCredential getByPassword(){
        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("password", "$2a$10$Xf.T6cLdQj/5/lGgG2CoCu.momkOn5C6X3WDKdgDH1pSLjYRxqReS");
        return (AppCredential)query.uniqueResult();
    }
    public AppCredential dummy(){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppCredential where id = :id";

        Query query = session.createQuery(hql);
        query.setParameter("id",1);
        return (AppCredential)query.uniqueResult();
    }
    public void insert(AuthCredential authCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        //session.save(appCredential.getUser());
        session.saveOrUpdate(authCredential);
        session.getTransaction().commit();
        session.close();

    }
    public void insert(AppCredential appCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        //session.save(appCredential.getUser());
        session.saveOrUpdate(appCredential);
        session.getTransaction().commit();
        session.close();

    }
    public void update(AuthCredential authCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        //session.save(appCredential.getUser());
        session.update(authCredential);
        session.getTransaction().commit();
        session.close();
    }
    public void update(AppCredential appCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        //session.save(appCredential.getUser());
        session.update(appCredential);
        session.getTransaction().commit();
        session.close();
    }
    public boolean isEmailExist(String email){
        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email";

        Query query = session.createQuery(hql);
        query.setParameter("email",email);
        AppCredential appCredential = (AppCredential)query.uniqueResult();

        if(appCredential==null){
            return false;
        }
        return true;
    }
}
