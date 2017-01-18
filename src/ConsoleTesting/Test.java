package ConsoleTesting;


import helper.EmailHelper;
import helper.template_engine.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by mi on 9/21/16.
 */

public class Test {


    static Map<String,AdminEmailDetails>  adminEmailDetailsMap = new HashMap<>();
    public static void main(String [] args) {

        AdminEmailDetails registrationEmail = new AdminEmailDetails();
        registrationEmail.subject = "New user registered";
        registrationEmail.emailBody.append("Dear [admin-username],\n");
        registrationEmail.emailBody.append("A new user has registered to the portal.\n");
        registrationEmail.emailBody.append("Basic Information of the user: \n [compete-customer-details]");

        adminEmailDetailsMap.put("registration",registrationEmail);

        AdminEmailDetails newProductUploadEmail = new AdminEmailDetails();
        newProductUploadEmail.subject = "New product added. Need to verify";
        newProductUploadEmail.emailBody.append("Dear [admin-username],\n" );
        newProductUploadEmail.emailBody.append("A new product [product-url] has been uploaded by [customer-email-address].\n");
        newProductUploadEmail.emailBody.append("[complete product and customer details]");
        newProductUploadEmail.emailBody.append("Please review the product as soon as possible.");
        VelocityContext context = new VelocityContext();
        context.put("adminUser", "Mavis");
        context.put("firstName", "Roger");
        context.put("email", "mrRogers@wmail.com");
        context.put("title", "Mr.");
        String s = VelocityUtil.getHtmlByTemplateAndContext("newRegistration.vm", context);
        System.out.println(s);

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
