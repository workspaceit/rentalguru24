package ConsoleTesting;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Mausum on 6/1/2016.
 */
public class BaseModel  {
    public static SessionFactory getSessionFactory()
    {
        return new Configuration().configure().buildSessionFactory();

    }

    public static Session getSession(SessionFactory factory) throws HibernateException {
        return factory.openSession();
    }
    public static void shutdown(SessionFactory factory) {
        // Close caches and connection pools

        factory.close();
    }
}
