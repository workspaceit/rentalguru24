package model;

import model.entity.admin.AdminGlobalNotificationTemplate;
import org.hibernate.Session;

/**
 * Created by omar on 9/28/16.
 */
public class AdminGlobalNotificationTemplateModel extends BaseModel {
    public void insert(AdminGlobalNotificationTemplate adminGlobalNotificationTemplate){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(adminGlobalNotificationTemplate);
        session.getTransaction().commit();
        session.close();
    }

    public AdminGlobalNotificationTemplate getById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            return session.get(AdminGlobalNotificationTemplate.class,id);
        }finally {
            session.close();
        }
    }

    public AdminGlobalNotificationTemplate getByType(String notificationType){
        Session session = this.sessionFactory.openSession();
        try {
            return (AdminGlobalNotificationTemplate) session.createQuery("FROM AdminGlobalNotificationTemplate aN WHERE aN.type = :notificationType")
                    .setParameter("notificationType", notificationType)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
}
