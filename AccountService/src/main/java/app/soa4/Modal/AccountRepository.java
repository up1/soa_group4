package app.soa4.Modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static java.lang.Math.floor;

@Repository
public class AccountRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public Account accountProfile(long account_id){
        String sql = "SELECT account_uid, account_id, account_email, " +
                "account_username, account_name,account_lastname, " +
                "account_birthday, account_sex, account_sexual_taste, account_latitude, " +
                "account_longtitude, account_location, account_descriptions FROM ACCOUNT WHERE account_id = ?";
        String sql2 = "SELECT account_birthday FROM ACCOUNT WHERE account_id = ?";
        String sql3 = "SELECT search_sex,search_sexual_taste, search_min_age, search_max_age, search_distance  from  SEARCHING WHERE account_id = ?";
        Birthday birthday = (Birthday)this.jdbcTemplate.queryForObject(sql2, new Object[] {account_id} , new BeanPropertyRowMapper(Birthday.class));
        long ageInMillis = new Date().getTime() - birthday.getAccount_birthday();
        String ageInString = (((ageInMillis / (24 * 60 * 60 * 1000) ) /365))+"";
        Integer age = Integer.parseInt(ageInString);
        Account account = (Account)this.jdbcTemplate.queryForObject(sql, new Object[] { account_id }, new BeanPropertyRowMapper(Account.class));
        account.setAge(age);
        Searching searching = (Searching)this.jdbcTemplate.queryForObject(sql3, new Object[] { account_id }, new BeanPropertyRowMapper(Searching.class));
        account.setSearch_distance(searching.getSearch_distance());
        account.setSearch_max_age(searching.getSearch_max_age());
        account.setSearch_min_age(searching.getSearch_min_age());
        account.setSearch_sex(searching.getSearch_sex());
        account.setSearch_sexual_taste(searching.getSearch_sexual_taste());
        return account;
    }

    @Transactional
    public String editPassword(String password, long id){
        try {
            String sql = "UPDATE ACCOUNT SET account_password = ? WHERE account_id = ?";
            this.jdbcTemplate.update(sql, password, id);
            return "Edit complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot Edit.";
        }
    }

    @Transactional
    public String editProfile(String email,String name,String lastname,long birthday,String sex,String sextaste,float lat,float lon,String location,String des,long id,String search_sex,String search_sextaste,int min_age,int max_age,float distance){
        try {
            String sql = "UPDATE ACCOUNT SET account_email = ?,account_name = ?,account_lastname = ?,account_age = ?, account_birthday = ?, account_sexual_taste = ?,account_latitude = ?,account_longtitude = ?,account_location = ?,account_descriptions = ? WHERE account_id = ?";
            String sql2 = "UPDATE SEARCHING SET search_latitude = ?,search_longtitude = ?,search_birthday = ?,search_sex = ?,search_sexual_taste = ?,search_min_age = ?,search_max_age = ?,search_distance = ? WHERE account_id = ?";
            this.jdbcTemplate.update(sql,email,name,lastname,birthday,sex,sextaste,lat,lon,location,des,id);
            this.jdbcTemplate.update(sql2,lat,lon,birthday,search_sex,search_sextaste,min_age,max_age,distance,id);
            return "Edit complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot Edit.";
        }
    }
}
