package app.soa4.Modal;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchingRowMapper implements RowMapper<Matching> {
    @Override
    public Matching mapRow(ResultSet resultSet, int i) throws SQLException {
        Matching matching = new Matching();
        matching.setUsername(resultSet.getString("account_username"));
        matching.setName(resultSet.getString("account_name"));
        matching.setLastname(resultSet.getString("account_lastname"));
        matching.setAge(resultSet.getInt("account_age"));
        matching.setLocationName(resultSet.getString("account_location"));
        matching.setDistance(0);
        matching.setDescription(resultSet.getString("account_descriptions"));
        matching.setSexTaste(resultSet.getString("account_sexual_taste"));
        return matching;
    }
}
