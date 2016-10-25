package model;

import model.entity.BannerImage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mi on 10/24/16.
 */
public class BannerImageModel extends BaseModel{
    public void insert(BannerImage bannerImage){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(bannerImage);
        session.getTransaction().commit();
        session.close();
    }
    public List<BannerImage> getAll(){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM BannerImage ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List result = query.list();
        return result;
    }
    public BannerImage getById(int id){
        Session session = this.sessionFactory.openSession();
        try{
            return session.get(BannerImage.class,id);
        }finally {
            session.close();
        }
    }

    public void delete(BannerImage bannerImage){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(bannerImage);
        session.getTransaction().commit();
        session.close();
    }
}
