package app.soa4.Modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public Account accountProfile(long account_id){
        String sql = "SELECT * FROM ACCOUNT WHERE account_id = ?";
        Account account = (Account)this.jdbcTemplate.queryForObject(sql, new Object[] { account_id }, new BeanPropertyRowMapper(Account.class));
        return account;
    }

    public AccountToMatching accountToMatching(long account_id){
        String sql = "SELECT account_username, account_name, account_lastname, account_age, account_sexual_taste, account_location, account_descriptions FROM ACCOUNT WHERE account_id = ?";
        AccountToMatching accountToMatching = (AccountToMatching)this.jdbcTemplate.queryForObject(sql, new Object[] { account_id }, new BeanPropertyRowMapper(AccountToMatching.class));
        return accountToMatching;
    }

    @Transactional
    public String editProfile(String email,String password,String name,String lastname,int age,String sex,String sextaste,String location,String des,  long id){
        try {
            String sql = "UPDATE ACCOUNT SET account_email = ?,account_password = ?,account_name = ?,account_lastname = ?,account_age = ?, account_sex = ?, account_sexual_taste = ?, account_location = ?,account_descriptions = ? WHERE account_id = ?";
            this.jdbcTemplate.update(sql,email,password,name,lastname,age,sex,sextaste,location,des, id);
            return "Edit complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot Edit.";
        }
    }
}
