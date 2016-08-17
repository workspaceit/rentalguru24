package validator.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mi on 8/2/16.
 */
public class Test {
    public static void main(String args[]){
        Product a = new RentalProduct();
        System.out.println(a.getClass());
        System.out.println(((RentalProduct)a).isRentable);
        Product b = new SellableProduct();
        System.out.println(b.getClass());
        System.out.println(((SellableProduct)b).isSellable);
        new H();
        new H();
    }
}
class H{
    public String a;
    public List<Integer> intList;

    {
        a= "Static";
        intList = new ArrayList<>();
        System.out.println("Executed");
    }
}

abstract class  Product{

}
class RentalProduct extends Product{
    public boolean isRentable = false;
}
class SellableProduct extends Product{
    public boolean isSellable = true;
}