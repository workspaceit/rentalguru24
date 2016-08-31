package model.admin;

import model.BaseModel;
import model.entity.admin.AdminSiteFeesEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Tomal on 8/30/2016.
 */
public class AdminSitesFeesModel extends BaseModel {
    public AdminSiteFeesEntity getAdminSiteFees(){
        AdminSiteFeesEntity adminSiteFeesEntity=null;
        Session session=null;
        try {
            session=this.sessionFactory.openSession();
            List<AdminSiteFeesEntity> adminSiteFeesEntities=session.createQuery("FROM AdminSiteFeesEntity").list();
            if (adminSiteFeesEntities.size()==1){
                adminSiteFeesEntity=adminSiteFeesEntities.get(0);
            }
            return adminSiteFeesEntity;

        }finally {
            session.close();
        }

    }

    public void updateSiteFees(AdminSiteFeesEntity adminSiteFeesEntity){
        Session session=null;
        Transaction transaction=null;
        try {
            session=this.sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.update(adminSiteFeesEntity);
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
