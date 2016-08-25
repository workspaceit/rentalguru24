package model;


import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.MyRentalProduct;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import model.entity.app.product.rentable.iface.RentalProduct;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar on 8/8/16.
 */
public class ProductModel extends BaseModel {
    public void insert(RentalProduct rentalProduct) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }

    public void update(RentalProduct rentalProduct) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }

    public RentalProductEntity getEntityById(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try {
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public RentalProduct getById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public List<RentalProductEntity> getRentalProduct() {
        Session session = null;
        List rentalProducts = null;
        try {
            session = this.sessionFactory.openSession();
            rentalProducts = session.createQuery("from RentalProductEntity R ORDER BY R.id DESC").list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return rentalProducts;
    }

    public List<RentalProduct> getRentalProduct(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
                return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity P  ORDER BY P.id DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }

    public List<RentalProduct> getRentalProduct(int limit, int offset, int productId) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity P WHERE P.id !=:productId  ORDER BY P.id DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setParameter("productId", productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }

    public List<SearchedProduct> getSearchedProduct(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        Session session = this.sessionFactory.openSession();
        try {
            String hql = "FROM RentalProductEntity P ORDER BY P.id DESC";
            Query query = session.createQuery(hql);
            query.setFirstResult(offset * limit);
            query.setMaxResults(limit);
            return (List<SearchedProduct>) query.list();
        } finally {
            session.close();
        }
    }

    public RentalProduct getProductSearchById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public RentalProduct getMyRentalProductById(int id, int ownerId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity P  where P.id =:id and P.owner.id = :ownerId";
        try {
            return (RentalProduct) session.createQuery(hql)
                    .setParameter("id", id)
                    .setParameter("ownerId", ownerId)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<RentalProduct> getMyRentalProductList(int ownerId, int limit, int offset) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentalProduct LEFT JOIN FETCH myRentalProduct.rentInf where myRentalProduct.owner.id = :ownerId ORDER BY myRentalProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("ownerId", ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }
    public List<RentalProduct> getMyRentalProductList(int ownerId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentalProduct LEFT JOIN FETCH myRentalProduct.rentInf where myRentalProduct.owner.id = :ownerId ORDER BY myRentalProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("ownerId", ownerId)
                    .list();
        } finally {
            session.close();
        }
    }

    public boolean approveRentalProduct(int productId) {
        Session session = null;
        boolean flag = true;
        Transaction transaction = null;
        try {

            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RentalProductEntity rentalProductEntity = session.get(RentalProductEntity.class, productId);
            rentalProductEntity.setReviewStatus(true);
            session.update(rentalProductEntity);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
            if (transaction != null)
                transaction.rollback();
        } finally {


            if (session != null)
                session.close();

        }

        return flag;

    }

    public boolean disapproveRentalProduct(int productId) {
        Session session = null;
        boolean flag = true;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RentalProductEntity rentalProductEntity = session.get(RentalProductEntity.class, productId);
            rentalProductEntity.setReviewStatus(false);
            session.update(rentalProductEntity);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
            if (transaction != null)
                transaction.rollback();
        } finally {


            if (session != null)
                session.close();
        }

        return flag;

    }

}
