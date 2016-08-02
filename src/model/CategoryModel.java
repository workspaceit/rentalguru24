package model;

import model.entity.app.Category;
import org.hibernate.Session;

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
}
