package app.soa4.Modal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchingRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public List<Account> checkAccount(String username, String email) {
        try {
            return this.jdbcTemplate.query("SELECT account_username FROM ACCOUNTS WHERE account_username = ? OR account_email = ?", new Object[]{username, email}, new MatchingRowMapper());
        }catch (Exception exception) {
            throw new MatchingNotFoundException(username, email);
        }
    }

    @Transactional
    public void createAccount(String email , String userName, String password, String name, String lastName, int age, String sex, 
            String sexTaste, Double latitude, Double longtitude, String location, String imagePath, String description){
        String sql = "INSERT INTO ACCOUNTS(account_email, "
                + "account_username, "
                + "account_password, "
                + "account_name, "
                + "account_lastname, "
                + "account_age, "
                + "account_sex, "
                + "account_sexual_taste, "
                + "account_latitude, "
                + "account_longtitude, "
                + "account_location, "
                + "account_img_profile_path, "
                + "account_descriptions) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, email, userName, password, name, lastName, age, sex, sexTaste, latitude, longtitude, location, imagePath, description);
    }
}
