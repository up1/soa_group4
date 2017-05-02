package app.soa4.repository;

import app.soa4.exception.ChatException;
import app.soa4.model.*;
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
            String createConversationSql = "INSERT INTO conversation(channel, time, status)" +
                    " values (?,?,?)";
            this.jdbcTemplate.update(createConversationSql, channel, time, status);

            String createUserStatusSql = "INSERT INTO chat_read_user_status(channel, user_id, time, status)" +
                    " values (?,?,?,?)";
            this.jdbcTemplate.update(createUserStatusSql, channel, user_one, time, status);
            this.jdbcTemplate.update(createUserStatusSql, channel, user_two, time, status);
            return "Created new chat";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public ChatConversation getConversation(String channel, int userId) {
        try {
            return (ChatConversation) this.jdbcTemplate.queryForObject("SELECT * FROM chat_read_user_status WHERE channel = ? AND user_id <> ?",
                    new Object[]{channel, userId}, new BeanPropertyRowMapper(ChatConversation.class));
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public List<ChatConversation> getChatList(int userId) {
        try {
            String sql = "SELECT conversation.channel, chat_read_user_status.user_id, conversation.status AS room_status, chat_read_user_status.status " +
                    "FROM conversation " +
                    "INNER JOIN chat_read_user_status " +
                    "ON conversation.channel=chat_read_user_status.channel " +
                    "WHERE user_id <> ?";
            return this.jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper(ChatConversation.class));
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    @Transactional(readOnly = true)
    public ChatConversation getOppositeId(int userId, String channel) {
        try {
            return (ChatConversation)this.jdbcTemplate.queryForObject("SELECT user_id FROM chat_read_user_status WHERE channel = ? AND user_id <> ? ",
                    new Object[]{channel, userId}, new BeanPropertyRowMapper(ChatConversation.class));
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

    public String updateChatRead(ChatUpdateStatus chatUpdateStatus){
        try {
            String sql = "UPDATE chat_read_user_status SET status = 1, time = ? WHERE channel = ? ";
            this.jdbcTemplate.update(sql, chatUpdateStatus.getTime(), chatUpdateStatus.getChannel());
            return "chat updated";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    public String updateChatNotRead(ChatUpdateStatus chatUpdateStatus){
        try {
            String sql = "UPDATE chat_read_user_status SET status = 0, time = ? WHERE channel = ? AND user_id <> ?";
            this.jdbcTemplate.update(sql, chatUpdateStatus.getTime(), chatUpdateStatus.getChannel(), chatUpdateStatus.getUser_id());
            return "chat updated";
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new ChatException(exception);
        }
    }

    public String updateChatRoomStatus(ChatUpdateChatRoomStatus chatUpdateChatRoomStatus){
        try {
            String sql = "UPDATE conversation SET status = ? WHERE channel = ?";
            this.jdbcTemplate.update(sql, chatUpdateChatRoomStatus.getStatus(), chatUpdateChatRoomStatus.getChannel());
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
