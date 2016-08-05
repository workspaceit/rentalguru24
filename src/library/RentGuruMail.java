package library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ws.mime.MimeMessage;

import java.util.HashMap;

/**
 * Created by mi on 8/5/16.
 */

import org.springframework.ui.velocity.*;
@Service("mailService")
public class RentGuruMail  {

    @Autowired
    private MailSender mailSender;

    public MailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSignUpMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rafi101010@gmail.com");
        message.setSubject("TESTING");
        message.setText("BOADY");
        mailSender.send(message);
    }

}
