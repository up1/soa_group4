package app.soa4.repository;

import app.soa4.exception.ChatException;
import app.soa4.model.ChatConversation;
import app.soa4.model.ChatCreate;
import app.soa4.model.ChatReply;
import app.soa4.model.ChatUpdateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ChatRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  

    public String addNewChat(int user_one, int user_two, String channel, long time, int status) {
        try {
            String sql = "INSERT INTO conversation(user_one, user_two, channel, time, status)" +
                    " values (?,?,?,?,?)";
            this.jdbcTemplate.update(sql, user_one, user_two, channel, time, status);
            return "Created new chat";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public ChatConversation getConversation(String channel) {
        try {
            return (ChatConversation) this.jdbcTemplate.queryForObject("SELECT * FROM conversation WHERE channel = ?",
                    new Object[]{channel}, new BeanPropertyRowMapper(ChatConversation.class));
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public List<ChatConversation> getChatList(int userId) {
        try {
            return this.jdbcTemplate.query("SELECT * FROM conversation WHERE user_one = ? OR user_two = ?",
                    new Object[]{userId, userId}, new BeanPropertyRowMapper(ChatConversation.class));
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public List<ChatReply> getChatReply(String channel, int offset) {
        try {
            return this.jdbcTemplate.query("(SELECT * FROM conversation_reply WHERE channel = ? " +
                            "ORDER BY time DESC LIMIT 20 OFFSET ?) ORDER BY time ASC",
                    new Object[]{channel, offset}, new BeanPropertyRowMapper(ChatReply.class));
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    public String updateChatMessageStatus(ChatUpdateStatus chatUpdateStatus){
        try {
            String sql = "UPDATE conversation_reply SET status = 1 WHERE channel = ? AND time <= ?";
            this.jdbcTemplate.update(sql, chatUpdateStatus.getChannel(), chatUpdateStatus.getTime());
            return "chat updated";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    public String deleteChat(String channel){
        try {
            String sql = "DELETE FROM conversation WHERE channel = ?";
            this.jdbcTemplate.update(sql, channel);
            return "chat deleted";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }
}
