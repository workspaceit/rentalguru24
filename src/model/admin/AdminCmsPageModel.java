package model.admin;

import model.BaseModel;
import model.entity.admin.AdminCmsPage;
import model.entity.admin.AdminSiteFeesEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Tomal on 8/30/2016.
 */
public class AdminCmsPageModel extends BaseModel {


    public void insert(AdminCmsPage adminCmsPage){
        Session session=null;
        Transaction transaction=null;
        try {
            session=this.sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.save(adminCmsPage);
            transaction.commit();

        }catch (HibernateException ex){
            ex.printStackTrace();
            if (transaction!=null)
                transaction.rollback();
        }finally {
            session.close();
        }

    }
}
