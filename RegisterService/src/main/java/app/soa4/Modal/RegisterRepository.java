package app.soa4.Modal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegisterRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public Boolean checkAccount(String username, String password, String email) {
        try {
            List<Account> check = this.jdbcTemplate.query("SELECT account_username FROM ACCOUNTS WHERE account_username = ? OR account_email = ?", new Object[]{username, email}, new RegisterRowMapper());
            if(check.isEmpty()){
                return true;
            }
            return false;
        }catch (Exception exception) {
            throw new AccountNotFoundException(username, email);
        }
    }

    @Transactional
    public void createAccount(String userName, String password, String email){
        String sql = "INSERT INTO ACCOUNTS(account_email, "
                + "account_username, "
                + "account_password) values (?,?,?)";
        this.jdbcTemplate.update(sql, email, userName, password);
    }
}
