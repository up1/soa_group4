package app.soa4.Modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ImageRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public ProfileImage getProfileImageById(long id) {
        try {
            return (ProfileImage) this.jdbcTemplate.queryForObject("SELECT * FROM profile_image WHERE image_id = ?",
                    new Object[]{id}, new BeanPropertyRowMapper(ProfileImage.class));
        }catch (Exception exception) {
            throw new ImageNotFoundException(id);
        }
    }

    @Transactional(readOnly = true)
    public ChatImage getChatImageById(long id) {
        try {
            return (ChatImage) this.jdbcTemplate.queryForObject("SELECT * FROM chat_image WHERE image_id = ?",
                    new Object[]{id}, new BeanPropertyRowMapper(ChatImage.class));
        }catch (Exception exception) {
            throw new ImageNotFoundException(id);
        }
    }

    @Transactional
    public String addProfileImage(String path, long uid){
        try {
            String sql = "INSERT INTO profile_image(image_path, account_id) values (?,?)";
            this.jdbcTemplate.update(sql, path, uid);
            return "Add image complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot add image there is something wrong.";
        }
    }

    @Transactional
    public String addChatImage(String path, long uid1, long uid2){
        try {
            String sql = "INSERT INTO chat_image(image_path, account_id_1, account_id_2) values (?,?,?)";
            this.jdbcTemplate.update(sql, path, uid1, uid2);
            return "Add image complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot add image there is something wrong.";
        }
    }

    @Transactional
    public String deleteProfileImage(long id){
        try {
            String sql = "DELETE FROM profile_image WHERE image_id = ?";
            System.err.print(this.jdbcTemplate.update(sql, id));
            return "Delete image complete";
        }catch (Exception e){
            System.err.println(e.getMessage());
            return "Cannot delete image there is something wrong.";
        }
    }

    @Transactional
    public String deleteChatImage(long id){
        try {
            String sql = "DELETE FROM chat_image WHERE image_id = ?";
            this.jdbcTemplate.update(sql, id);
            return "Delete image complete";
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "Cannot delete image there is something wrong.";
        }
    }
}
