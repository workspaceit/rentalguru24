package model;

import model.entity.app.ProductRating;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
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
                        "WHERE  pR.productId = "+productId;
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
            String hql = "SELECT 1 FROM ProductRating pR WHERE pR.appCredential.id ="+id+" AND pR.productId ="+productId;
            Query query = session.createQuery(hql);
            return (query.uniqueResult() != null);
        }finally {
            session.close();
        }
    }

    public List<ProductRating> getByProductId(int productId) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            return  session.createQuery("FROM ProductRating pR WHERE pR.productId = :productId").setParameter("productId", productId).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (session != null)
                session.close();
        }
    }
    public void delete(ProductRating productRating){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(productRating);
        session.getTransaction().commit();
        session.close();
    }

    public boolean isAlreadyRatedByUser(int productId, int rentInfId, int rentRequestId, int appCridentialId){
        ProductRating productRating = this.getAlreadyRatedByUser(productId,rentInfId,rentRequestId, appCridentialId);
        return (productRating!=null)?true:false;
    }

    public ProductRating getAlreadyRatedByUser(int productId, int rentInfId, int rentRequestId, int appCridentialId){
        Session session = this.sessionFactory.openSession();
        try{
            ProductRating productRating = (ProductRating)
                    session.createQuery(" FROM ProductRating productRating " +
                            " WHERE productRating.productId =:productId " +
                            " AND productRating.rentInfId =:rentInfId" +
                            " AND productRating.rentRequestId =:rentRequestId" +
                            " AND productRating.appCredential.id =:appCridentialId")
                            .setParameter("productId", productId)
                            .setParameter("rentInfId", rentInfId)
                            .setParameter("rentRequestId", rentRequestId)
                            .setParameter("appCridentialId", appCridentialId)
                            .setMaxResults(1)
                            .uniqueResult();
            return productRating;
        }finally {
            session.close();
        }
    }
}
