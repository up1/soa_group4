package app.soa4.Repository;

import app.soa4.Modal.ChatImage;
import app.soa4.Exception.ImageNotFoundException;
import app.soa4.Modal.ProfileImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public List<ProfileImage> getProfileImageByUserId(long id) {
        try {
            return this.jdbcTemplate.query("SELECT * FROM profile_image WHERE account_id = ?",
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

    //If there are some error or value is not found will return 0, if fine return 1
    @Transactional
    public String deleteProfileImage(long id){
        try {
            String sql = "DELETE FROM profile_image WHERE image_id = ?";
            ProfileImage profileImage = getProfileImageById(id);
            this.jdbcTemplate.update(sql, id);
            return profileImage.getImage_path();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return "0";
        }
    }

    // This delete function will do two step
    // 1. Get ChatImage by call getImageById store value before deleted
    // 2. Delete image
    // Then return Image URL that store in 1. for delete on Azure
    @Transactional
    public String deleteChatImage(long id){
        try {
            String sql = "DELETE FROM chat_image WHERE image_id = ?";
            ChatImage chatImage = getChatImageById(id);
            this.jdbcTemplate.update(sql, id);
            return chatImage.getImage_path();
        }catch (Exception e){
            System.err.print(e.getMessage());
            return "0";
        }
    }

    @Transactional
    public int updateProfileImage(long id, String url){
        try {
            String sql = "UPDATE profile_image SET image_path = ? WHERE image_id = ?";
            return this.jdbcTemplate.update(sql, url, id);
        }catch (Exception e){
            System.err.print(e.getMessage());
            return 0;
        }
    }
}
