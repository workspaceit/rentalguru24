package ConsoleTesting;


import helper.DateHelper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;

import static java.lang.System.out;

/**
 * Created by mi on 9/21/16.
 */
public class Test {

    public static void main(String args[]) {
        System.out.println("Main method");
        Derived d = new Derived();
    }

}


class Base {
    static {
        System.out.println("Base static block");
    }
    {
        System.out.println("Base instance block");
    }

    public Base() {
        System.out.println("Base constructor");
    }
}

class Derived extends Base {
    static {
        System.out.println("Derived static block");
    }
    {
        System.out.println("Derived instance block");
    }

    public Derived() {
        System.out.println("Derived constructor");
    }

    public static void main(String[] args) {

    }
}