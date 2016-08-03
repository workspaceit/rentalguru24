package model;

import model.entity.app.IdentityType;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * Created by omar on 8/2/16.
 */
public class IdentityTypeModel extends BaseModel {
    public void insert(IdentityType identityType){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(identityType);
        session.getTransaction().commit();
        session.close();
    }

    public IdentityType getById(int id){
        Session session = this.sessionFactory.openSession();
        return session.get(IdentityType.class, id);
    }
    public boolean isExist(int id){
        Session session = this.sessionFactory.openSession();
        if(session.get(IdentityType.class, id)!=null)
            return true;
        return false;
    }

    public List<IdentityType> getAll(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM IdentityType";
        Query query = session.createQuery(hql);
        return (List<IdentityType>)query.list();
//        List result =query.list();
//        for (Iterator<IdentityType> iter = result.iterator(); iter.hasNext(); ) {
//            IdentityType element = iter.next();
//            System.out.println(element.getId());
//            System.out.println(element.getName());
//            System.out.println(element.getCreatedDate());
//        }
//        return result;
    }
}
