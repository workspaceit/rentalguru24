package validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * Created by mi on 8/2/16.
 */
public class Test {
    public static void main(String args[]){
        String p = "12345";
       ;
                String encodedPassword =  DigestUtils.md5DigestAsHex(p.getBytes());
        System.out.println("HELLOZ  ---->  " + encodedPassword);
    }
}
