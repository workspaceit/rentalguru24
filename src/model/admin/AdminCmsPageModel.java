package model.admin;

import model.BaseModel;
import model.entity.admin.AdminCmsPage;
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
    public List<AdminCmsPage> getAll(){
        Session session=this.sessionFactory.openSession();
        try {
            return session.createQuery("FROM AdminCmsPage order by sortedOrder asc ")
                    .list();
        }finally {
            session.close();
        }
    }
    public AdminCmsPage getByPageKey(String pageKey){
        Session session=null;
        Transaction transaction=null;
        try {
            session=this.sessionFactory.openSession();
            return (AdminCmsPage)session.createQuery("FROM AdminCmsPage where pageKey = :pageKey ")
                    .setParameter("pageKey",pageKey)
                    .setMaxResults(1)
                    .uniqueResult();


        }finally {
            session.close();
        }
    }
    public Integer maxSortOrder(){
        Session session = this.sessionFactory.openSession();
        try{
            return (Integer) session.createQuery("SELECT MAX(adminCmsPage.sortedOrder) FROM AdminCmsPage adminCmsPage").uniqueResult();
        }finally {
            session.close();
        }
    }
}
