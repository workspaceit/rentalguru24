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
    public boolean isPaymentAlreadyExist(String paymentId,String payerId){

        RentPayment rentPayment = this.getByPayIdAndPayerId(paymentId,payerId);
        if(rentPayment==null)
            return false;
        return true;

    }
    public RentPayment getByPayIdAndPayerId(String paymentId,String payerId){
        Session session = this.sessionFactory.openSession();
        System.out.println("paypalPayId "+paymentId+" paypalPayerId "+payerId);
        return  (RentPayment)session.createQuery("from RentPayment where paypalPayerId =:paypalPayerId and paypalPayId=:paypalPayId")
                .setParameter("paypalPayId", paymentId)
                .setParameter("paypalPayerId",payerId)
                .setMaxResults(1)
                .uniqueResult();

    }
}
