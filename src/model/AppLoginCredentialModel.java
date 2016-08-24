package model;


import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mi on 7/20/16.
 */
public class AppLoginCredentialModel extends BaseModel {
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    public AppLoginCredentialModel() {

    }

    public AppCredential getAppCredentialById(int id){
        Session session = this.sessionFactory.openSession();
        return session.get(AppCredential.class,id);
    }
    public AuthCredential getById(int id){
        Session session = this.sessionFactory.openSession();
        return session.get(AuthCredential.class,id);
    }
    public AuthCredential authenticationByEmailPassword(String email,String password){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email and password = :password";
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", DigestUtils.md5DigestAsHex(password.getBytes()));
        return (AuthCredential)query.uniqueResult();
    }
    public AuthCredential adminAuthenticationByEmailPassword(String email,String password){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email and password = :password and role = 1";
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", DigestUtils.md5DigestAsHex(password.getBytes()));
        return (AuthCredential)query.uniqueResult();
    }
    public AuthCredential authenticationByAccessToken(String accessToken){
        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where accesstoken = :accessToken";

        Query query = session.createQuery(hql);
        query.setParameter("accessToken", accessToken);
        return (AuthCredential)query.uniqueResult();
    }

    public boolean isEmailExist(String email){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppCredential where email = :email";

        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        AppCredential appCredential = (AppCredential)query.uniqueResult();

        if(appCredential==null){
            return false;
        }
        return true;
    }
    public void insert(AuthCredential authCredential){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        authCredential.setPassword(DigestUtils.md5DigestAsHex(authCredential.getPassword().getBytes()));
        authCredential.setAccesstoken(DigestUtils.md5DigestAsHex((authCredential.getEmail() + authCredential.getPassword()).getBytes()));


        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

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

    public List<AuthCredential> getAllAppUser(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM AuthCredential WHERE  role < 0";
        Query query = session.createQuery(hql);
        List result = query.list();
        return result;
    }

    public void appUserStatusUpdate(int appUserId, int varified){
        Session session = this.sessionFactory.openSession();
        AuthCredential authCredential = session.get(AuthCredential.class, appUserId);
        if(varified == 1){
            authCredential.setVerified(true);
        }else {
            authCredential.setVerified(false);
        }
        session.beginTransaction();
        session.update(authCredential);
        session.getTransaction().commit();
        session.close();
    }
}
