package app.soa4.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationRowMapper implements RowMapper<CreateNotification> {
    @Override
    public CreateNotification mapRow(ResultSet resultSet, int i) throws SQLException {
        CreateNotification createNotification = new CreateNotification();
        createNotification.setAccount_id1(resultSet.getInt("notification_account_id1"));
        createNotification.setAccount_id2(resultSet.getInt("notification_account_id2"));
        createNotification.setRead_status(resultSet.getInt("notification_read_status"));

        return createNotification;
    }
}
