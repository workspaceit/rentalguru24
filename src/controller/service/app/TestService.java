package controller.service.app;


import helper.EmailHelper;
import helper.template_engine.VelocityUtil;
import controller.service.BaseService;
import library.RentGuruMail;
import model.*;

import model.entity.app.*;
import model.entity.app.payments.RentPayment;
import model.entity.app.product.rentable.RentInf;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

/**
 * Created by omar on 8/1/16.
 */
@RestController
@RequestMapping("/api/app")
@Scope("request")
public class TestService extends BaseService{
    @Autowired
    AttributesModel attributesModel;

    @Autowired
    CategoryModel categoryModel;

    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    IdentityTypeModel identityTypeModel;

    @Autowired
    RentRequestModel rentRequestModel;

    @Autowired
    RentPaymentModel rentPaymentModel;

    @Autowired
    RentInfModel rentInfModel;

    @Autowired
    RentGuruMail rentGuruMail;

    @Autowired
    ProductModel productModel;

    @Autowired
    AdminGlobalNotificationModel adminGlobalNotificationModel;

    @Autowired
    AdminGlobalNotificationTemplateModel adminGlobalNotificationTemplateModel;

    @Autowired
    AppLoginCredentialModel appLoginCredentialModel;


    @Autowired
    EmailHelper emailHelper;
    @RequestMapping(value = "/test/category", method = RequestMethod.POST)
    public void postCategory(){


        RentPayment rentPayment = rentPaymentModel.getByRentRequestId(148);
        emailHelper.sendAdminProductReceiveEmail(rentPayment, true);
        emailHelper.sendAdminProductReceiveEmail(rentPayment, false);
        emailHelper.sendAdminRentRequestEmail(rentPayment,"approve");
        emailHelper.sendAdminProductReturnRequestEmail(rentPayment);
        emailHelper.sendAdminProductReceiveEmail(rentPayment,true);

    }

    @RequestMapping(value = "/test/email", method = RequestMethod.POST)
    public AppCredential testEmail(){
        AppCredential registeredUser = appLoginCredentialModel.getAppCredentialById(80);
        return  registeredUser;

    }
}
