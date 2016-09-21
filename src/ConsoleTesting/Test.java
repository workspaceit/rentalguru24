package ConsoleTesting;

/**
 * Created by mi on 9/21/16.
 */
public class Test {
    public static void main(String args[]){
        String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-13T60597PP2972138";
        String newUrl = url.replaceAll("https://www.paypal.com/","https://www.sandbox.paypal.com/");
        System.out.println(newUrl);


        String payPalDate  = "2016-09-21T05:52:26Z";
    }
}
