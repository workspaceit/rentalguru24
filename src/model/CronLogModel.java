package model;

import model.entity.app.PasswordResetsEntity;
import model.entity.developer.cron.CronLog;
import org.hibernate.Session;

/**
 * Created by mi on 10/13/16.
 */
public class CronLogModel extends BaseModel {
    public CronLog insert(CronLog cronLog){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(cronLog);
        session.getTransaction().commit();
        session.close();
        return cronLog;
    }
}
