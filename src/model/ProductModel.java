package model;


import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.SearchedProduct;
import org.hibernate.Query;

import model.entity.app.product.rentable.iface.RentalProduct;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by omar on 8/8/16.
 */
public class ProductModel extends BaseModel{
    public void insert(RentalProduct rentalProduct){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }
    public void update(RentalProduct rentalProduct){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }
    public RentalProductEntity getEntityById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return session.get(RentalProductEntity.class, id);
        }finally {
            session.close();
        }
    }
    public RentalProduct getById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            session.beginTransaction();
            return session.get(RentalProductEntity.class,id);
        }finally {
            session.close();
        }
    }

    public List<RentalProduct> getRentalProduct(int limit, int offset){
        if(limit > 15){
            limit = 15;
        }
        String hql = "FROM RentalProductEntity P  ORDER BY P.id DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return  session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        }finally {
            session.close();
        }
    }
    public List<RentalProduct> getRentalProduct(int limit, int offset, int productId){
        if(limit > 15){
            limit = 15;
        }
        String hql = "FROM RentalProductEntity P WHERE P.id !=:productId  ORDER BY P.id DESC" ;
        Session session = this.sessionFactory.openSession();
        try {
            return  session.createQuery(hql)
                    .setParameter("productId", productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        }finally {
            session.close();
        }
    }
    public List<SearchedProduct> getSearchedProduct(int limit, int offset){
        if(limit > 15){
            limit = 15;
        }
        Session session = this.sessionFactory.openSession();
        try {
            String hql = "FROM RentalProductEntity P ORDER BY P.id DESC";
            Query query = session.createQuery(hql);
            query.setFirstResult(offset * limit);
            query.setMaxResults(limit);
            return (List<SearchedProduct>) query.list();
        }finally {
            session.close();
        }
    }

    public RentalProduct getProductSearchById(int id){
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductEntity.class,id);
        }finally {
            session.close();
        }
    }

}
