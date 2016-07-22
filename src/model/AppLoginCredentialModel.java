package model;

import model.entity.app.AEntity;
import model.entity.app.AppLoginCredentialEntity;
import model.entity.app.DeviceInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mi on 7/20/16.
 */
public class AppLoginCredentialModel extends BaseModel {
    public AppLoginCredentialEntity getById(){
        Session session = this.sessionFactory.openSession();



        return session.get(AppLoginCredentialEntity.class,1);
    }
    public AppLoginCredentialEntity getByPassword(){
        Session session = this.sessionFactory.openSession();
        String hql = "from AppLoginCredentialEntity where password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("password","123456789");
        return (AppLoginCredentialEntity)query.uniqueResult();
    }

    public void insert(AppLoginCredentialEntity appLoginCredentialEntity){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(appLoginCredentialEntity.getUserInfByUserInfId());
        appLoginCredentialEntity.setUserInfId(appLoginCredentialEntity.getUserInfByUserInfId().getId());
        session.save(appLoginCredentialEntity);
        Integer Deviceid= appLoginCredentialEntity.getId();
        Collection<DeviceInfoEntity> List = appLoginCredentialEntity.getDeviceInfosById();
        for (DeviceInfoEntity device : List){
            device.setAppCredentialId(Deviceid);
            session.save(device);
        }
        session.getTransaction().commit();
        session.close();
    }
    public AEntity aEntity(){
        Session session = this.sessionFactory.openSession();
        return session.get(AEntity.class,2);
    }

}
