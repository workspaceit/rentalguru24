package model;

import model.entity.app.product.ProductCategory;
import org.hibernate.Session;

/**
 * Created by mi on 11/11/16.
 */
public class ProductCategoryModel extends BaseModel {
    public void insert(ProductCategory productCategory){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(productCategory);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(ProductCategory productCategory){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(productCategory);
        session.getTransaction().commit();
        session.close();
    }
}
