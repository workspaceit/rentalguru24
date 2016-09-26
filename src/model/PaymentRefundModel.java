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
    public PaymentRefund getByRentPaymentId(long rentPaymentId){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return (PaymentRefund)session.createQuery("from PaymentRefund where rentPayment.id = :rentPaymentId ")
                    .setParameter("rentPaymentId", rentPaymentId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public boolean alreadyRefundedByRentPaymentId(long rentPaymentId){
        PaymentRefund paymentRefund = this.getByRentPaymentId(rentPaymentId);
        if(paymentRefund!=null)
            return true;
        return false;
    }
}
