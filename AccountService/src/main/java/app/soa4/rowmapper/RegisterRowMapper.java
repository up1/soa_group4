package app.soa4.rowmapper;

import app.soa4.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account matching = new Account();
        matching.setAccount_username(resultSet.getString("account_username"));
        return matching;
    }
}
