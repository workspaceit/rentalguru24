package model;


import model.entity.app.product.SearchedProduct;
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
        if(limit > 15){
            limit = 15;
        }
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Product P ORDER BY P.id DESC";
        Query query =  session.createQuery(hql);
        query.setFirstResult(offset*limit);
        query.setMaxResults(limit);
        return (List<Product>)query.list();
    }
    public List<SearchedProduct> getSearchedProduct(int limit, int offset){
        if(limit > 15){
            limit = 15;
        }
        Session session = this.sessionFactory.openSession();
        String hql = "FROM SearchedProduct P ORDER BY P.id DESC";
        Query query =  session.createQuery(hql);
        query.setFirstResult(offset*limit);
        query.setMaxResults(limit);
        return (List<SearchedProduct>)query.list();
    }

    public Product getProductSearchById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        return session.get(Product.class,id);
    }
}
