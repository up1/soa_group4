package app.soa4.Modal;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchingRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account matching = new Account();
        matching.setUsername(resultSet.getString("account_username"));
        return matching;
    }
}
