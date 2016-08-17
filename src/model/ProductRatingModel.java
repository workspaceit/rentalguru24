package model;

import model.entity.app.ProductRating;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by omar on 8/12/16.
 */
public class ProductRatingModel extends BaseModel {
    public void insert(ProductRating productRating){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(productRating);
        session.getTransaction().commit();
        session.close();
    }
    public double averageRating(int productId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "SELECT COALESCE(AVG(pR.rateValue),0) as avgRating " +
                        "FROM ProductRating pR " +
                        "WHERE  pR.product.id = "+productId;
    //        String hql = "SELECT AVG(ProductRating.rateval) AS averageRating, WHERE ProductRating.product_id="+productId;
            Query query = session.createQuery(hql);
            double result = (double) query.uniqueResult();
            System.out.println("Average result "+result);
            result = Math.round(result * 100.0) / 100.0;
            return result;
        }finally {
            session.close();
        }
    }

    public boolean getAuthorization(int id, int productId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "SELECT 1 FROM ProductRating pR WHERE pR.appCredential.id ="+id+" AND pR.product.id ="+productId;
            Query query = session.createQuery(hql);
            return (query.uniqueResult() != null);
        }finally {
            session.close();
        }
    }
}
