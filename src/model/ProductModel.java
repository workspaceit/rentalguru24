package model;

import model.entity.app.Product;
import org.hibernate.Session;

/**
 * Created by omar on 8/8/16.
 */
public class ProductModel extends BaseModel{
    public void insert(Product product){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }
    public void update(Product product){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }
    public Product getById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        return session.get(Product.class,id);
    }
}
