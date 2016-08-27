package model;

import model.entity.app.product.rentable.RequestProductReturn;
import org.hibernate.Session;

/**
 * Created by mi on 8/26/16.
 */
public class RequestProductReturnModel extends BaseModel {

    public RequestProductReturn getById(int id){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try{
            return session.get(RequestProductReturn.class, id);
        }finally {
            session.close();
        }
    }
    public void insert(RequestProductReturn requestProductReturn){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(requestProductReturn);
        session.getTransaction().commit();
        session.close();
    }
}
