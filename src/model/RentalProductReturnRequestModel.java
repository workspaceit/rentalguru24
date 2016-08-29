package model;

import model.entity.app.product.rentable.RentalProductReturnRequest;
import org.hibernate.Session;

/**
 * Created by mi on 8/26/16.
 */
public class RentalProductReturnRequestModel extends BaseModel {

    public void insert(RentalProductReturnRequest rentalProductReturnRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProductReturnRequest);
        session.getTransaction().commit();
        session.close();
    }

    public RentalProductReturnRequest getById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return session.get(RentalProductReturnRequest.class, id);
        }finally {
            session.close();
        }
    }


    public boolean alreadyRequestedToReturn(int rentInfId){
       Session session = this.sessionFactory.openSession();
        RentalProductReturnRequest requestProductReturn =(RentalProductReturnRequest) session.createQuery("FROM RentalProductReturnRequest returnRequest where " +
                " returnRequest.rentInf.id = :id and returnRequest.isExpired = false ")
               .setParameter("id", rentInfId)
               .setMaxResults(1)
                .uniqueResult();

        if(requestProductReturn != null)
            return true;
        return false;
    }
}
