package app.soa4.Modal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchingRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public List<Matching> listMatching(long id) {
        try {
            return this.jdbcTemplate.query("SELECT * FROM ACCOUNTS WHERE account_id = ?", new Object[]{id}, new MatchingRowMapper());
        }catch (Exception exception) {
            throw new MatchingNotFoundException(id);
        }
    }

    @Transactional
    public void makeLike(String userNameOwner , String userName){
        String sql = "INSERT INTO LIKES(id_user_like, id_user_liked) values (?,?)";
        this.jdbcTemplate.update(sql, userNameOwner, userName);
    }
}
