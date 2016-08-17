package model;

import model.entity.app.RentRequest;
import model.entity.app.product.rentable.RentalProductEntity;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by omar on 8/3/16.
 */
public class RentRequestModel extends BaseModel {
    public RentRequest getById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return session.get(RentRequest.class, id);
        }finally {
            session.close();
        }
    }
    public void insert(RentRequest rentRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentRequest);
        session.getTransaction().commit();
        session.close();
    }
    public List<RentRequest> getByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest where rentRequest.rentalProduct.owner.id =:ownerId")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getProductId(int ownerId,int productId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest where rentRequest.rentalProduct.owner.id =:ownerId and rentRequest.rentalProduct.id = :productId")
                    .setParameter("ownerId",ownerId)
                    .setParameter("productId",productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }


}
