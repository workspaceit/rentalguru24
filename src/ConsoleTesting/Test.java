package ConsoleTesting;


import helper.EmailHelper;
import helper.template_engine.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mi on 9/21/16.
 */

public class Test {


    static Map<String,AdminEmailDetails>  adminEmailDetailsMap = new HashMap<>();
    public static void main(String [] args) {


        String to = "rafi101010@gmail.com";
        Properties properties = System.getProperties();
         properties.put("mail.smtp.starttls.enable", "true");

        String host = "mail.rentguru24.com";
        String username  ="no-reply@rentguru24.com";
        String password ="qwe112233";

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user",username ); // User name
        properties.put("mail.smtp.password",password); // password
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", host);
      //  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });

        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress("no-reply@rentguru24.com"));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru24 : Your rent request has been  ");
            message.setText("TEST", null, "html");



            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();

        }



    }
}
class AdminEmailDetails{
    String subject;
    StringBuilder emailBody = new StringBuilder();

    @Override
    public String toString() {
        return "AdminEmailDetails{" +
                "subject='" + subject + '\'' +
                ", emailBody=" + emailBody +
                '}';
    }
}
