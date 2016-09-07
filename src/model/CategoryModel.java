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
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(int categoryId){
        Category category = this.getById(categoryId);
        if(category == null) return;

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(List<Category> categoryList){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        for(Category category : categoryList){
            for(Category subCategory : category.getSubcategory()){
                session.delete(subCategory);
            }
            session.delete(category);
        }
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Category category){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
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
            return session.createQuery("select distinct category FROM Category category LEFT JOIN FETCH category.subcategory where category.isSubcategory = false ORDER BY category.sortedOrder")
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

    public int maxSortOrder(){
        Session session = this.sessionFactory.openSession();
        try{
            return (int) session.createQuery("SELECT MAX(category.sortedOrder) FROM Category category").uniqueResult();
        }finally {
            session.close();
        }
    }

}
