package helper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mi on 9/7/16.
 */
public class MailHelper {
    public static boolean sendPasswordRestMail(String email,String activationCode,String url){



        String to = email;
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
        String activationUrl =url+activationCode;

        String link = "<a href='"+activationUrl+"'>Click here</a>";
        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Password Reset");
            message.setText("Hi,<br>   Please click this link " + link + " to reset your password",null,"html");
            Transport.send(message);
            String title = "";
            String body = "";


        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }


        return true;
    }
}
