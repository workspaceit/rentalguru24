package model;

import model.entity.admin.AdminGlobalNotification;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by omar on 9/28/16.
 */
public class AdminGlobalNotificationModel extends BaseModel {
    public void insert(AdminGlobalNotification adminGlobalNotification){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(adminGlobalNotification);
        session.getTransaction().commit();
        session.close();
    }
    public void update(AdminGlobalNotification adminGlobalNotification){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(adminGlobalNotification);
        session.getTransaction().commit();
        session.close();
    }
    public AdminGlobalNotification getById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            return session.get(AdminGlobalNotification.class,id);
        }finally {
            session.close();
        }
    }

    public List<AdminGlobalNotification> getAllUnreadNotification(){
        Session session = null;
        List adminGlobalNotification = null;
        try {
            session = this.sessionFactory.openSession();
            adminGlobalNotification = session.createQuery("FROM AdminGlobalNotification adminGlobalNotification " +
                                        "WHERE adminGlobalNotification.isRead = FALSE " +
                                        "ORDER BY adminGlobalNotification.id DESC").list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return adminGlobalNotification;
    }

    public List<AdminGlobalNotification> getUnreadNotification(int limit, int offset){
        Session session = null;
        List adminGlobalNotification = null;
        try {
            session = this.sessionFactory.openSession();
            adminGlobalNotification = session.createQuery("FROM AdminGlobalNotification adminGlobalNotification " +
                    "WHERE adminGlobalNotification.isRead = FALSE " +
                    "ORDER BY adminGlobalNotification.id DESC")
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return adminGlobalNotification;
    }

    public List<AdminGlobalNotification> getAllNotification(){
        Session session = null;
        List adminGlobalNotification = null;
        try {
            session = this.sessionFactory.openSession();
            adminGlobalNotification = session.createQuery("FROM AdminGlobalNotification adminGlobalNotification " +
                    "ORDER BY adminGlobalNotification.id DESC").list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return adminGlobalNotification;
    }
}
