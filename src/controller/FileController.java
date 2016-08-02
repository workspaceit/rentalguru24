package controller;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.entity.app.IdentityDoc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by mi on 8/2/16.
 */
@RestController
@RequestMapping("/fileupload")
public class FileController {
    private ServiceResponse serviceResponse;


    @RequestMapping(value = "/upload/document-identity", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadDocumentIdentity(@RequestParam("documentIdentity") MultipartFile file){

        try {
            ImageHelper.saveAsPdf(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setErrorMsg("documentIdentity","No file attached");
        }
        IdentityDoc identityDoc = new IdentityDoc();
        return serviceResponse;
    }

}
