package model;

import model.entity.app.PasswordResetsEntity;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import model.entity.developer.cron.CronLastExecuted;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mi on 10/13/16.
 */
public class CronLastExecutedModel extends BaseModel {
    public CronLastExecuted saveOrUpdateMaxRentRequestId(CronLastExecuted cronLastExecuted){
        CronLastExecuted prevCronLastExecuted = this.getFirst();
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        if(prevCronLastExecuted!=null){
            cronLastExecuted.setId(prevCronLastExecuted.getId());
        }
        session.saveOrUpdate(cronLastExecuted);
        session.getTransaction().commit();
        session.close();
        return cronLastExecuted;
    }
    public int getMaxRentRequestId() {
        Session session = null;
        CronLastExecuted cronLastExecuted = null;
        try {
            session = this.sessionFactory.openSession();
            cronLastExecuted =(CronLastExecuted) session.createQuery("from CronLastExecuted  ORDER BY id DESC")
                                    .setMaxResults(1)
                                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return (cronLastExecuted!=null)?cronLastExecuted.getRentRequestId():0;

    }
    public CronLastExecuted getFirst() {
        Session session = null;
        CronLastExecuted cronLastExecuted = null;
        try {
            session = this.sessionFactory.openSession();
            cronLastExecuted =(CronLastExecuted) session.createQuery("from CronLastExecuted  ORDER BY id DESC")
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return cronLastExecuted;

    }
}
