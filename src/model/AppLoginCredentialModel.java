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

    public String getPasswordAsMd5DigestAsHex(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
    public AppCredential getAppCredentialById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            return session.get(AppCredential.class,id);
        }finally {
            session.close();
        }
    }
    public AuthCredential getById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            return session.get(AuthCredential.class,id);
        }finally {
            session.close();
        }
    }
    public AuthCredential getByEmail(String email){
        Session session = this.sessionFactory.openSession();
        try{
            return (AuthCredential)session.createQuery("from AuthCredential where email = :email ")
                    .setParameter("email",email).uniqueResult();
        }finally {
            session.close();
        }
    }
    public AppCredential getAppcredentialByEmail(String email){
        Session session = this.sessionFactory.openSession();
        try{
            return (AppCredential)session.createQuery("from AppCredential where email = :email ")
                    .setParameter("email",email).uniqueResult();
        }finally {
            session.close();
        }
    }
    public boolean isVerified(int id){
        AuthCredential authCredential = this.getById(id);
        return authCredential.isVerified();
    }
    public boolean isBlocked(int id){
        AuthCredential authCredential = this.getById(id);
        return authCredential.isBlocked();
    }
    public AuthCredential authenticationByEmailPassword(String email,String password){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email and password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", this.getPasswordAsMd5DigestAsHex(password));
        return (AuthCredential)query.uniqueResult();
    }
    public AuthCredential adminAuthenticationByEmailPassword(String email,String password){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email and password = :password and role = 1";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", this.getPasswordAsMd5DigestAsHex(password));
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



    public boolean isEmailUsedByOtherButMe(int id,String email){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppCredential where email = :email and id !=:id";

        AppCredential appCredential = (AppCredential) session.createQuery(hql)
                                                        .setParameter("id", id)
                                                        .setParameter("email", email)
                                                        .uniqueResult();


        if(appCredential==null){
            return false;
        }
        return true;
    }
    public void insert(AuthCredential authCredential){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        authCredential.setPassword(this.getPasswordAsMd5DigestAsHex(authCredential.getPassword()));
        authCredential.setAccesstoken(this.getPasswordAsMd5DigestAsHex((authCredential.getEmail() + authCredential.getPassword())));


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
        session.update(authCredential);
        session.getTransaction().commit();
        session.close();
    }
    /* For Profile Edit */
    public void updateWithNewPassword(AuthCredential authCredential){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        authCredential.setPassword(this.getPasswordAsMd5DigestAsHex(authCredential.getPassword()));
        authCredential.setAccesstoken(this.getPasswordAsMd5DigestAsHex((authCredential.getEmail() + authCredential.getPassword())));


        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
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
        String hql = "FROM AuthCredential  WHERE  role < 0 ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List result = query.list();
        return result;
    }

    public List<AuthCredential>getAllAdmin(){
        Session session=null;
        try {
            session=this.sessionFactory.openSession();
            String hql="FROM AuthCredential  WHERE  role = 1 ORDER BY id DESC";
            return session.createQuery(hql).list();
        }finally {
            session.close();
        }
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
