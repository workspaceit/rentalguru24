package model;

import model.entity.app.payments.RentPayment;
import model.nonentity.rent_payment.RentPaymentSummary;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public void update(RentPayment rentPayment){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentPayment);
        session.getTransaction().commit();
        session.close();
    }
    public boolean isPaymentAlreadyExist(String paymentId,String payerId){

        RentPayment rentPayment = this.getByPayIdAndPayerId(paymentId, payerId);
        if(rentPayment==null)
            return false;
        return true;

    }
    public List<RentPayment> getAll(){
        Session session= null;
        try{
            session = this.sessionFactory.openSession();
            return  session.createQuery("from RentPayment").list();
        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
        return new ArrayList<>();

    }
    public RentPayment getByPayIdAndPayerId(String paymentId,String payerId){
        Session session = this.sessionFactory.openSession();
        System.out.println("paypalPayId "+paymentId+" paypalPayerId "+payerId);
        try{
            return  (RentPayment)session.createQuery("from RentPayment where paypalPayerId =:paypalPayerId and paypalPayId=:paypalPayId")
                    .setParameter("paypalPayId", paymentId)
                    .setParameter("paypalPayerId", payerId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }

    }
    public RentPayment getByRentRequestId(int rentRequestId){
        Session session = this.sessionFactory.openSession();
        try{
            return  (RentPayment)session.createQuery("from RentPayment where rentRequest.id =:rentRequestId ")
                    .setParameter("rentRequestId", rentRequestId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public RentPayment getByRentRentInfId(int rentInfId){
        Session session = this.sessionFactory.openSession();
        try{
            return  (RentPayment)session.createQuery("from RentPayment where rentInf.id =:rentInfId ")
                    .setParameter("rentInfId", rentInfId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public RentPaymentSummary getSummary(){
        Session session = this.sessionFactory.openSession();
        List<Object[]> rows = session.createCriteria(RentPayment.class)
                .setProjection(
                        Projections.projectionList()
                                .add(Projections.rowCount(), "totalOrderCount")
                                .add(Projections.sum("siteFee"), "companyEarning")
                                .add(Projections.sum("rentFee"), "renteeEarning")
                ).list();
        RentPaymentSummary rps = new RentPaymentSummary();
        for(Object[] obj : rows){
            rps.setTotalOrderCount((Long) obj[0]);
            rps.setCompanyEarning((Double) obj[1]);
            rps.setRenteerEarning((Double) obj[2]);
        }
        return rps;

    }
}
