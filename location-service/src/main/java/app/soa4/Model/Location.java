package app.soa4.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Boeing on 4/3/2017.
 */
@Entity
public class Location {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private float latitude;

    @Column
    private float longitude;

    @Column
    private String username;

    public Location() {
    }

    public Location(String name, float latitude, float longitude, String username) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
