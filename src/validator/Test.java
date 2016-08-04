package validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by mi on 8/2/16.
 */
public class Test {
    public static void main(String args[]){
        String p = "12345";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(p);
        System.out.println("HELLOZ  ---->  " + encodedPassword);
    }
}
