package model;

import model.entity.app.Category;
import model.entity.app.RentProduct;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by omar on 8/3/16.
 */
public class RentProductModel extends BaseModel {
    public void insert(RentProduct rentProduct){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentProduct);
        session.getTransaction().commit();
        session.close();
    }
    public boolean isProductInRent(int productId,Timestamp startDate,Timestamp endsDate){
        RentProduct rentProduct = this.getProductInRent(productId,startDate,endsDate);
        return ( rentProduct != null  );
    }
    public RentProduct getProductInRent(int productId,Timestamp startDate,Timestamp endsDate){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RentProduct where ( ( startDate BETWEEN :startDate and :endsDate ) or" +
                    " ( endsDate BETWEEN :startDate and :endsDate ) ) and rentalProduct.id = :productId and expired = false ";

            Query query =  session.createQuery(hql);

            query.setParameter("productId", productId);
            query.setParameter("startDate",startDate);
            query.setParameter("endsDate", endsDate);

            return  (RentProduct)query.uniqueResult();
        }finally {
            session.close();
        }
    }
}
