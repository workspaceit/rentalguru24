package helper;

import model.entity.app.AppCredential;
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
        String emailHtmlBody = "Hi,<br>   Please click this link " + link + " to reset your password <br> For app user use this token :<b>"+activationCode+"<b>";
        emailHtmlBody = templateTop+emailHtmlBody+templateBottom;
        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Password Reset");

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
        emailHtmlBody = templateTop+emailHtmlBody+templateBottom;

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
        String statusText = (approve)?"approved":"disapproved";
        String rentRequestDetails = "<a href='"+BASEURL+"/rent/request/"+rentRequest.getId()+"'>click here</a>";
        String ownerFirstName = rentRequest.getRequestedBy().getUserInf().getFirstName();
        String ownerLastName = rentRequest.getRequestedBy().getUserInf().getLastName();
        String ownerFullName = ownerFirstName+" "+ownerLastName;
        String emailHtmlBody = "Dear "+ownerFullName+" "+"<br> Your rent request has been approved. Please check your order  "+rentRequestDetails+". <br>Thank you for using <a href='http://rentguru24.com'>rentguru24.com</a> ";
        emailHtmlBody = templateTop+emailHtmlBody+templateBottom;

        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru24 : Your rent request has been  " + statusText);
            message.setText(emailHtmlBody, null,"html");
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }


        return true;
    }
    public static boolean accountApprovalEmail(AppCredential appCredential){



        String to = appCredential.getEmail();
        Properties properties = getProperties();

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });

        String ownerFirstName = appCredential.getUserInf().getFirstName();
        String ownerLastName = appCredential.getUserInf().getLastName();
        String ownerFullName = ownerFirstName+" "+ownerLastName;
        String emailHtmlBody = "Dear "+ownerFullName+" "+"<br> Your account has been verified";
        emailHtmlBody = templateTop+emailHtmlBody+templateSignInBottom;

        try{

            MimeMessage message = new MimeMessage(session);

            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("RentGuru24 : Your account has been approved ");
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
        emailHtmlBody = templateTop+emailHtmlBody+templateBottom;

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

    static String templateTop = "<html>\n" +
            "\t<title>Email template</title>\n" +
            "\t<link href='https://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet'> \n" +
            "\t<style type='text/css'>\n" +
            "\t\tbody{\n" +
            "\t\t\tfont-family: 'Lato', sans-serif;\n" +
            "\t\t\tfont-size:14px;\n" +
            "\t\t}\n" +
            "\t</style>\n" +
            "\t<body>\n" +
            "\t\t<div style='width:600px;margin:10px auto;overflow:hidden;min-height:20px;position:relative;background:#f8f8f8;'>\n" +
            "\t\t\t<table style='width:100%;text-align:center;padding:10px 0px;border-bottom:3px solid #F19329;'>\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th><img src='"+BASEURL+"/resources/img/email-logo.png'></th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t</table>\n" +
            "\t\t\t<div style='overflow:hidden;min-height:150px;padding:20px;background:#f0f0f0;margin:0px;display:block;font-size:15px;color:#424242;'>\n" +
            "\t\t\t\t<p style='margin:0px;line-height:24px;'>";
    static String templateBottom = "</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div style='display:block;text-align:center;padding:25px 15px 25px 15px;'>\n" +
            "\t\t\t\t<a href='"+BASEURL+"' style='padding:10px 20px;background:#F19329;color:#fff;border:0px;text-decoration:none;text-transform:uppercase;font-weight:bold;'>\n" +
            "\t\t\t\t\tClick here to go rentguru24\n" +
            "\t\t\t\t</a>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<table style='width:100%;text-align:center;padding:10px 0px;background:#3490a7;color:#fff;'>\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>Copyright. Rentguru24.com</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t</table>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>" ;
    static String templateSignInBottom = "</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div style='display:block;text-align:center;padding:25px 15px 25px 15px;'>\n" +
            "\t\t\t\t<a href='"+BASEURL+"/signin' style='padding:10px 20px;background:#F19329;color:#fff;border:0px;text-decoration:none;text-transform:uppercase;font-weight:bold;'>\n" +
            "\t\t\t\t\tClick here to sing-in rentguru24\n" +
            "\t\t\t\t</a>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<table style='width:100%;text-align:center;padding:10px 0px;background:#3490a7;color:#fff;'>\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>Copyright. Rentguru24.com</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t</table>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>" ;
}
