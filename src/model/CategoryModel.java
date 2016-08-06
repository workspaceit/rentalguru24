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
    public List<Category> getAll(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Category where parent_id = NULL";
        Query query =  session.createQuery(hql);
        //query.setParameter("parent_id",null);
        return (List<Category>)query.list();
    }
}
