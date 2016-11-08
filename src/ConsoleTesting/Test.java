package ConsoleTesting;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.lang.System.out;

/**
 * Created by mi on 9/21/16.
 */
public class Test {

    public static void main(String args[]){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hasPass = bCryptPasswordEncoder.encode("123456");
        out.println(hasPass);
        out.println(bCryptPasswordEncoder.matches("asdasd4","$2a$10$xCRWnpjAarYndXaAQ.PvOeqhKQFQPbeEssqXATLveI8SA/5QxIfKm"));
    }


}




