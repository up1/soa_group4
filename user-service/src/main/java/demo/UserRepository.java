package demo;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id=?", new Object[]{id}, new UserRowMapper());
        }catch (Exception exception) {
            throw new UserNotFoundException(id);
        }
    }
    @Transactional(readOnly = true)
    public List<User> findItemsPerPage(Long page, Long items){
        return this.jdbcTemplate.query("Select id,firstname,lastname From USERS WHERE id LIMIT ? OFFSET ?",new Object[]{items,(page-1)*items},new UserRowMapper());
    }
    @Transactional
    public void save(User user) {
        String sql = "INSERT INTO USERS(id, firstname, lastname) VALUES (?,?,?)";
        this.jdbcTemplate.update(sql, user.getId(), user.getFirstname(), user.getLastname());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM USERS WHERE id=?";
        this.jdbcTemplate.update(sql, id);
    }
}
