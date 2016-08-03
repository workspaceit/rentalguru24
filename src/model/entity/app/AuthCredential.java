package model.entity.app;

import javax.persistence.Entity;

/**
 * Created by mi on 8/2/16.
 */
@Entity
public class AuthCredential extends AppCredential {
    String abc;

    public String getAbc() {
        return "54657";
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }
}
