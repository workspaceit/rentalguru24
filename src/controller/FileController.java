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

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mi on 8/2/16.
 */
@RestController
@RequestMapping("/fileupload/upload")
public class FileController {
    private List<String> documentContentTypeList;
    private List<String> productImgContentTypeList;
    private List<String> profileImgContentTypeList;
    private List<String> categoryImgContentTypeList;
    @Autowired
    TempFileModel tempFileModel;
    public FileController(){
        documentContentTypeList = new ArrayList<String>(){
            {
                add("application/pdf");
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };
        productImgContentTypeList = new ArrayList<String>(){
            {
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };
        profileImgContentTypeList = new ArrayList<String>(){
            {
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };
        categoryImgContentTypeList = new ArrayList<String>(){
            {
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };
    }
    @RequestMapping(value = "/document-identity", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadDocumentIdentity(HttpServletRequest request,@RequestParam("documentIdentity") MultipartFile file){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();
        /*---------Content type validation -----------------*/
        String mimeType = file.getContentType();
        if(mimeType==null || mimeType.equals("")){
            mimeType =  URLConnection.guessContentTypeFromName(file.getOriginalFilename());
            System.out.println("File Inf 02 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }
        if(mimeType==null || mimeType.equals("")){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            mimeType = fileNameMap.getContentTypeFor(file.getOriginalFilename());
            System.out.println("File Inf 03 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }


        if(!documentContentTypeList.contains(mimeType)){
            serviceResponse.setRequestError("productImage"," Mime Type "+ mimeType+" not allowed");
            return serviceResponse;
        }


        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                serviceResponse.setRequestError("documentIdentity", "No file attached");
                return serviceResponse;
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
        serviceResponse.getResponseStat().setMsg( file.getOriginalFilename()+" has been uploaded");
        serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }
    @RequestMapping(value = "/product-image", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadProductImage(HttpServletRequest request,@RequestParam("productImage") MultipartFile file){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();



        /*---------Content type validation -----------------*/

        String mimeType = file.getContentType();
        if(mimeType==null || mimeType.equals("")){
            mimeType =  URLConnection.guessContentTypeFromName(file.getOriginalFilename());
            System.out.println("File Inf 02 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }
        if(mimeType==null || mimeType.equals("")){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            mimeType = fileNameMap.getContentTypeFor(file.getOriginalFilename());
            System.out.println("File Inf 03 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }


        if(!productImgContentTypeList.contains(mimeType)){
            serviceResponse.setRequestError("productImage"," Mime Type "+ mimeType+" not allowed");
            return serviceResponse;
        }






        long fileSizeLimit = 2 *1024 *1024; // 2 MB

        if(file.getSize() > fileSizeLimit){
            serviceResponse.setRequestError("productImage", "Max file size 2 MB");
            return serviceResponse;
        }
        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                serviceResponse.setRequestError("productImage", "No file attached");
                return serviceResponse;
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
        serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }
    @RequestMapping(value = "/auth/user/profile-image", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadProfileImage(HttpServletRequest request,@RequestParam("profileImage") MultipartFile file){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();


        /*---------Only Doc type validation -----------------*/
        String mimeType = file.getContentType();
        if(mimeType==null || mimeType.equals("")){
            mimeType =  URLConnection.guessContentTypeFromName(file.getOriginalFilename());
            System.out.println("File Inf 02 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }
        if(mimeType==null || mimeType.equals("")){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            mimeType = fileNameMap.getContentTypeFor(file.getOriginalFilename());
            System.out.println("File Inf 03 " + file.getOriginalFilename() + " " + file.getSize() + " " + mimeType);
        }


        if(!profileImgContentTypeList.contains(mimeType)){
            serviceResponse.setRequestError("productImage"," Mime Type "+ mimeType+" not allowed");
            return serviceResponse;
        }

        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                serviceResponse.setRequestError("profileImage", "No file attached");
                return serviceResponse;
            }
            String filePath = ImageHelper.saveFile(fileByte, file.getOriginalFilename());
            tempFile.setPath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setRequestError("profileImage", "No file attached");
        }


        Random rnd = new Random();
        long n = 1000000000 + rnd.nextInt(900000);

        tempFile.setToken(n);


        this.tempFileModel.insert(tempFile);
        serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }
    @RequestMapping(value = "/category-image", headers = "Content-Type=multipart/form-data",method = RequestMethod.POST)
    public ServiceResponse uploadCategoryImage(HttpServletRequest request,@RequestParam("categoryImage") MultipartFile file){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");
        model.entity.app.TempFile tempFile = new model.entity.app.TempFile();



        /*---------Content type validation -----------------*/
        String mimeType = file.getContentType();
        if(mimeType==null || mimeType.equals("")){
            mimeType =  URLConnection.guessContentTypeFromName(file.getOriginalFilename());
            System.out.println("File Inf 02 " +file.getOriginalFilename()+" "+file.getSize()+" "+mimeType);
        }
        if(mimeType==null || mimeType.equals("")){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            mimeType = fileNameMap.getContentTypeFor(file.getOriginalFilename());
            System.out.println("File Inf 03 " + file.getOriginalFilename() + " " + file.getSize() + " " + mimeType);
        }


        if(!categoryImgContentTypeList.contains(mimeType)){
            serviceResponse.setRequestError("productImage"," Mime Type "+ mimeType+" not allowed");
            return serviceResponse;
        }



        long fileSizeLimit = 2 *1024 *1024; // 2 MB
        if(file.getSize() > fileSizeLimit){
            serviceResponse.setRequestError("categoryImage", "Max file size 2 MB");
            return serviceResponse;
        }
        try {
            byte[] fileByte = file.getBytes();
            System.out.println("Byte Received " +fileByte.length);
            if(fileByte.length==0){
                serviceResponse.setRequestError("categoryImage", "No file attached");
                return serviceResponse;
            }
            String filePath = ImageHelper.saveFile(fileByte, file.getOriginalFilename());
            tempFile.setPath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            serviceResponse.setRequestError("categoryImage", "No file attached");
        }


        Random rnd = new Random();
        long n = 1000000000 + rnd.nextInt(900000);

        tempFile.setToken(n);


        this.tempFileModel.insert(tempFile);
        serviceResponse.setResponseData(tempFile.getToken());
        return serviceResponse;
    }

}
