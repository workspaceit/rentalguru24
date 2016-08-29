package model;

import model.entity.app.AppCredential;
import model.entity.app.product.rentable.RentalProductReturnedHistory;
import org.hibernate.Session;

/**
 * Created by mi on 8/27/16.
 */
public class RentalProductReturnedHistoryModel  extends BaseModel {

    public void insert(RentalProductReturnedHistory rentalProductReturnedHistory){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProductReturnedHistory);
        session.getTransaction().commit();
        session.close();

    }
}
