package app.soa4.repository;

import app.soa4.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSJIN on 17/4/2560.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("account_id"));
        user.setUsername(resultSet.getString("account_username"));
        user.setUid(resultSet.getString("account_uid"));
        return user;
    }
}
