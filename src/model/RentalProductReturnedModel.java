package model;

import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.RentalProductReturnRequest;
import model.entity.app.product.rentable.RentalProductReturned;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.hibernate.Session;

/**
 * Created by mi on 8/27/16.
 */
public class RentalProductReturnedModel extends BaseModel {

    public RentalProductReturned getById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductReturned.class, id);
        } finally {
            session.close();
        }
    }
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
                " rentalProductReturned.isExpired = false and rentalProductReturned.rentInf.id =:rentInfId")
                .setParameter("rentInfId",rentInfId)
                .setMaxResults(1)
                .uniqueResult();

        if(rentalProductReturned != null)
            return true;
        return false;
    }
    public boolean alreadyReceivedProduct(int rentalProductReturnedId){
        Session session = this.sessionFactory.openSession();
        RentalProductReturned rentalProductReturned =(RentalProductReturned) session.createQuery("FROM RentalProductReturned rentalProductReturned where " +
                " rentalProductReturned.isExpired = false and rentalProductReturned.confirm = true ")
                .setParameter("rentalProductReturnedId",rentalProductReturnedId)
                .setMaxResults(1)
                .uniqueResult();

        if(rentalProductReturned != null)
            return true;
        return false;
    }
}
