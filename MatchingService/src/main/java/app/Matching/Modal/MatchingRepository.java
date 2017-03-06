package app.Matching.Modal;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public class MatchingRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public Account findById(long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM ACCOUNTS WHERE id=?", new Object[]{id}, new MatchingRowMapper());
        }catch (Exception exception) {
            throw new MatchingNotFoundException(id);
        }
    }
    
    @Transactional(readOnly = true)
    public List<Account> findItemsPerPage(long page, long items) {
        return this.jdbcTemplate.query("Select account_id,latitude,longtitude From ACCOUNTS WHERE id LIMIT ? OFFSET ?",new Object[]{items,(page-1)*items},new MatchingRowMapper());
    }
    
    
}
