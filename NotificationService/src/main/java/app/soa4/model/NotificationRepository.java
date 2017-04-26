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
        String sql = "INSERT INTO NOTIFICATION(notification_account_id1, notification_account_id2, " +
                ") values (?,?,?)";
        this.jdbcTemplate.update(sql, account_id1, account_id2, 1);
        this.jdbcTemplate.update(sql, account_id2, account_id1, 1);
    }



    @Transactional(readOnly = true)
    public List<CreateNotification> selectNotification(int account_id1) {
        String sql2 = "SELECT notification_account_id1, notification_account_id2, notification_read_status FROM NOTIFICATION WHERE notification_account_id1 = ?";
        List<CreateNotification> result = this.jdbcTemplate.query(sql2, new List[]{Arrays.asList(account_id1)}, new NotificationRowMapper());
        return result;
    }
}
