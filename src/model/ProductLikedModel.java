package model;

import model.entity.app.product.ProductLiked;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by omar on 8/22/16.
 */
public class ProductLikedModel extends BaseModel {
    public ProductLiked insert(ProductLiked productLiked){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(productLiked);
        session.getTransaction().commit();
        session.close();
        return productLiked;
    }

    public boolean isLiked(int productId, int appCridentialId){
        ProductLiked productLiked = this.getAlreadyLiked(productId, appCridentialId);
        return (productLiked!=null)?true:false;
    }

    public void delete(int prductId, int appCridentialId){

        ProductLiked productLiked = this.getAlreadyLiked(prductId, appCridentialId);
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(productLiked);
        session.getTransaction().commit();
        session.close();
    }

    public ProductLiked getAlreadyLiked(int prductId, int appCridentialId){
        Session session = this.sessionFactory.openSession();
        try{
            ProductLiked productLiked = (ProductLiked)
                                        session.createQuery(" FROM ProductLiked productLiked " +
                                                            " WHERE productLiked.rentalProduct.id =:prductId " +
                                                            " AND productLiked.appCredential.id =:appCridentialId")
                                                            .setParameter("prductId", prductId)
                                                            .setParameter("appCridentialId", appCridentialId)
                                                            .setMaxResults(1)
                                                            .uniqueResult();
            return productLiked;
        }finally {
            session.close();
        }
    }
}
