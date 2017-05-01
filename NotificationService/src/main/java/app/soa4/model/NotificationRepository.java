package app.soa4.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Repository
public class NotificationRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    

    @Transactional
    public void makeNewMatchingNotification(int account_id1 , int account_id2){
        String sql = "INSERT INTO NOTIFICATION(notification_account_id1, notification_account_id2, notification_read_status) values (?,?,?)";
        this.jdbcTemplate.update(sql, account_id1, account_id2, 1);
        this.jdbcTemplate.update(sql, account_id2, account_id1, 1);
    }

    @Transactional
    public void chatNotifiying(int sender_id, int receiver_id, double time, int status){
        String sql = "INSERT INTO CHATNOTIFICATION(sender_id, receiver_id, time, status) values (?,?,?,?)";
        this.jdbcTemplate.update(sql, sender_id, receiver_id, time, status);
    }

    @Transactional
    public String updateChatNotificationDB(int sender_id, int receiver_id, double time, int status){
        try {
            String sql = "UPDATE CHATNOTIFICATION SET status = ?, time = ? WHERE sender_id = ? AND receiver_id = ?";
            this.jdbcTemplate.update(sql, status, time, sender_id, receiver_id);
            return "Update database complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot update database";
        }
    }

    @Transactional(readOnly = true)
    public List<CreateNotification> selectNotification(int account_id1) {
        String sql2 = "SELECT notification_account_id1, notification_account_id2, notification_read_status FROM NOTIFICATION WHERE notification_account_id1 = ?";
        return this.jdbcTemplate.query(sql2, new Object[]{account_id1}, new NotificationRowMapper());
    }

    @Transactional
    public String updateNotificationDB(int account_id1, int account_id2){
        try {
            String sql = "UPDATE NOTIFICATION SET notification_read_status = ? WHERE notification_account_id1 = ? AND notification_account_id2 = ?";
            this.jdbcTemplate.update(sql, 0, account_id1, account_id2);
            return "Update database complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot update database";
        }
    }


}
