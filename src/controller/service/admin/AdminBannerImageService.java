package controller.service.admin;

import helper.ImageHelper;
import helper.ServiceResponse;
import model.BannerImageModel;
import model.TempFileModel;
import model.entity.BannerImage;
import model.entity.app.AppCredential;
import model.entity.app.TempFile;
import model.nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by omar on 10/25/16.
 */
@RestController
@RequestMapping("/admin-signin/banner-image")
public class AdminBannerImageService {
    @Autowired
    TempFileModel tempFileModel;

    @Autowired
    BannerImageModel bannerImageModel;

    @RequestMapping(value = "/add-image", method = RequestMethod.POST)
    public ServiceResponse adminBannerImageAdd(HttpServletRequest request,
                                               @RequestParam Map<String, String> allRequestParams){
        ServiceResponse serviceResponse =(ServiceResponse) request.getAttribute("serviceResponse");

        if(allRequestParams.get("url") == null || allRequestParams.get("url").isEmpty()){
            serviceResponse.setRequestError("url", "url required");
        }

        if(allRequestParams.get("bannerImageToken") == null || allRequestParams.get("bannerImageToken").isEmpty()){
            serviceResponse.setRequestError("bannerImageToken", "Banner image required");
        }

        if(serviceResponse.hasErrors()){
            return serviceResponse;
        }

        String url = allRequestParams.get("url");
        Long bannerImageToken = Long.parseLong(allRequestParams.get("bannerImageToken"));


        BannerImage bannerImage = new BannerImage();
        bannerImage.setUrl(url);

        if(bannerImageToken!=null){
            Picture picture = null;
            TempFile tempOtherFile = this.tempFileModel.getByToken(bannerImageToken);
            try {
                picture = ImageHelper.moveBannerImage( tempOtherFile.getPath());

                if(picture.getOriginal().getPath().isEmpty()) {
                    serviceResponse.setRequestError("bannerImageToken", "Unable to save profile image");
                }
                bannerImage.setImagePath(picture.getOriginal().getPath());

            } catch (Exception e) {
                //e.printStackTrace();
                serviceResponse.setRequestError("bannerImageToken", "Unable to save image.."+e.getMessage());
                return serviceResponse;
            }
        }

        bannerImageModel.insert(bannerImage);
        serviceResponse.getResponseStat().setMsg("Banner image upload success");
        return serviceResponse;
    }
}
