package model;

import model.entity.app.ProductRating;
import org.hibernate.Query;
import org.hibernate.Session;



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
        Session session = this.sessionFactory.openSession();
        String hql = "SELECT AVG(pR.rateValue) as avgRating " +
                    "FROM ProductRating pR " +
                    "WHERE  pR.product.id = "+productId;
//        String hql = "SELECT AVG(ProductRating.rateval) AS averageRating, WHERE ProductRating.product_id="+productId;
        Query query = session.createQuery(hql);
        double result = (double) query.uniqueResult();
        return result;
    }
}
