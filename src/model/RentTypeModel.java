package model;

import model.entity.app.RentType;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mi on 8/11/16.
 */
public class RentTypeModel extends BaseModel {

    public List<RentType> getAll(){
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery("from RentType").list();
        }finally {
            session.close();
        }
    }
    public RentType getById(int id){
        Session session = this.sessionFactory.openSession();
        try {
            return (RentType)session.createQuery("from RentType where id = "+id).uniqueResult();
        }finally {
            session.close();
        }
    }

}
