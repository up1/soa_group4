package app.soa4.Modal;
public class Image {
    private long id;
    private String imageType;
    private String imageName;
    private String imagePath;
    private long accountId;

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getImagePath() {

        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {

        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}