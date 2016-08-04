package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 7/25/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "app_login_credential", schema = "", catalog = "rentguru24")
public class AppCredential extends AbstractCredential{
    public AppCredential() {
        this.id =0;
        this.userId=0;
        this.role=0;
        this.email="";

        this.createdDate =null;
        this.userInf = new UserInf();
    }
}
