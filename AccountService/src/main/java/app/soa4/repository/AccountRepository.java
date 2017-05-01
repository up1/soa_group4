package app.soa4.repository;

import app.soa4.model.Account;
import app.soa4.model.Birthday;
import app.soa4.model.Editdata;
import app.soa4.model.Searching;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

import org.springframework.web.client.RestTemplate;
@Repository
public class AccountRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

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
            logger.info("log", e);
            return "Cannot Edit.";
        }
    }

    @Transactional
    public String editProfile(Editdata editdata){
        try {
            Map<String,Object> maps = restTemplate.getForObject("http://maps.googleapis.com/maps/api/geocode/json?latlng=13.179440,100.79361",Map.class);
            JSONObject json = new JSONObject(maps);
            String location = (json.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(2).getString("long_name")+", "+json.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(3).getString("long_name"));
            String sql = "UPDATE ACCOUNT SET account_email = ?,account_name = ?,account_lastname = ?, account_birthday = ?, account_sex = ?, account_sexual_taste = ?, account_location = ?, account_latitude = ?,account_longtitude = ?,account_descriptions = ? WHERE account_id = ?";
            String sql2 = "UPDATE SEARCHING SET search_latitude = ?,search_longtitude = ?,search_birthday = ?,search_sex = ?,search_sexual_taste = ?,search_min_age = ?,search_max_age = ?,search_distance = ? WHERE account_id = ?";
            this.jdbcTemplate.update(sql,editdata.getAccount_email(),editdata.getAccount_name(),editdata.getAccount_lastname(),editdata.getAccount_birthday(),editdata.getAccount_sex(),editdata.getAccount_sexual_taste(), location,editdata.getAccount_latitude(),editdata.getAccount_longtitude(),editdata.getAccount_descriptions(),editdata.getAccount_id());
            this.jdbcTemplate.update(sql2,editdata.getAccount_latitude(),editdata.getAccount_longtitude(),editdata.getAccount_birthday(),editdata.getSearch_sex(),editdata.getSearch_sexual_taste(), editdata.getSearch_min_age(),editdata.getSearch_max_age(), editdata.getSearch_distance(),editdata.getAccount_id());
            return "Edit complete";
        }catch (Exception e){
            logger.info("log", e);
            return "Cannot Edit.";
        }
    }
}
