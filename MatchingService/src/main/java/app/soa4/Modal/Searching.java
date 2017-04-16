package app.soa4.Modal;

/**
 * Created by ARMY on 4/15/2017.
 */
public class Searching {

    private Integer id;
    private Float lat;
    private Float lon;
    private Integer age;
    private String sex;
    private String sexual_taste;
    private Integer min_age;
    private Integer max_age;
    private Float distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexual_taste() {
        return sexual_taste;
    }

    public void setSexual_taste(String sexual_taste) {
        this.sexual_taste = sexual_taste;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMin_age() {
        return min_age;
    }

    public void setMin_age(Integer min_age) {
        this.min_age = min_age;
    }

    public Integer getMax_age() {
        return max_age;
    }

    public void setMax_age(Integer max_age) {
        this.max_age = max_age;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
