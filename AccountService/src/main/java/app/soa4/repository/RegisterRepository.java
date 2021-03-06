/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.soa4.repository;

import java.util.List;

import app.soa4.model.Account;
import app.soa4.exception.AccountNotFoundException;
import app.soa4.util.Hash;
import app.soa4.rowmapper.RegisterRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegisterRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Hash hash;
    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);
    
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
        }catch (Exception e) {
            logger.info("log", e);
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
                + "account_password, "
                + "account_birthday) values (?,?,?,?,929379600000)";
        this.jdbcTemplate.update(sql, uid, email, userName, pwd);
        String sql2 = "INSERT INTO SEARCHING(search_birthday, " +
                "account_id) values (929379600000, (SELECT account_id FROM ACCOUNT WHERE account_username = ?))";
        this.jdbcTemplate.update(sql2, userName);
    }
}