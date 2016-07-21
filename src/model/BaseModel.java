package model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mi on 7/20/16.
 */
public class BaseModel {
    @Autowired
    protected SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println("TEST");
        this.sessionFactory = sessionFactory;
    }
}
