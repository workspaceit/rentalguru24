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
        session.save(productRating);
        session.getTransaction().commit();
        session.close();
    }
    public float averageRating(int productId){
        Session session = this.sessionFactory.openSession();
//        String hql = "SELECT AVG(ProductRating.rateValue) as avgRating FROM ProductRating pR WHERE  pR.product_id = "+productId;
        String hql = "SELECT AVG(ProductRating.rateval) AS averageRating, WHERE ProductRating.product_id="+productId;
        Query query = session.createQuery(hql);
        float result = (float) query.uniqueResult();
        return result;
    }
}
