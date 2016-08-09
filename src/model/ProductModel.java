package model;


import org.hibernate.Query;

import model.entity.app.product.Product;

import org.hibernate.Session;

import java.util.List;

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
    public List<Product> getProductSearch(int limit, int offset){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM SerchedProduct";
        Query query =  session.createQuery(hql);
        return (List<Product>)query.list();
    }
}
