package model.nonentity.photo;

/**
 * Created by mi on 10/7/15.
 */
public class PictureDetails {

    public String path;
    public String type;
    public PictureSize size;

    public PictureDetails() {
        this.path = "";
        this.type = "";
        this.size = new PictureSize();
    }
}
