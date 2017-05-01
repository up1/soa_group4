/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.soa4.Modal;

import java.util.List;
import app.soa4.Modal.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegisterRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Hash hash;
    
    @Autowired
    public RegisterRepository(){
        this.hash = new Hash();
    }
    
    @Transactional(readOnly = true)
    public Boolean checkAccount(String username, String password, String email) {
        try {
            List<Account> check = this.jdbcTemplate.query("SELECT account_username FROM ACCOUNT WHERE account_username = ? OR account_email = ?", new Object[]{username, email}, new RegisterRowMapper());
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
        String uid = this.hash.getSha256("renniT"+userName);
        String pwd = this.hash.getSha256(password);
        String sql = "INSERT INTO ACCOUNT(account_uid, "
                + "account_email, "
                + "account_username, "
                + "account_password) values (?,?,?,?)";
        this.jdbcTemplate.update(sql, uid, email, userName, pwd);
    }
}