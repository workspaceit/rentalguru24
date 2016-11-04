package helper;

import model.entity.app.RentRequest;
import model.entity.app.product.rentable.iface.RentalProduct;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by mi on 9/7/16.
 */
public class EmailHelper {
    private static String BASEURL = "http://rentguru24.com";
    private final static String from="developer_beta@workspaceit.com";
    private final static String username="developer_beta@workspaceit.com";
    private final static String password="wsit_cabguard1";



    private static Properties getProperties(){
        Properties properties = System.getProperties();
        // properties.put("mail.smtp.starttls.enable", "true");


        properties.put("mail.smtp.host", "hera.ihostman.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user",username ); // User name
        properties.put("mail.smtp.password",password); // password
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return properties;
    }
    public static boolean sendPasswordRestMail(String email,String activationCode,String url){



        String to = email;
        Properties properties = System.getProperties();
        // properties.put("mail.smtp.starttls.enable", "true");


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
            message.setText("Hi,<br>   Please click this link " + link + " to reset your password <br> For app user use this token :<b>"+activationCode+"<b>",null,"html");
            Transport.send(message);
            String title = "";
            String body = "";


        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }


        return true;
    }
    public static boolean rentalProductApprovalDisapprovalEmail(RentalProduct rentalProduct,boolean approve){



        String to = rentalProduct.getOwner().getEmail();
        Properties properties = getProperties();

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });
        String statusText = (approve)?"approve":"disapprove";
        String productDetailsUrl = "<a href='"+BASEURL+"/product/details/"+rentalProduct.getId()+"'>"+rentalProduct.getName()+"</a>";
        String ownerFirstName = rentalProduct.getOwner().getUserInf().getFirstName();
        String ownerLastName = rentalProduct.getOwner().getUserInf().getLastName();
        String ownerFullName = ownerFirstName+" "+ownerLastName;
        String emailHtmlBody = "Dear "+ownerFullName+" "+"<br> Your product "+productDetailsUrl+" has been "+statusText+".<br> Thank you for using <a href='http://rentguru24.com'>rentguru24.com</a> ";
        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru24 : You product has been " + statusText);
            message.setText(emailHtmlBody, null,"html");
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }


        return true;
    }
    public static boolean rentalRequestApprovalEmail(RentRequest rentRequest,boolean approve){



        String to = rentRequest.getRequestedBy().getEmail();
        Properties properties = getProperties();

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });
        String statusText = (approve)?"approve":"disapprove";
        String rentRequestDetails = "<a href='"+BASEURL+"/rent/request/"+rentRequest.getId()+"'>click here</a>";
        String ownerFirstName = rentRequest.getRequestedBy().getUserInf().getFirstName();
        String ownerLastName = rentRequest.getRequestedBy().getUserInf().getLastName();
        String ownerFullName = ownerFirstName+" "+ownerLastName;
        String emailHtmlBody = "Dear "+ownerFullName+" "+"<br> Your rent request has been approved. Please check your order  "+rentRequestDetails+". <br>Thank you for using <a href='http://rentguru24.com'>rentguru24.com</a> ";
        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru24 : Your rent request has been approved " + statusText);
            message.setText(emailHtmlBody, null,"html");
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }


        return true;
    }
    public static boolean signUpConfirmationMail(String email,String activationCode,String confirmUrl,String diapproveUrl){



        String to = email;
        Properties properties = System.getProperties();
        // properties.put("mail.smtp.starttls.enable", "true");


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
        String activationUrl = null;
        try {
            String params = URLEncoder.encode(email,"UTF-8")+"/"+URLEncoder.encode(activationCode, "UTF-8");
            activationUrl = confirmUrl+params;
            diapproveUrl = diapproveUrl+params;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String confirmLinkHtml = "<a href='"+activationUrl+"'>Click here</a>";
        String disapproveLinkHtml = "<a href='"+diapproveUrl+"'>Click here</a>";
        String emailHtmlBody = "Hi,<br>   Please click this link " + confirmLinkHtml + " to confirm , or to deny "+disapproveLinkHtml;
        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru account email confirmation");
            message.setText(emailHtmlBody,null,"html");
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
