package app.soa4.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchingRowMapper implements RowMapper<Matching> {
    @Override
    public Matching mapRow(ResultSet resultSet, int i) throws SQLException {
        Matching matching = new Matching();
        matching.setId(resultSet.getInt("account_id"));
        matching.setUsername(resultSet.getString("account_username"));
        matching.setName(resultSet.getString("account_name"));
        matching.setLastname(resultSet.getString("account_lastname"));
        matching.setAge(resultSet.getLong("account_age"));
        matching.setLocationName(resultSet.getString("account_location"));
        matching.setDistance(resultSet.getDouble("account_distance"));
        matching.setDescription(resultSet.getString("account_descriptions"));
        matching.setSexTaste(resultSet.getString("account_sexual_taste"));
        return matching;
    }
}
