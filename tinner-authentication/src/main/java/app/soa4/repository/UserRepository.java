package app.soa4.repository;

import app.soa4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }
    @Transactional(readOnly = true)
    public User accountInformation(String username){
        User user = null;
        String sql = "SELECT account_username, account_id, account_uid FROM ACCOUNT WHERE account_username = ?";
        try {
            user = this.jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user;
    }
}
