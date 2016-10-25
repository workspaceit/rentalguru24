package model;

import model.entity.BannerImage;
import org.hibernate.Session;

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
}
