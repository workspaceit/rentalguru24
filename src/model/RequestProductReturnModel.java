package model;

import model.entity.app.product.rentable.RentalProductReturnRequest;
import org.hibernate.Session;

/**
 * Created by mi on 8/26/16.
 */
public class RequestProductReturnModel extends BaseModel {

    public RentalProductReturnRequest getById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return session.get(RentalProductReturnRequest.class, id);
        }finally {
            session.close();
        }
    }
    public void insert(RentalProductReturnRequest rentalProductReturnRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProductReturnRequest);
        session.getTransaction().commit();
        session.close();
    }
}
