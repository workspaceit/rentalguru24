package model.nonentity.social_media;

/**
 * Created by mi on 9/2/16.
 */
public class FaceBookProfilePicture {
    private Data data;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {
        private int height = 0;
        private boolean is_silhouette = false;
        private String url = "";
        private int width = 0;



        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean is_silhouette() {
            return is_silhouette;
        }

        public void setIs_silhouette(boolean is_silhouette) {
            this.is_silhouette = is_silhouette;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
