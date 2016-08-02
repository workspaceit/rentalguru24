package controller;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.entity.app.IdentityDoc;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * Created by mi on 8/2/16.
 */
@RestController
@RequestMapping("/fileupload")
@Scope("request")
public class FileController {
    private ServiceResponse serviceResponse;

    public FileController( ) {
        this.serviceResponse = new ServiceResponse();
    }

    @RequestMapping(value = "/upload/document-identity", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadDocumentIdentity(@RequestParam("documentIdentity") MultipartFile file){

        try {
            ImageHelper.saveAsPdf(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setErrorMsg("documentIdentity","No file attached");
        }
        IdentityDoc identityDoc = new IdentityDoc();
        identityDoc.setId(0);
        identityDoc.setPath("PATH");

        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        System.out.println(n);
        identityDoc.setToken(10164687);
        identityDoc.setCreatedDate(null);
       // serviceResponse.setResponseData(identityDoc);
        return serviceResponse;
    }

}
