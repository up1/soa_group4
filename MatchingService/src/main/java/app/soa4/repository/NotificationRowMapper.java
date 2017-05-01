package app.soa4.repository;

import app.soa4.model.CreateNotification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ARMY on 4/24/2017.
 */
public class NotificationRowMapper implements RowMapper<CreateNotification> {
    @Override
    public CreateNotification mapRow(ResultSet resultSet, int i) throws SQLException {
        CreateNotification createNotification = new CreateNotification();
        createNotification.setAccount_id1(resultSet.getInt("matching_account_done"));
        createNotification.setAccount_id2(resultSet.getInt("matching_account_do"));
        return createNotification;
    }
}
