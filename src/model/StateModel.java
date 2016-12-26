package model;

import model.entity.State;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mi on 11/29/16.
 */
public class StateModel extends BaseModel {

    public List<State> getAll(){
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery("select distinct state FROM State state left join fetch state.cities ").list();
        }finally {
            session.close();
        }
    }

    public State getById(int id){
        Session session = this.sessionFactory.openSession();
        try {
            return (State)session.createQuery("FROM State state left join fetch state.cities WHERE state.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }

    public State getByCode(String code){
        Session session = this.sessionFactory.openSession();
        try {
            return (State)session.createQuery("FROM State state left join fetch state.cities WHERE state.code = :code")
                    .setParameter("code", code)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
}
