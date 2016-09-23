package model;

import model.entity.app.payments.PaymentRefund;
import org.hibernate.Session;

/**
 * Created by mi on 9/23/16.
 */
public class PaymentRefundModel extends BaseModel {

    public void insert(PaymentRefund paymentRefund){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(paymentRefund);
        session.getTransaction().commit();
        session.close();
    }

}
