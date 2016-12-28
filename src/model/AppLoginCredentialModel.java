package model;


import model.entity.app.AppCredential;
import model.entity.app.AuthCredential;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mi on 7/20/16.
 */
public class AppLoginCredentialModel extends BaseModel {

    public String getPasswordAsMd5DigestAsHex(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
    public boolean isPasswordMatched(int appCredentialId,String password){
        AuthCredential authCredential = this.getById(appCredentialId);
        if(authCredential==null) return false;
        return this.isPasswordMatched(authCredential, password);
    }
    public boolean isPasswordMatched(AuthCredential authCredential,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password,authCredential.getPassword());
    }
    public static String SHA2HASH(String password,String salt) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            String passWithSalt = password + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        AuthCredential authCredential = (AuthCredential)query.uniqueResult();
        session.close();

        if(authCredential==null){
            return null;
        }
        if(bCryptPasswordEncoder.matches(password,authCredential.getPassword())) {
            return authCredential;
        }else {
            return null;
        }
    }
    public AuthCredential adminAuthenticationByEmailPassword(String email,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Session session = this.sessionFactory.openSession();
        String hql = "from AuthCredential where email = :email and role = 1";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        AuthCredential authCredential = (AuthCredential)query.uniqueResult();

        session.close();

        if(authCredential==null){
            return null;
        }

        if(bCryptPasswordEncoder.matches(password,authCredential.getPassword())) {
            return authCredential;
        }else {
            return null;
        }
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String tmpAccessToken = this.SHA2HASH(authCredential.getPassword(),authCredential.getEmail());
        tmpAccessToken = (tmpAccessToken==null)
                        ?this.getPasswordAsMd5DigestAsHex(authCredential.getEmail() + authCredential.getPassword())
                        :tmpAccessToken;

        authCredential.setPassword(bCryptPasswordEncoder.encode(authCredential.getPassword()));
        authCredential.setAccesstoken(tmpAccessToken);


        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(authCredential);
        session.getTransaction().commit();

        session.close();
        // Adding Primary key which is unique
        // ( MD5 Collision vulnerabilities https://en.wikipedia.org/wiki/MD5#Collision_vulnerabilities )

        authCredential.setAccesstoken(authCredential.getAccesstoken() + authCredential.getId());
        this.update(authCredential);

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
    public void delete(AuthCredential authCredential){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(authCredential);
        session.getTransaction().commit();
        session.close();
    }
    /* For Profile Edit */
    public void updateWithNewPassword(AuthCredential authCredential){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String tmpAccessToken = this.SHA2HASH(authCredential.getPassword(),authCredential.getEmail());
        tmpAccessToken = (tmpAccessToken==null)
                ?this.getPasswordAsMd5DigestAsHex(authCredential.getEmail() + authCredential.getPassword())
                :tmpAccessToken;
        authCredential.setPassword(bCryptPasswordEncoder.encode(authCredential.getPassword()));
        authCredential.setAccesstoken(tmpAccessToken+authCredential.getId());


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
    public List<AuthCredential> getAllVerifiedAppUser(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM AuthCredential  WHERE  role < 0 and verified = true ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List result = query.list();
        return result;
    }
    public List<AuthCredential>getAllUnVerifiedAppUser(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM AuthCredential  WHERE  role < 0 and verified = false  ORDER BY id DESC";
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
