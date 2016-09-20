package model;

import model.entity.app.payments.RentPayment;
import org.hibernate.Session;

/**
 * Created by mi on 9/20/16.
 */
public class RentPaymentModel extends BaseModel {
    public void insert(RentPayment rentPayment){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentPayment);
        session.getTransaction().commit();
        session.close();
    }
}
