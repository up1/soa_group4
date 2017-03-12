package app.soa4.Modal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ImageRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public Image getImageById(long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM IMAGE WHERE image_id = ?", new Object[]{id}, new ImageRowMapper());
        }catch (Exception exception) {
            throw new ImageNotFoundException(id);
        }
    }

    @Transactional
    public String addImage(String type , String name, String path, int uid){
        try {
            String sql = "INSERT INTO IMAGE(image_type, image_name, image_path, account_id) values (?,?,?,?)";
            this.jdbcTemplate.update(sql, type, name, path, uid);
            return "Add image complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot add image there is somthig wrong.";
        }
    }
}
