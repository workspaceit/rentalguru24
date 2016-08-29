package model;

import model.entity.app.product.rentable.RentalProductReturnRequest;
import model.entity.app.product.rentable.RentalProductReturned;
import org.hibernate.Session;

/**
 * Created by mi on 8/27/16.
 */
public class RentalProductReturnedModel extends BaseModel {

    public void insert(RentalProductReturned rentalProductReturned){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProductReturned);
        session.getTransaction().commit();
        session.close();
    }

    public boolean alreadyReturnedProduct(int rentInfId){
        Session session = this.sessionFactory.openSession();
        RentalProductReturned rentalProductReturned =(RentalProductReturned) session.createQuery("FROM RentalProductReturned rentalProductReturned where " +
                " rentalProductReturned.isExpired = false ")
                .setMaxResults(1)
                .uniqueResult();

        if(rentalProductReturned != null)
            return true;
        return false;
    }
}
