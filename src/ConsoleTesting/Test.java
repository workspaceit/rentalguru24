package ConsoleTesting;


import helper.EmailHelper;
import model.entity.app.AppCredential;
import model.entity.app.UserInf;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;

/**
 * Created by mi on 9/21/16.
 */
public class Test {
    public static void main(String args[]){
        AppCredential appCredential = new AppCredential();
        appCredential.setUserInf(new UserInf());
        appCredential.setEmail("rafi101010@gmail.com");
        appCredential.getUserInf().setFirstName("ASD");
        appCredential.getUserInf().setLastName("Last");
        RentalProduct rentalProduct = new RentalProductEntity();
        rentalProduct.setName("TESTINNDFSDFSDF");
        rentalProduct.setId(102);
        rentalProduct.setOwner(appCredential);
        EmailHelper.rentalProductApprovalDisapprovalEmail( rentalProduct, true);
        EmailHelper.rentalProductApprovalDisapprovalEmail( rentalProduct, false);


    }

}



