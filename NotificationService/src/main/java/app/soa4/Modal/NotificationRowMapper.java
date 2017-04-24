package app.soa4.Modal;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationRowMapper implements RowMapper<Notification> {
    @Override
    public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
        Notification notification = new Notification();
        notification.setNoti_id(resultSet.getInt("Noti_id"));
        notification.setAccountId_1(resultSet.getInt("accountId_1"));
        notification.setAccountId_2(resultSet.getInt("accountId_2"));
        notification.setChat_id(resultSet.getInt("chat_id"));

        return notification;
    }
}
