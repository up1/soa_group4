package app.soa4.repository;

import app.soa4.model.SuperlikeCheck;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ARMY on 4/25/2017.
 */
public class SuperlikeCheckRowMapper implements RowMapper<SuperlikeCheck> {
    @Override
    public SuperlikeCheck mapRow(ResultSet resultSet, int i) throws SQLException {
        SuperlikeCheck superlikeCheck = new SuperlikeCheck();
        superlikeCheck.setCheck_status(resultSet.getInt("matching_status"));
        return superlikeCheck;
    }
}
