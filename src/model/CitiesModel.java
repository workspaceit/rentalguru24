package model;

import model.entity.Cities;
import model.entity.State;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mi on 11/29/16.
 */
public class CitiesModel extends BaseModel {



    public Cities getById(int id){
        Session session = this.sessionFactory.openSession();
        try {
            return (Cities)session.createQuery("FROM Cities city WHERE city.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }


}
