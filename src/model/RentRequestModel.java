package model;

import model.entity.app.RentRequest;
import model.entity.app.product.rentable.RentalProductEntity;
import org.hibernate.Session;

import java.sql.Timestamp;
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
    public void update(RentRequest rentRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentRequest);
        session.getTransaction().commit();
        session.close();
    }
    public List<RentRequest> getByProductOwnerAndProductId(int ownerId,int productId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest " +
                    " INNER Join FETCH rentRequest.requestedBy " +
                    " where rentRequest.rentalProduct.owner.id =:ownerId and rentRequest.rentalProduct.id = :productId ORDER BY rentRequest.id desc  ")
                    .setParameter("ownerId",ownerId)
                    .setParameter("productId",productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getByRequestedByAndProductId(int requestedById,int productId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest " +
                    " INNER Join FETCH rentRequest.requestedBy " +
                    " where rentRequest.requestedBy.id =:requestedById and rentRequest.rentalProduct.id = :productId ORDER BY rentRequest.id desc  ")
                    .setParameter("requestedById",requestedById)
                    .setParameter("productId",productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public RentRequest getAlreadyRentRequested(int requestedBy,int productId,Timestamp startDate,Timestamp endsDate){
        Session session = this.sessionFactory.openSession();
        try{
             RentRequest rentRequest =(RentRequest)
                                    session.createQuery(
                                                            "FROM RentRequest rentRequest " +
                                                            " where ( ( rentRequest.startDate BETWEEN :startDate and :endsDate ) or " +
                                                            " ( rentRequest.endDate BETWEEN :startDate and :endsDate ) ) " +
                                                            " and rentRequest.requestedBy.id =:requestedBy " +
                                                            "and rentRequest.rentalProduct.id =:productId "
                                                        )
                                                        .setParameter("productId", productId)
                                                        .setParameter("requestedBy", requestedBy)
                                                        .setParameter("startDate", startDate)
                                                        .setParameter("endsDate", endsDate)
                                                        .setMaxResults(1)
                                                        .uniqueResult();
            return rentRequest;
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllByDateBetweenAndProductId(int productId,Timestamp startDate,Timestamp endsDate){
        Session session = this.sessionFactory.openSession();
        try{
            List<RentRequest>  rentRequestList = session.createQuery(
                    "FROM RentRequest rentRequest " +
                            " where ( ( rentRequest.startDate BETWEEN :startDate and :endsDate ) or " +
                            " ( rentRequest.endDate BETWEEN :startDate and :endsDate ) ) " +
                            "and rentRequest.rentalProduct.id =:productId "
            )
                                                .setParameter("productId", productId)
                                                .setParameter("startDate", startDate)
                                                .setParameter("endsDate", endsDate)
                                                .list();
            return rentRequestList;
        }finally {
            session.close();
        }
    }
    public boolean isAlreadyRequested(int requestedBy,int productId,Timestamp startDate,Timestamp endsDate){
        RentRequest rentRequest = this.getAlreadyRentRequested(requestedBy, productId, startDate, endsDate);
        return (rentRequest!=null)?true:false;
    }
    public List<RentRequest> getByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy " +
                                        " where rentRequest.rentalProduct.owner.id =:ownerId ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllApproveRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                                        " where rentRequest.rentalProduct.owner.id =:ownerId and rentRequest.approve = true ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllDisapproveRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                                        " where rentRequest.rentalProduct.owner.id =:ownerId" +
                                        " and rentRequest.disapprove = true ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllPendingRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                                        " where rentRequest.rentalProduct.owner.id =:ownerId" +
                                         " and rentRequest.disapprove = false" +
                                         " and rentRequest.approve = false ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllCanceledRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                                        " where rentRequest.rentalProduct.owner.id =:ownerId " +
                                        " and rentRequest.requestCancel = true ORDER BY rentRequest.id desc ")
                                        .setParameter("ownerId", ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }

    public List<RentRequest> getByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy " +
                    " where rentRequest.requestedBy.id =:requestedById ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById", requestedById)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllPendingRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById" +
                    " and rentRequest.disapprove = false" +
                    " and rentRequest.approve = false ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById",requestedById)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllApproveRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById and rentRequest.approve = true ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById",requestedById)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllDisapproveRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById" +
                    " and rentRequest.disapprove = true ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById",requestedById)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllCanceledRequestByRequestedBy(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:ownerId " +
                    " and rentRequest.requestCancel = true ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId", ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }
    public void expireByDateBetween(int rentId,int productId,Timestamp startDate,Timestamp endsDate){
        List<RentRequest>  rentRequests = this.getAllByDateBetweenAndProductId(productId,startDate,endsDate);

        for(RentRequest rentRequest : rentRequests){
            /****** Update All in the list except the Approved one *********/
            if(rentRequest.getId() == rentId) continue;

            rentRequest.setIsExpired(true);
            this.update(rentRequest);
        }

    }
}
