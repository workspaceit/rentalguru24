package model;

import model.entity.app.Category;
import org.hibernate.Session;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by omar on 8/1/16.
 */
public class CategoryModel extends BaseModel {
    public void insert(Category category){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }
    public Category getById(int id){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Category where id = :id";
        Query query =  session.createQuery(hql);
        query.setParameter("id", id);
        try{
            return (Category)query.uniqueResult();
        }finally{
            session.close();
        }
    }
    public List<Category> getAllCategoryParent(){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("select distinct category FROM Category category where category.isSubcategory = false")
                    .list();
        }finally{
            session.close();
        }
    }
    public List<Category> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("select distinct category FROM Category category LEFT JOIN FETCH category.subcategory where category.isSubcategory = false")
                    .list();
        }finally{
            session.close();
        }
    }
    public List<Category> getByParentId(int parentId){
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery("select distinct category FROM Category category INNER JOIN FETCH category.subcategory where category.id =:parentId")
                    .setParameter("parentId", parentId).list();
        }finally{
            session.close();
        }
    }
}
