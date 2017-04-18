package app.soa4.Modal;

import java.util.ArrayList;

public class Matching {
    private Integer id;
    private String username;
    private String name;
    private String lastname;
    private String locationName;
    private Long age;
    private Double distance;
    private String description;
    private String sexTaste;
    private ArrayList<String> imgProfile;

    public ArrayList<String> getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(ArrayList<String> imgProfile) {
        this.imgProfile = imgProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSexTaste() {
        return sexTaste;
    }

    public void setSexTaste(String sexTaste) {
        this.sexTaste = sexTaste;
    }
}
