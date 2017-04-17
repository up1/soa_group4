package app.soa4.Modal;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ARMY on 4/16/2017.
 */
public class SearchingRowMapper implements RowMapper<Searching>{
    @Override
    public Searching mapRow(ResultSet resultSet, int i) throws SQLException {
        Searching searching = new Searching();
        searching.setId(resultSet.getInt("search_id"));
        searching.setLat(resultSet.getDouble("search_latitude"));
        searching.setLon(resultSet.getDouble("search_longtitude"));
        searching.setAge(resultSet.getInt("search_age"));
        searching.setSex(resultSet.getString("search_sex"));
        searching.setSexual_taste(resultSet.getString("search_sexual_taste"));
        searching.setMin_age(resultSet.getInt("search_min_age"));
        searching.setMax_age(resultSet.getInt("search_max_age"));
        searching.setDistance(resultSet.getDouble("search_distance"));
        return searching;
    }
}
