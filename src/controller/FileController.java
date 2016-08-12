package controller;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.TempFileModel;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/fileupload/upload")
@Scope("request")
public class FileController {
    private ServiceResponse serviceResponse;
    @Autowired
    TempFileModel tempFileModel;
    public FileController( ) {
        System.out.println("File Controller");
        this.serviceResponse = new ServiceResponse();
    }

    @RequestMapping(value = "/document-identity", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadDocumentIdentity(@RequestParam("documentIdentity") MultipartFile file){
        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();


        /*---------Only Doc type validation -----------------*/

        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                this.serviceResponse.setRequestError("documentIdentity", "No file attached");
                return this.serviceResponse;
            }
            String filePath = ImageHelper.saveFile(fileByte, file.getOriginalFilename());
            tempFile.setPath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setRequestError("documentIdentity", "No file attached");
        }


        Random rnd = new Random();
        long n = 1000000000 + rnd.nextInt(900000);

        tempFile.setToken(n);


        this.tempFileModel.insert(tempFile);
        this.serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }
    @RequestMapping(value = "/product-image", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadProductImage(@RequestParam("productImage") MultipartFile file){
        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();


        /*---------Only Doc type validation -----------------*/
        System.out.println("File Size"+file.getSize());



        long fileSizeLimit = 2 *1024 *1024; // 2 MB
        if(file.getSize() > fileSizeLimit){
            serviceResponse.setRequestError("productImage", "Max file size 2 MB");
            return this.serviceResponse;
        }
        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                this.serviceResponse.setRequestError("productImage", "No file attached");
                return this.serviceResponse;
            }
            String filePath = ImageHelper.saveFile(fileByte, file.getOriginalFilename());
            tempFile.setPath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setRequestError("productImage", "No file attached");
        }


        Random rnd = new Random();
        long n = 1000000000 + rnd.nextInt(900000);

        tempFile.setToken(n);


        this.tempFileModel.insert(tempFile);
        this.serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }

}
