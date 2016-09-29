package model;

import model.entity.admin.AdminUnreadAlertCount;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by omar on 9/29/16.
 */
public class AdminUnreadAlertCounterModel extends BaseModel {
    public void insert(AdminUnreadAlertCount adminUnreadAlertCount){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(adminUnreadAlertCount);
        session.getTransaction().commit();
        session.close();
    }

    public void update(AdminUnreadAlertCount adminUnreadAlertCount){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(adminUnreadAlertCount);
        session.getTransaction().commit();
        session.close();
    }

    public AdminUnreadAlertCount getAllUnreadAlertCount(){
        Session session = null;
        AdminUnreadAlertCount adminUnreadAlertCount = null;
        try {
            session = this.sessionFactory.openSession();
            adminUnreadAlertCount = (AdminUnreadAlertCount) session.createQuery("FROM AdminUnreadAlertCount adminUnreadAlertCount " +
                    "ORDER BY adminUnreadAlertCount.id DESC")
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return adminUnreadAlertCount;
    }
}
