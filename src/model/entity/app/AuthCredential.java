package model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by mi on 8/2/16.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "app_login_credential", schema = "", catalog = "rentguru24")
public class AuthCredential extends AbstractCredential {

    private String password;
    private String accesstoken;
    private boolean verified;
    private boolean blocked;

    @JsonIgnore
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "accesstoken")
    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }



    @Basic
    @Column(name = "varified")
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean varified) {
        this.verified = varified;
    }

    @Basic
    @Column(name = "blocked")
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
