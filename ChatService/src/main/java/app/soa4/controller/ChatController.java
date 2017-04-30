package app.soa4.controller;

import app.soa4.adapter.AccountAdapter;
import app.soa4.exception.UnauthorizedException;
import app.soa4.model.*;
import app.soa4.repository.ChatRepository;
import app.soa4.util.TokenParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    private static final TokenParse tokenParse = new TokenParse();

    @RequestMapping(value = {"/chat"}, method = POST)
    public String postChat(@RequestBody ChatCreate chatCreate){
        int userOneInt = chatCreate.getUser_one();
        int userTwoInt = chatCreate.getUser_two();
        String channel = CreateChannelName(userOneInt, userTwoInt);
        long time = System.currentTimeMillis();
        int status = 2;
        return chatRepository.addNewChat(userOneInt, userTwoInt, channel, time, status);
    }

    @RequestMapping(value = {"/chatlist/{userId}"}, method = GET)
    public List<ChatConversation> getChatList(@PathVariable("userId") int userId){
        AccountAdapter accountAdapter = new AccountAdapter();
        String name;
        List<ChatConversation> chatList = chatRepository.getChatList(userId);
        for (ChatConversation chatConversation : chatList) {
            int userOneId = chatConversation.getUser_one();
            int userTwoId = chatConversation.getUser_two();
            if (userId == userOneId){
                name = accountAdapter.getAccountName(userTwoId);
                chatConversation.setName(name);
            } else {
                name = accountAdapter.getAccountName(userOneId);
                chatConversation.setName(name);
            }
        }
        return chatList;
    }

    @RequestMapping(value = {"/chat/{channel}/{offset}"}, method = GET)
    public ResponseEntity<List<ChatReply>> getChatMessage(@RequestHeader("Authorization") String token,
                                        @PathVariable("channel") String channel,
                                        @PathVariable("offset") int offset){
        TokenUser tokenUser = tokenParse.parseToken(token);
        int userId;
        if (tokenUser!=null){
            userId = Math.toIntExact(tokenUser.getId());
            ChatConversation chatConversation = chatRepository.getConversation(channel);
            if (userId == chatConversation.getUser_one() || userId == chatConversation.getUser_two()) {
                AccountAdapter accountAdapter = new AccountAdapter();
                List<ChatReply> chatReply = chatRepository.getChatReply(channel, offset);
                int userOneId = chatConversation.getUser_one();
                int userTwoId = chatConversation.getUser_two();
                String userOneName = accountAdapter.getAccountName(userOneId);
                String userTwoName = accountAdapter.getAccountName(userTwoId);
                for (ChatReply eachChatReply : chatReply){
                    if (eachChatReply.getUser_id() == userOneId){
                        eachChatReply.setName(userOneName);
                    } else {
                        eachChatReply.setName(userTwoName);
                    }
                }
                return new ResponseEntity<>(chatReply, HttpStatus.OK);
            }else{
                throw new UnauthorizedException();
            }
        }
        throw new UnauthorizedException();
    }

    @RequestMapping(value = {"/chat"}, method = PUT)
    public ResponseEntity<String> updateChatMessageStatus(@RequestHeader("Authorization") String token,
                                                  @RequestBody ChatUpdateStatus chatUpdateStatus){
        TokenUser tokenUser = tokenParse.parseToken(token);
        if (tokenUser!=null){
            String updateResult = chatRepository.updateChatMessageStatus(chatUpdateStatus);
            return new ResponseEntity<>(updateResult, HttpStatus.OK);

        }
        return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = {"/chat/{userOne}/{userTwo}"}, method = DELETE)
    public ResponseEntity<String> deleteChat(@RequestHeader("Authorization") String token,
                                             @PathVariable("userOne") int userOneInt,
                                             @PathVariable("userTwo") int userTwoInt){
        TokenUser tokenUser = tokenParse.parseToken(token);
        if (tokenUser!=null){
            String channel = CreateChannelName(userOneInt, userTwoInt);
            String deleteResult = chatRepository.deleteChat(channel);
            return new ResponseEntity<>(deleteResult, HttpStatus.OK);
        }
        return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
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