package model;

import model.entity.admin.AdminPaypalCredential;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Tomal on 8/30/2016.
 */
public class AdminPaypalCredentailModel extends BaseModel {

    public AdminPaypalCredential getAdminPaypalCredentail(){
        AdminPaypalCredential adminPaypalCredential=null;
        Session session=null;
        try {
            session=this.sessionFactory.openSession();
            List<AdminPaypalCredential>paypalCredentials=session.createQuery("FROM AdminPaypalCredential").list();
            if (paypalCredentials.size()==1){
                adminPaypalCredential=paypalCredentials.get(0);
            }
            return adminPaypalCredential;

        }finally {
            session.close();
        }

    }

    public void UpdateAdminPaypalCredientail(AdminPaypalCredential adminPaypalCredential){
        Session session=null;
        Transaction transaction=null;
        try {
            session=this.sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.update(adminPaypalCredential);
            transaction.commit();

        }catch (HibernateException ex){
            ex.printStackTrace();
            if (transaction!=null)
                transaction.rollback();
        }finally {
            session.close();
        }

    }
}
