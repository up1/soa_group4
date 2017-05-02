package app.soa4.controller;

import app.soa4.adapter.AccountAdapter;
import app.soa4.exception.UnauthorizedException;
import app.soa4.model.*;
import app.soa4.repository.ChatRepository;
import app.soa4.util.TokenParse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    private AccountAdapter accountAdapter;
    private static final TokenParse tokenParse = new TokenParse();

    @Autowired
    public ChatController() {
        this.accountAdapter = new AccountAdapter();
    }

    @RequestMapping(value = {"/chat"}, method = POST)
    public String postChat(@RequestBody ChatCreate chatCreate){
        int userOneInt = chatCreate.getAccount_id1();
        int userTwoInt = chatCreate.getAccount_id2();
        String channel = CreateChannelName(userOneInt, userTwoInt);
        long time = System.currentTimeMillis();
        int status = 0;
        return chatRepository.addNewChat(userOneInt, userTwoInt, channel, time, status);
    }

    @RequestMapping(value = {"/chatlist/{userId}"}, method = GET)
    public List<ChatConversation> getChatList(@PathVariable("userId") int userId){
        List<ChatConversation> chatList = chatRepository.getChatList(userId);
        List<ChatConversation> realChatList = new ArrayList<>();
        for (ChatConversation chatConversation : chatList) {
            int userIdA = Integer.parseInt(chatConversation.getChannel().substring(0,6));
            int userIdB = Integer.parseInt(chatConversation.getChannel().substring(6));
            if (userId == userIdA || userId == userIdB) {
                chatConversation.setName(accountAdapter.getAccountName(chatConversation.getUser_id()));
                realChatList.add(chatConversation);
            }
        }
        return realChatList;
    }

    @RequestMapping(value = {"/chat/{channel}"}, method = GET)
    public ResponseEntity<ChatConversation> getChatOppositeDetailToken(@RequestHeader("Authorization") String token,
                                              @PathVariable("channel") String channel){
        int userId;
        TokenUser tokenUser = tokenParse.parseToken(token);
        userId = Math.toIntExact(tokenUser.getId());
        if (tokenUser!=null){
            return new ResponseEntity<ChatConversation>(getChatOppositeDetail(userId, channel), HttpStatus.OK);
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = {"/chatdetail/{channel}/{id}"}, method = GET)
    public ResponseEntity<ChatConversation> getChatOppositeDetailPath(@PathVariable("id") Integer id,
                                                                         @PathVariable("channel") String channel){
        return new ResponseEntity<ChatConversation>(getChatOppositeDetail(id, channel), HttpStatus.OK);
    }

    private ChatConversation getChatOppositeDetail(int userId, String channel){
        ChatConversation chatConversation = chatRepository.getConversation(channel, userId);
        chatConversation.setName(accountAdapter.getAccountName(chatConversation.getUser_id()));
        return chatConversation;
    }

    @RequestMapping(value = {"/chat/{channel}/{offset}"}, method = GET)
    public ResponseEntity<List<ChatReply>> getChatMessage(@RequestHeader("Authorization") String token,
                                        @PathVariable("channel") String channel,
                                        @PathVariable("offset") int offset){
        TokenUser tokenUser = tokenParse.parseToken(token);
        if (tokenUser!=null){
            List<ChatReply> chatReplyList = chatRepository.getChatReply(channel, offset);
            return new ResponseEntity<List<ChatReply>>(chatReplyList, HttpStatus.OK);
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = {"/chat"}, method = PUT)
    public ResponseEntity<String> updateChatMessageStatus(@RequestBody ChatUpdateStatus chatUpdateStatus){
        String updateResult;
        String createNotiResult = "";
        RestTemplate restTemplate = new RestTemplate();
        NotificationCreate notificationCreate = new NotificationCreate();
        if (chatUpdateStatus.getStatus() == 1){
            String url = "http://128.199.163.168:9005/notification/chatupdate";
            notificationCreate.setSender_id(chatUpdateStatus.getUser_id());
            ChatConversation oppositeId = chatRepository.getOppositeId(chatUpdateStatus.getUser_id(), chatUpdateStatus.getChannel());
            notificationCreate.setReciever_id(oppositeId.getUser_id());
            notificationCreate.setTime(chatUpdateStatus.getTime());
            notificationCreate.setStatus(0);
            createNotiResult = restTemplate.postForObject( url, notificationCreate, String.class);

            updateResult = chatRepository.updateChatRead(chatUpdateStatus);
        } else {
            String url = "http://128.199.163.168:9005/notification/chatting";
            notificationCreate.setSender_id(chatUpdateStatus.getUser_id());
            ChatConversation oppositeId = chatRepository.getOppositeId(chatUpdateStatus.getUser_id(), chatUpdateStatus.getChannel());
            notificationCreate.setReciever_id(oppositeId.getUser_id());
            notificationCreate.setTime(chatUpdateStatus.getTime());
            notificationCreate.setStatus(1);
            createNotiResult = restTemplate.postForObject( url, notificationCreate, String.class);

            updateResult = chatRepository.updateChatNotRead(chatUpdateStatus);
        }
        return new ResponseEntity<>(updateResult + " " + createNotiResult, HttpStatus.OK);
    }

    @RequestMapping(value = {"/chatroom"}, method = PUT)
    public ResponseEntity<String> updateChatRoomStatus(@RequestHeader("Authorization") String token,
                                                       @RequestBody ChatUpdateChatRoomStatus chatUpdateChatRoomStatus){
        int userId;
        TokenUser tokenUser = tokenParse.parseToken(token);
        System.err.println(chatUpdateChatRoomStatus.getUser_id() + "-" + chatUpdateChatRoomStatus.getChannel() + "-" + chatUpdateChatRoomStatus.getStatus());
        if (tokenUser!=null){
            userId = Math.toIntExact(tokenUser.getId());
            if(chatUpdateChatRoomStatus.getStatus() == 0){
                System.err.println("this is 1");
                chatUpdateChatRoomStatus.setStatus(userId);
                String updateResult = chatRepository.updateChatRoomStatus(chatUpdateChatRoomStatus);
                return new ResponseEntity<>(updateResult, HttpStatus.OK);
            }else{
                System.err.println("this is 2");
                chatUpdateChatRoomStatus.setStatus(userId + chatUpdateChatRoomStatus.getUser_id());
                String updateResult = chatRepository.updateChatRoomStatus(chatUpdateChatRoomStatus);
                System.err.println("this is status"+chatUpdateChatRoomStatus.getStatus());
                return new ResponseEntity<>(updateResult, HttpStatus.OK);
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = {"/chat"}, method = DELETE)
    public ResponseEntity<String> deleteChat(@RequestBody ChatDelete chatDelete){
            String channel = CreateChannelName(chatDelete.getAccount_do(), chatDelete.getAccount_done());
            String deleteResult = chatRepository.deleteChat(channel);
            return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }

    private String CreateChannelName(int userOneInt, int userTwoInt){
        String user_one = String.format("%06d", userOneInt);
        String user_two = String.format("%06d", userTwoInt);
        if (userOneInt < userTwoInt){
            return user_one + user_two;
        }else {
            return user_two + user_one;
        }
    }
}