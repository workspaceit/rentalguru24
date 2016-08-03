package helper;

import model.nonentity.photo.PictureDetails;
import model.nonentity.photo.Pictures;
import model.nonentity.photo.PictureSize;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Created by mi on 10/1/15.
 */
public class ImageHelper {
    //server settings for pictures and images
//    private final static String GLOBAL_PATH = "/home/wsit/Projects/j2ee/imagetalk_picture/";
//    private final static String STICKER_GLOBAL_PATH = "/home/wsit/Projects/j2ee/";

//      Local settings for pictures and Images
    private static String GLOBAL_PATH= "/home/mi/Projects/j2ee/rentguru24files/";
    private static String DOC_FOLDER= "identityDoc/";
    private static String DOC_PATH= GLOBAL_PATH+DOC_FOLDER;
    private static String TEMP_FOLDER= "temp/";
    private static String TEMP_FILE_PATH= GLOBAL_PATH+TEMP_FOLDER;



    public static void setGlobalPath(String globalPath) {
        GLOBAL_PATH = globalPath;
    }

    public static String getGlobalPath() {
        return GLOBAL_PATH;
    }
    public static boolean isFileExist(String path){
        File docFile =new File(GLOBAL_PATH+path);
        return docFile.exists();
    }
    public static String moveFile(int appCredential,String oldPath){
        String fileName = appCredential+"/"+System.nanoTime()+"."+getExtension(oldPath);
        String filePath = DOC_PATH+fileName;
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(DOC_PATH + appCredential);

            if(docFile.renameTo(new File(filePath))){
                System.out.println("File is moved successful!");
            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
        }
        return DOC_FOLDER+fileName;
    }

    public static void createDirIfNotExist(String path) {
        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
                System.out.println("DIR created");
            } catch (SecurityException se) {
                //handle it
            }
        }
    }
    public static byte[] serialize(Object object)throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(object);
        System.out.println("Completed Serializing");
        return b.toByteArray();
    }


    public static BufferedImage decodeToImage(String imageString) {
        BufferedImage image = null;
        byte[]        imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public static String saveFile(byte[] pdfByte, String originalFileName) {
        String fileName = getRandomNumber() + "."+getExtension(originalFileName);
        try {
            File someFile = new File(TEMP_FILE_PATH+ "/" +fileName);
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TEMP_FOLDER+fileName;
    }
    public static void saveFile(String base64Str) {
        BufferedImage image = null;
        byte[]        pdfByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            pdfByte = decoder.decodeBuffer(base64Str);


            File someFile = new File(GLOBAL_PATH);
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String getRandomNumber(){
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(900000);
        return Integer.toString(n);
    }
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static String createThumbnail(BufferedImage img, int width, int height, String path) {


        Image         scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(scaledImg, 0, 0, null);

        String fileName = System.nanoTime() + ".jpg";

        path += "/thumbnail";
        createDirIfNotExist(GLOBAL_PATH + path);
        path += "/" + fileName;

        try {
            ImageIO.write(thumbnail, "jpg", new File(GLOBAL_PATH + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    public static String createThumbnail(BufferedImage img, int width, int height, String path,String tmpFileName) {


        Image         scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(scaledImg, 0, 0, null);

        String fileName = System.nanoTime() + "."+getExtension(tmpFileName);

        path += "/thumbnail";
        createDirIfNotExist(GLOBAL_PATH + path);
        path += "/" + fileName;

        try {
            ImageIO.write(thumbnail, getExtension(tmpFileName), new File(GLOBAL_PATH + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getExtension(String fileName){
        String extension ="";
        String name = fileName;
        try {
            extension =  name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
        System.out.println("Extension "+extension);
        return extension;
    }

}


