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
        Session session = this.sessionFactory.openSession();
        String hql = "from RentProduct where ( RentProduct.endsDate <= :endsDate and RentProduct.startDate >= :endsDate ) or" +
                    " ( RentProduct.endsDate <= :startDate and RentProduct.startDate >= :startDate ) and RentProduct.productId = :productId ";

        Query query =  session.createQuery(hql);

        query.setParameter("productId", productId);
        query.setParameter("startDate",startDate);
        query.setParameter("endsDate", endsDate);

        RentProduct rentProduct = (RentProduct)query.uniqueResult();
        return ( rentProduct != null  );
    }
}
