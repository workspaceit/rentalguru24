package model;

import helper.DateHelper;
import model.entity.app.RentRequest;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public RentRequest getValidRentRequestById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return(RentRequest)session.createQuery("from RentRequest where id=:id " +
                                                    " and requestCancel = false " +
                                                   " and isExpired = false ")
                                                    .setParameter("id",id)
                                                .setMaxResults(1).uniqueResult();
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
    public void delete(RentRequest rentRequest){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(rentRequest);
        session.getTransaction().commit();
        session.close();
    }
    public List<RentRequest> getAll(){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest "+
                    " where rentRequest.isExpired = false ORDER BY rentRequest.id desc  ")
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllPending(){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest " +
                    " where rentRequest.isExpired = false " +
                    " AND rentRequest.approve = false" +
                    " AND rentRequest.disapprove = false " +
                    " ORDER BY rentRequest.id desc  ")
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllInProgress(){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest " +
                    " where rentRequest.isExpired = false " +
                    " AND ( rentRequest.approve = true" +
                    " or rentRequest.isRentComplete = true ) " +
                    " AND rentRequest.isRentComplete = false " +
                    " ORDER BY rentRequest.id desc  ")
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getAllCompelte(){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery(" SELECT rentRequest FROM RentRequest rentRequest " +
                    " where rentRequest.isExpired = false " +
                    " AND rentRequest.isRentComplete = true " +
                    " ORDER BY rentRequest.id desc  ")
                    .list();
        }finally {
            session.close();
        }
    }
    public List<RentRequest> getByProductOwnerAndProductId(int ownerId,int productId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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
        if(limit<=0){
            return new ArrayList<>();
        }
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
    public List<RentRequest> getAlreadyRentRequested(int requestedBy,int productId,Timestamp startDate,Timestamp endsDate){
        Session session = this.sessionFactory.openSession();
        try{

            List<RentRequest> rentRequest = session.createQuery(
                                                            "FROM RentRequest rentRequest " +
                                                            " where ( ( :startDate BETWEEN rentRequest.startDate and rentRequest.endDate ) or " +
                                                            " ( :endsDate  BETWEEN rentRequest.startDate and rentRequest.endDate ) ) " +
                                                            " and rentRequest.requestedBy.id =:requestedBy " +
                                                            " and rentRequest.rentalProduct.id =:productId " +
                                                            " and rentRequest.isExpired = false "
                                                        )
                                                        .setParameter("productId", productId)
                                                        .setParameter("requestedBy", requestedBy)
                                                        .setParameter("startDate", startDate)
                                                        .setParameter("endsDate", endsDate)
                                                        .list();
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
                            " and rentRequest.rentalProduct.id =:productId " +
                            " and rentRequest.isExpired = false " +
                            " and rentRequest.isRentComplete = false " +
                            " and rentRequest.isPaymentComplete = true ")
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
        List<RentRequest> rentRequestList = this.getAlreadyRentRequested(requestedBy, productId, startDate, endsDate);

        if(rentRequestList==null || rentRequestList.size()==0){
            return false;
        }
        for(RentRequest rentRequest:rentRequestList){
            // Have to take care of Request extension check
            if(!rentRequest.getIsExpired() && !rentRequest.getIsRentComplete()){
                return true;
            }
        }
        return false;
    }
    private boolean doRequestHasValidOrInCompleteRentInf(int rentRequestId){
        Session session = this.sessionFactory.openSession();
        try{

            RentInf rentInf = (RentInf)session.createQuery("FROM RentInf  where rentRequest.id =:rentRequestId" +
                                                            " and expired =  false " +
                                                            " and isRentComplete = false")
                    .setParameter("rentRequestId",rentRequestId)
                    .setMaxResults(1)
                    .uniqueResult();
            if(rentInf!=null)
                return true;
            return false;

        }finally {
            session.close();
        }

    }
    public List<RentRequest> getByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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
        if(limit<=0){
            return new ArrayList<>();
        }
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

    //OVERLOADED FUNCTION for user dashboard
    public List<RentRequest> getAllApproveRequestByProductOwner(int ownerId){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.rentalProduct.owner.id =:ownerId and rentRequest.approve = true ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId", ownerId)
                    .list();
        }finally {
            session.close();
        }
    }


    public List<RentRequest> getAllDisapproveRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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

    //OVERLOADED FUNCTION for user Dashboard
    public List<RentRequest> getAllDisapproveRequestByProductOwner(int ownerId){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.rentalProduct.owner.id =:ownerId" +
                    " and rentRequest.disapprove = true ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId", ownerId)
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
                                        " and rentRequest.approve = false " +
                                        " and rentRequest.requestCancel = false " +
                                        " and rentRequest.isExpired = false " +
                                        " and rentRequest.isPaymentComplete = true " +
                                        " ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId",ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }

    //OVERLOAD for User dashboard
    public List<RentRequest>getAllPendingRequestByProductOwner(int ownerId){
        Session session = null;
        try{
            session=this.sessionFactory.openSession();
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.rentalProduct.owner.id =:ownerId" +
                    " and rentRequest.disapprove = false" +
                    " and rentRequest.requestCancel = false" +
                    " and rentRequest.isExpired = false " +
                    " and rentRequest.isPaymentComplete = true " +
                    " and rentRequest.approve = false ORDER BY rentRequest.id desc ")
                    .setParameter("ownerId", ownerId)
                    .list();
        }
        finally {
            if (session!=null)
            session.close();
        }

    }
    public List<RentRequest> getAllCanceledRequestByProductOwner(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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
        if(limit<=0){
            return new ArrayList<>();
        }
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

    //OVERLOADED for user dashboard
    public List<RentRequest> getByRequestedBy(int requestedById){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy " +
                    " where rentRequest.requestedBy.id =:requestedById ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById", requestedById)
                    .list();
        }finally {
            session.close();
        }
    }


    public List<RentRequest> getAllPendingRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById" +
                    " and rentRequest.disapprove = false" +
                    " and rentRequest.requestCancel = false" +
//                    " and rentRequest.isExpired = false" +
                    " and rentRequest.approve = false ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById",requestedById)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        }finally {
            session.close();
        }
    }

    //OVERLOAD Function for user dashboard
    public List<RentRequest> getAllPendingRequestByRequestedBy(int requestedById){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById" +
                    " and rentRequest.disapprove = false" +
                    " and rentRequest.requestCancel = false" +
                    " and rentRequest.isExpired = false" +
                    " and rentRequest.approve = false ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById", requestedById)
                    .list();
        }finally {
            session.close();
        }
    }

    public List<RentRequest> getAllApproveRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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

    //OVERLOADED for user Dashboard
    public List<RentRequest> getAllApproveRequestByRequestedBy(int requestedById){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById and rentRequest.approve = true ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById", requestedById)
                    .list();
        }finally {
            session.close();
        }
    }


    public List<RentRequest> getAllDisapproveRequestByRequestedBy(int requestedById,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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

    //OVERLOADED MODEL for user Dashboard
    public List<RentRequest> getAllDisapproveRequestByRequestedBy(int requestedById){
        Session session = this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM RentRequest rentRequest INNER Join FETCH rentRequest.requestedBy" +
                    " where rentRequest.requestedBy.id =:requestedById" +
                    " and rentRequest.disapprove = true ORDER BY rentRequest.id desc ")
                    .setParameter("requestedById", requestedById)
                    .list();
        }finally {
            session.close();
        }
    }

    public List<RentRequest> getAllCanceledRequestByRequestedBy(int ownerId,int limit,int offset){
        Session session = this.sessionFactory.openSession();
        if(limit<=0){
            return new ArrayList<>();
        }
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
        List<RentRequest>  rentRequests = this.getAllByDateBetweenAndProductId(productId, startDate, endsDate);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        for(RentRequest rentRequest : rentRequests){
            /****** Update All in the list except the Approved one *********/
            if(rentRequest.getId() == rentId) continue;

            rentRequest.setIsExpired(true);
            session.update(rentRequest);
        }
        session.getTransaction().commit();
        session.close();

    }
    public void expireByDateBetween(int rentId,List<RentRequest>  rentRequests){

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        for(RentRequest rentRequest : rentRequests){
            /****** Update All in the list except the Approved one *********/
            if(rentRequest.getId() == rentId) continue;

            rentRequest.setIsExpired(true);
            session.update(rentRequest);
        }
        session.getTransaction().commit();
        session.close();

    }
    public void expireByDateBetween(RentRequest  rentRequest){

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        rentRequest.setIsExpired(true);
        session.update(rentRequest);
        session.getTransaction().commit();
        session.close();

    }

    public boolean productOnrent(int productId){
        Session session = this.sessionFactory.openSession();
        RentRequest rentRequest = (RentRequest) session.createQuery("FROM RentRequest returnRequest WHERE " +
                " returnRequest.rentalProduct.id =:productId")
                .setParameter("productId", productId)
                .setMaxResults(1)
                .uniqueResult();
        if(rentRequest != null)
            return  true;
        return  false;
    }
    public List<RentRequest> getAllPendingRequest(int maxId){
        Session session = this.sessionFactory.openSession();

        try{
            List<RentRequest>  rentRequestList = session.createQuery(
                    "FROM RentRequest rentRequest where " +
                            " rentRequest.approve = false " +
                            " and rentRequest.disapprove = false " +
                            " and rentRequest.isExpired = false " +
                            " and id>:maxId order by id asc")
                    .setParameter("maxId",maxId)
                    .list();
            return rentRequestList;
        }finally {
            session.close();
        }
    }

    public List<RentRequest> searchRentRequestAllByBetweenDates(String stDate, String edDate){
        Session session = this.sessionFactory.openSession();
        List<RentRequest> rentRequestList = new ArrayList<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilFrmDate = simpleDateFormat.parse(stDate);
            java.util.Date utilEnDate = simpleDateFormat.parse(edDate);

            Date frmDate = new java.sql.Date(utilFrmDate.getTime());
            Date enDate = new java.sql.Date(utilEnDate.getTime());

            rentRequestList = session.createQuery("FROM RentRequest RR WHERE " +
                    " RR.startDate BETWEEN :frmDate AND :enDate " +
                    " ORDER BY RR.id desc ")
                    .setParameter("frmDate", frmDate)
                    .setParameter("enDate", enDate)
                    .list();
            return rentRequestList;

        }catch (ParseException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return rentRequestList;
    }

    public List<RentRequest> searchRentRequestPendingByBetweenDates(String stDate, String edDate){
        Session session = this.sessionFactory.openSession();
        List<RentRequest> rentRequestList = new ArrayList<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilFrmDate = simpleDateFormat.parse(stDate);
            java.util.Date utilEnDate = simpleDateFormat.parse(edDate);

            Date frmDate = new java.sql.Date(utilFrmDate.getTime());
            Date enDate = new java.sql.Date(utilEnDate.getTime());

            rentRequestList = session.createQuery("FROM RentRequest RR WHERE " +
                    "RR.startDate BETWEEN :frmDate AND :enDate " +
                    " AND RR.isExpired = false " +
                    " AND RR.approve = false " +
                    " AND RR.disapprove = false " +
                    " ORDER BY RR.id desc ")
                    .setParameter("frmDate", frmDate)
                    .setParameter("enDate", enDate)
                    .list();
            return rentRequestList;

        }catch (ParseException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return rentRequestList;
    }

    public List<RentRequest> searchRentRequestOnProgressByBetweenDates(String stDate, String edDate){
        Session session = this.sessionFactory.openSession();
        List<RentRequest> rentRequestList = new ArrayList<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilFrmDate = simpleDateFormat.parse(stDate);
            java.util.Date utilEnDate = simpleDateFormat.parse(edDate);

            Date frmDate = new java.sql.Date(utilFrmDate.getTime());
            Date enDate = new java.sql.Date(utilEnDate.getTime());

            rentRequestList = session.createQuery("FROM RentRequest RR WHERE " +
                    "RR.startDate BETWEEN :frmDate AND :enDate " +
                    " AND RR.isExpired = false " +
                    " AND ( RR.approve = true or RR.isRentComplete = true )" +
                    " AND RR.isRentComplete = false" +
                    " ORDER BY RR.id desc ")
                    .setParameter("frmDate", frmDate)
                    .setParameter("enDate", enDate)
                    .list();
            return rentRequestList;

        }catch (ParseException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return rentRequestList;
    }

    public List<RentRequest> searchRentRequestCompleteByBetweenDates(String stDate, String edDate){
        Session session = this.sessionFactory.openSession();
        List<RentRequest> rentRequestList = new ArrayList<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilFrmDate = simpleDateFormat.parse(stDate);
            java.util.Date utilEnDate = simpleDateFormat.parse(edDate);

            Date frmDate = new java.sql.Date(utilFrmDate.getTime());
            Date enDate = new java.sql.Date(utilEnDate.getTime());

            rentRequestList = session.createQuery("FROM RentRequest RR WHERE " +
                    "RR.startDate BETWEEN :frmDate AND :enDate " +
                    " AND RR.isExpired = false " +
                    " AND RR.isRentComplete = true" +
                    " ORDER BY RR.id desc ")
                    .setParameter("frmDate", frmDate)
                    .setParameter("enDate", enDate)
                    .list();
            return rentRequestList;

        }catch (ParseException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return rentRequestList;
    }
}
