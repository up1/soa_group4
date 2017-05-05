package app.soa4.model;
import java.util.ArrayList;

public class Account {
    private String account_uid;
    private long account_id;
    private String account_email;
    private String account_username;
    private String account_name;
    private String account_lastname;
    private long account_birthday;
    private String account_sex;
    private String account_sexual_taste;
    private double account_latitude;
    private double account_longtitude;
    private String account_location;
    private String account_descriptions;
    private ArrayList<String> account_imgsprofile;
    private int age;
    private String search_sex;
    private String search_sexual_taste;
    private int search_min_age;
    private int search_max_age;
    private int search_distance;

    public String getSearch_sex() {
        return search_sex;
    }

    public void setSearch_sex(String search_sex) {
        this.search_sex = search_sex;
    }

    public String getSearch_sexual_taste() {
        return search_sexual_taste;
    }

    public void setSearch_sexual_taste(String search_sexual_taste) {
        this.search_sexual_taste = search_sexual_taste;
    }

    public int getSearch_min_age() {
        return search_min_age;
    }

    public void setSearch_min_age(int search_min_age) {
        this.search_min_age = search_min_age;
    }

    public int getSearch_max_age() {
        return search_max_age;
    }

    public void setSearch_max_age(int search_max_age) {
        this.search_max_age = search_max_age;
    }

    public int getSearch_distance() {
        return search_distance;
    }

    public void setSearch_distance(int search_distance) {
        this.search_distance = search_distance;
    }

    public ArrayList<String> getAccount_imgsprofile() {
        return account_imgsprofile;
    }

    public void setAccount_imgsprofile(ArrayList<String> account_imgsprofile) {
        this.account_imgsprofile = account_imgsprofile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAccount_uid() {
        return account_uid;
    }

    public void setAccount_uid(String account_uid) {
        this.account_uid = account_uid;
    }
    
    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_username() {
        return account_username;
    }

    public void setAccount_username(String account_username) {
        this.account_username = account_username;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_lastname() {
        return account_lastname;
    }

    public void setAccount_lastname(String account_lastname) {
        this.account_lastname = account_lastname;
    }

    public long getAccount_birthday() {
        return account_birthday;
    }

    public void setAccount_birthday(long account_birthday) {
        this.account_birthday = account_birthday;
    }
    public String getAccount_sex() {
        return account_sex;
    }

    public void setAccount_sex(String account_sex) {
        this.account_sex = account_sex;
    }

    public String getAccount_sexual_taste() {
        return account_sexual_taste;
    }

    public void setAccount_sexual_taste(String account_sextual_taste) {
        this.account_sexual_taste = account_sextual_taste;
    }

    public double getAccount_latitude() {
        return account_latitude;
    }

    public void setAccount_latitude(double account_latitude) {
        this.account_latitude = account_latitude;
    }

    public double getAccount_longtitude() {
        return account_longtitude;
    }

    public void setAccount_longtitude(double account_longtitude) {
        this.account_longtitude = account_longtitude;
    }

    public String getAccount_location() {
        return account_location;
    }

    public void setAccount_location(String account_location) {
        this.account_location = account_location;
    }


    public String getAccount_descriptions() {
        return account_descriptions;
    }

    public void setAccount_descriptions(String account_descriptions) {
        this.account_descriptions = account_descriptions;
    }
}