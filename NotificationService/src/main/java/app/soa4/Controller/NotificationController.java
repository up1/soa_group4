package app.soa4.controller;


import app.soa4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class  NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @RequestMapping(value = "/notification/matching", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createNotificationMatching(@RequestBody CreateNotification createNotification){
        this.notificationRepository.makeNewMatchingNotification(createNotification.getAccount_id1(), createNotification.getAccount_id2());
        return new ResponseEntity<>("Create notification complete.", HttpStatus.OK);
    }

    @RequestMapping(value = "/notification/{userId}", method = RequestMethod.GET)
    public List<CreateNotification> getAccount_id1(@PathVariable("userId") int account_id1){
        return this.notificationRepository.selectNotification(account_id1);
    }

    @RequestMapping(value = "/notification/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateNotification(@RequestBody UpdateNotification updateNotification){
        return new ResponseEntity(this.notificationRepository.updateNotificationDB(
                updateNotification.getAccount_id1(),
                updateNotification.getAccount_id2())
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/notification/chatting", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> chatNotifiying(@RequestBody ChatNotification chatNotification){
        this.notificationRepository.chatNotifiying(chatNotification.getSender_id(), chatNotification.getReciever_id(), chatNotification.getTime(), chatNotification.getStatus());
        return new ResponseEntity<>("Notify user complete", HttpStatus.OK);
    }

    @RequestMapping(value = "/notification/chatupdate", method = RequestMethod.PUT)
    public ResponseEntity<String> updateChatNotification(@RequestBody ChatNotification chatNotification){
        return new ResponseEntity(this.notificationRepository.updateChatNotificationDB(
                chatNotification.getSender_id(), chatNotification.getReciever_id(), chatNotification.getTime(), chatNotification.getStatus()), HttpStatus.OK);
    }
}