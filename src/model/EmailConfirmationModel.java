package model;

import helper.UtilituHelper;
import model.entity.app.AppCredential;
import model.entity.app.EmailConfirmation;
import model.entity.app.IdentityType;
import org.hibernate.Session;

/**
 * Created by mi on 10/19/16.
 */
public class EmailConfirmationModel extends BaseModel {
    public void insert(EmailConfirmation emailConfirmation){
        boolean isExist = this.isExistByAppCredentialId(emailConfirmation.getAppCredential().getId());

        if(isExist){
            this.delete(emailConfirmation);
        }

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        String email = emailConfirmation.getAppCredential().getEmail();
        String randomText = UtilituHelper.getRandomNumber(6);


        emailConfirmation.setToken(UtilituHelper.getMD5Text(email+randomText));

        session.save(emailConfirmation);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(EmailConfirmation emailConfirmation){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(emailConfirmation);
        session.getTransaction().commit();
        session.close();
    }
    public void update(EmailConfirmation emailConfirmation){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(emailConfirmation);
        session.getTransaction().commit();
        session.close();
    }
    public EmailConfirmation getByAppCredentialId(int appCredentialId){


        Session session = this.sessionFactory.openSession();
        try{
            return (EmailConfirmation)session.createQuery("FROM EmailConfirmation  where appCredential.id =:appCredentialId")
                    .setParameter("appCredentialId",appCredentialId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public EmailConfirmation getByEmailAndToken(String email,String token){


        Session session = this.sessionFactory.openSession();
        try{
            return (EmailConfirmation)session.createQuery("FROM EmailConfirmation  where appCredential.email =:email and token =:token")
                    .setParameter("email", email)
                    .setParameter("token",token)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public boolean isExistByAppCredentialId(int appCredentialId){
        EmailConfirmation emailConfirmation = this.getByAppCredentialId(appCredentialId);
        return (emailConfirmation!=null);
    }
}
