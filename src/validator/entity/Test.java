package validator.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mi on 8/2/16.
 */
public class Test {
    public static void main(String args[]){
        String to = "rafi101010@gmail.com";
        String from = "developer_beta@workspaceit.com";
        Properties properties = System.getProperties();
        // properties.put("mail.smtp.starttls.enable", "true");
        String username = "developer_beta@workspaceit.com";
        String password =  "wsit_cabguard1";


        properties.put("mail.smtp.host", "hera.ihostman.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user",username ); // User name
        properties.put("mail.smtp.password",password); // password
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username,password);// Specify the Username and the PassWord
            }
        });

        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Activate your account");
            message.setText("Hi,<br>  registration/active/user?activation_code ",null,"html");
            Transport.send(message);
            String title = "";
            String body = "";


        }catch (MessagingException mex) {
            mex.printStackTrace();

        }

    }
}
