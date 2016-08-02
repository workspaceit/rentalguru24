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

/**
 * Created by mi on 10/1/15.
 */
public class ImageHelper {
    //server settings for pictures and images
//    private final static String GLOBAL_PATH = "/home/wsit/Projects/j2ee/imagetalk_picture/";
//    private final static String STICKER_GLOBAL_PATH = "/home/wsit/Projects/j2ee/";

//      Local settings for pictures and Images
    private final static String GLOBAL_PATH = "/home/mi/Projects/j2ee/imagetalk_picture/";
    private final static String STICKER_GLOBAL_PATH = "/home/mi/Projects/j2ee/";

    public static String getGlobalPath() {
        return GLOBAL_PATH;
    }
    public static String getStickerGlobalPath() {
        return  STICKER_GLOBAL_PATH;
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
    public static String saveFile(Object imgObj, String path, int uId) {
        if (path == null) {
            //path = "/home/mi/pic/";
            path = "/home/wsit/pic";
        }
        String fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            createDirIfNotExist(path + uId);
            path += uId + "/" + fileName;
            File file = new File(path);

            long startTime = System.nanoTime();
            if (imgObj.getClass().equals(BufferedImage.class)) {
                ImageIO.write((BufferedImage) imgObj, "jpg", file);
            } else if (imgObj.getClass().equals(String.class)) {
                ImageIO.write(decodeToImage((String) imgObj), "jpg", file);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            return fileName;
        }
        return fileName;
    }

    public static Pictures saveProfilePicture(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/profile";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);


            if (imgObj.getClass().equals(BufferedImage.class)) {
                ImageIO.write((BufferedImage) imgObj, "jpg", file);

                PictureDetails thumb1 = new PictureDetails();
                thumb1.type = "thumbnail";
                thumb1.path = createThumbnail((BufferedImage) imgObj, 50, 50, uId + "/profile");
                thumb1.size.width = 50;
                thumb1.size.height = 50;
                pictures.thumb.add(thumb1);

                PictureDetails thumb2 = new PictureDetails();
                thumb2.type = "thumbnail";
                thumb2.path = createThumbnail((BufferedImage) imgObj, 100, 100, uId + "/profile");

                thumb2.size.width = 100;
                thumb2.size.height = 100;

                pictures.thumb.add(thumb2);

            } else if (imgObj.getClass().equals(String.class)) {
                ImageIO.write(decodeToImage((String) imgObj), "jpg", file);

                PictureDetails thumb1 = new PictureDetails();
                thumb1.type = "thumbnail";
                thumb1.path = createThumbnail(decodeToImage((String) imgObj), 50, 50, uId + "/profile");

                thumb1.size.width = 50;
                thumb1.size.height = 50;

                pictures.thumb.add(thumb1);

                PictureDetails thumb2 = new PictureDetails();
                thumb2.type = "thumbnail";
                thumb2.path = createThumbnail(decodeToImage((String) imgObj), 100, 100, uId + "/profile");
                thumb2.size.width = 100;
                thumb2.size.height = 100;
                pictures.thumb.add(thumb2);
            }

            fileName = uId + "/profile/" + fileName;
            pictures.original.size.height = 0;
            pictures.original.size.width = 0;
            pictures.original.type = "original";
            pictures.original.path = fileName;


        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
    }

    public static Pictures saveByteToChatPicture(byte[] b,int uId,String tmpFileName) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        System.out.println("tmpFileName :" + tmpFileName);
        try {
            fileName = +System.nanoTime() + "."+getExtension(tmpFileName);
            path += uId;
            createDirIfNotExist(path);
            path += "/chat";
            createDirIfNotExist(path);
            path += "/media";
            createDirIfNotExist(path);
            path += "/picture";
            createDirIfNotExist(path);
            path += "/regular";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File outputfile = new File(path);




            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(b));
            ImageIO.write(bufferedImage,getExtension(tmpFileName), outputfile);

            fileName = uId + "/chat/media/picture/regular/" + fileName;
            pictures.original.size.height = bufferedImage.getHeight();
            pictures.original.size.width = bufferedImage.getWidth();
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(bufferedImage, 100, 100, uId + "/chat/media/picture/regular",tmpFileName);
            thumb1.size.width = 32;
            thumb1.size.height = 32;
            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(bufferedImage, 200, 200, uId + "/chat/media/picture/regular",tmpFileName);
            thumb2.size.width = 48;
            thumb2.size.height = 48;
            pictures.thumb.add(thumb2);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return pictures;
    }
    public static Pictures saveByteToChatPrivatePicture(byte[] b,int uId,String tmpFileName) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + "."+getExtension(tmpFileName);
            path += uId;
            createDirIfNotExist(path);
            path += "/chat";
            createDirIfNotExist(path);
            path += "/media";
            createDirIfNotExist(path);
            path += "/picture";
            createDirIfNotExist(path);
            path += "/private";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File outputfile = new File(path);




            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(b));
            ImageIO.write(bufferedImage,getExtension(tmpFileName), outputfile);

            fileName = uId + "/chat/media/picture/" + fileName;
            pictures.original.size.height = bufferedImage.getHeight();
            pictures.original.size.width = bufferedImage.getWidth();
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(bufferedImage, 100, 100, uId + "/chat/media/picture/private",tmpFileName);
            thumb1.size.width = 32;
            thumb1.size.height = 32;
            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(bufferedImage, 200, 200, uId + "/chat/media/picture/private",tmpFileName);
            thumb2.size.width = 48;
            thumb2.size.height = 48;
            pictures.thumb.add(thumb2);

            System.out.println("Path  : " + path);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return pictures;
    }
    public static Pictures saveChatPicture(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/chat";
            createDirIfNotExist(path);
            path += "/media";
            createDirIfNotExist(path);
            path += "/picture";
            createDirIfNotExist(path);
            path += "/regular";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);
            BufferedImage img;

            if (imgObj.getClass().equals(BufferedImage.class)) {
                img = (BufferedImage) imgObj;
            } else if (imgObj.getClass().equals(String.class)) {
                img = decodeToImage((String) imgObj);
            }else{
                img = decodeToImage((String) imgObj);
            }

            ImageIO.write(img, "jpg", file);

            fileName = uId + "/chat/media/picture/regular/" + fileName;
            pictures.original.size.height = img.getHeight();
            pictures.original.size.width = img.getWidth();
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(decodeToImage((String) imgObj), 100, 100, uId + "/chat/media/picture/regular");

            thumb1.size.width = 32;
            thumb1.size.height = 32;

            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(decodeToImage((String) imgObj), 200, 200, uId + "/chat/media/picture/regular");
            thumb2.size.width = 48;
            thumb2.size.height = 48;

            pictures.thumb.add(thumb2);

        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
    }
    public static Pictures saveChatPrivatePicture(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/chat";
            createDirIfNotExist(path);
            path += "/media";
            createDirIfNotExist(path);
            path += "/picture";
            createDirIfNotExist(path);
            path += "/private";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);
            BufferedImage img;

            if (imgObj.getClass().equals(BufferedImage.class)) {
                img = (BufferedImage) imgObj;
            } else if (imgObj.getClass().equals(String.class)) {
                img = decodeToImage((String) imgObj);
            }else{
                img = decodeToImage((String) imgObj);
            }

            ImageIO.write(img, "jpg", file);

            fileName = uId + "/chat/media/picture/private/" + fileName;
            pictures.original.size.height = img.getHeight();
            pictures.original.size.width = img.getWidth();
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(decodeToImage((String) imgObj), 100, 100, uId + "/chat/media/picture/private");

            thumb1.size.width = 32;
            thumb1.size.height = 32;

            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(decodeToImage((String) imgObj), 200, 200, uId + "/chat/media/picture/private");
            thumb2.size.width = 48;
            thumb2.size.height = 48;

            pictures.thumb.add(thumb2);

        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
    }
    public static Pictures saveChatLocationPicture(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/chat";
            createDirIfNotExist(path);
            path += "/media";
            createDirIfNotExist(path);
            path += "/location";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);
            BufferedImage img;

            if (imgObj.getClass().equals(BufferedImage.class)) {
                img = (BufferedImage) imgObj;
            } else if (imgObj.getClass().equals(String.class)) {
                img = decodeToImage((String) imgObj);
            }else{
                img = decodeToImage((String) imgObj);
            }

            ImageIO.write(img, "jpg", file);

            fileName = uId + "/chat/media/picture/" + fileName;
            pictures.original.size.height = img.getHeight();
            pictures.original.size.width = img.getWidth();
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(decodeToImage((String) imgObj), 100, 100, uId + "/chat/media/picture");

            thumb1.size.width = 32;
            thumb1.size.height = 32;

            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(decodeToImage((String) imgObj), 200, 200, uId + "/chat/media/picture");
            thumb2.size.width = 48;
            thumb2.size.height = 48;

            pictures.thumb.add(thumb2);

        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
    }
    public static Pictures saveJobIcon(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/job";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);
            BufferedImage img;

            if (imgObj.getClass().equals(BufferedImage.class)) {
                img = (BufferedImage) imgObj;
            } else if (imgObj.getClass().equals(String.class)) {
                img = decodeToImage((String) imgObj);
            }else{
                img = decodeToImage((String) imgObj);
            }

            ImageIO.write(img, "jpg", file);

            fileName = uId + "/job/" + fileName;
            pictures.original.size.height = 0;
            pictures.original.size.width = 0;
            pictures.original.path = fileName;


            PictureDetails thumb1 = new PictureDetails();
            thumb1.type = "thumbnail";
            thumb1.path = createThumbnail(decodeToImage((String) imgObj), 32, 32, uId + "/job");

            thumb1.size.width = 32;
            thumb1.size.height = 32;

            pictures.thumb.add(thumb1);

            PictureDetails thumb2 = new PictureDetails();
            thumb2.type = "thumbnail";
            thumb2.path = createThumbnail(decodeToImage((String) imgObj), 48, 48, uId + "/job");
            thumb2.size.width = 48;
            thumb2.size.height = 48;

            pictures.thumb.add(thumb2);

        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
    }
    public static Pictures saveWallPostPicture(Object imgObj, int uId) {
        Pictures pictures = new Pictures();
        String   path     = GLOBAL_PATH;
        String   fileName = "";
        try {
            fileName = +System.nanoTime() + ".jpg";
            path += uId;
            createDirIfNotExist(path);
            path += "/wallpost";
            createDirIfNotExist(path);
            path += "/" + fileName;
            System.out.println(path);
            File file = new File(path);


            if (imgObj.getClass().equals(BufferedImage.class)) {
                ImageIO.write((BufferedImage) imgObj, "jpg", file);

                //                PictureDetails thumb1 = new  PictureDetails();
                //                thumb1.type = "thumbnail";
                //                thumb1.path = createThumbnail((BufferedImage)imgObj, 100, 50,uId+"/wallpost");
                //                pictures.thumb.add(thumb1);
            } else if (imgObj.getClass().equals(String.class)) {
                ImageIO.write(decodeToImage((String) imgObj), "jpg", file);

                //                PictureDetails thumb1 = new  PictureDetails();
                //                thumb1.type = "thumbnail";
                //                thumb1.path = createThumbnail(decodeToImage((String) imgObj), 100, 50,uId+"/wallpost");
                //                pictures.thumb.add(thumb1);
            }

            fileName = uId + "/wallpost/" + fileName;
            pictures.original.size.height = 0;
            pictures.original.size.width = 0;
            pictures.original.path = fileName;


        } catch (Exception ex) {
            System.out.println(ex);
            return pictures;
        }
        return pictures;
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
    public static void saveAsPdf(byte[] pdfByte) {
        try {
            File someFile = new File(GLOBAL_PATH+ "/" +System.nanoTime() + ".pdf");
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void saveAsPdf(String base64Str) {
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


