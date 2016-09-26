package model;

import model.entity.app.Category;
import model.entity.app.payments.Payout;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by omar on 8/1/16.
 */
public class PayoutModel extends BaseModel {
    public void insert(Payout payout){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(payout);
        session.getTransaction().commit();
        session.close();
    }

}
