package model;

import model.entity.app.Attributes;
import model.entity.app.Category;
import org.hibernate.Session;

/**
 * Created by omar on 8/1/16.
 */
public class AttributesModel extends BaseModel{
    public void insert(Attributes attributes){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(attributes);
        session.getTransaction().commit();
        session.close();
    }
    public Attributes getById(){
        Session session = this.sessionFactory.openSession();
        return session.get(Attributes.class, 2);
    }
}
