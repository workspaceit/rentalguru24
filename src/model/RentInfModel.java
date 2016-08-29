package model;

import model.entity.app.product.rentable.RentInf;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;

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
        RentInf rentInf = this.getProductInRent(productId,startDate,endsDate);
        return ( rentInf != null  );
    }
    public RentInf getProductInRent(int productId,Timestamp startDate,Timestamp endsDate){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RentInf where ( ( startDate BETWEEN :startDate and :endsDate ) or" +
                    " ( endsDate BETWEEN :startDate and :endsDate ) ) " +
                    " and rentalProduct.id = :productId " +
                    " and expired = false ";

            Query query =  session.createQuery(hql);

            query.setParameter("productId", productId);
            query.setParameter("startDate",startDate);
            query.setParameter("endsDate", endsDate);

            return  (RentInf)query.uniqueResult();
        }finally {
            session.close();
        }
    }
}
