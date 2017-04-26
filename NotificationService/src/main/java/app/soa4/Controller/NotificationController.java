package app.soa4.Controller;



import app.soa4.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;
    

    @RequestMapping(value = "/notification/matching", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createNotificationMatching(@RequestBody CreateNotification createNotification){
        this.notificationRepository.makeNewMatchingNotification(createNotification.getAccount_id1(), createNotification.getAccount_id2());
        return new ResponseEntity<>("Create notification complete.", HttpStatus.OK);
    }


}