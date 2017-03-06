package app.Matching.Modal;
public class Account {
    private long id;
    private String username;
    private int age;
    private String sex;
    private String sexual_taste;
    private float latitude;
    private float longtitude;
    private String location;
    private String img_profile_path;
    private String descriptions;

    public Account(long id, String username, int age, String sex, String sexual_taste, float latitude, float longtitude, String location, String img_profile_path, String descriptions) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.sexual_taste = sexual_taste;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.location = location;
        this.img_profile_path = img_profile_path;
        this.descriptions = descriptions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexual_taste() {
        return sexual_taste;
    }

    public void setSexual_taste(String sexual_taste) {
        this.sexual_taste = sexual_taste;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg_profile_path() {
        return img_profile_path;
    }

    public void setImg_profile_path(String img_profile_path) {
        this.img_profile_path = img_profile_path;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }


    
    
}