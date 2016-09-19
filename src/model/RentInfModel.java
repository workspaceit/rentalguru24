package model;

import model.entity.app.product.rentable.RentInf;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by omar on 8/3/16.
 */
public class RentInfModel extends BaseModel {
    public RentInf getById(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try {
            return session.get(RentInf.class, id);
        } finally {
            session.close();
        }
    }
    public void insert(RentInf rentInf){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentInf);
        session.getTransaction().commit();
        session.close();
    }
    public void update(RentInf rentInf){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentInf);
        session.getTransaction().commit();
        session.close();
    }
    public boolean isProductInRent(int productId,Timestamp startDate,Timestamp endsDate){
        RentInf rentInf = this.getProductInRent(productId, startDate, endsDate);
        return ( rentInf != null  );
    }
    public RentInf getByRentRequestId(int requestId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RentInf rentInf where  rentInf.rentRequest.id = :rentRequestId";

            return  (RentInf)session.createQuery(hql)
                    .setParameter("rentRequestId", requestId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public RentInf getProductInRent(int productId,Timestamp startDate,Timestamp endsDate){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RentInf where ( ( startDate BETWEEN :startDate and :endsDate ) or" +
                    " ( endsDate BETWEEN :startDate and :endsDate ) ) " +
                    " and rentalProduct.id = :productId " +
                    " and expired = false ";

            return  (RentInf) session.createQuery(hql).setParameter("productId", productId)
                    .setParameter("startDate", startDate)
                    .setParameter("endsDate", endsDate)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }

    public List<RentInf> getProductByAppcridentialId(int appcridentialId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "FROM RentInf rentInf WHERE rentInf.rentee.id = :appcridentialId";
            return session.createQuery(hql).setParameter("appcridentialId", appcridentialId).list();
        }finally {
            session.close();
        }
    }
}
