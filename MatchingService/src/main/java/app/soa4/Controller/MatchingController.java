package app.soa4.controller;
import java.util.ArrayList;
import java.util.List;

import app.soa4.model.*;
import app.soa4.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class MatchingController {
    @Autowired
    private MatchingRepository matchingRepository;

    @Value("${myapp.service.image.url}")
    private String imageServiceUrl;

    @Value("${myapp.service.image.port}")
    private String imageServicePort;

    @Value("${myapp.service.chat.url}")
    private String chatServiceUrl;

    @Value("${myapp.service.chat.port}")
    private String chatServicePort;

    @Value("${myapp.service.notification.url}")
    private String notificationServiceUrl;

    @Value("${myapp.service.notification.port}")
    private String notificationServicePort;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/matching")
    public List<Matching> getAccount(@RequestParam(value="id", defaultValue="1") int id){

        String imageServicePath  = imageServiceUrl + imageServicePort + "/image/profile-image/";

        Searching searchingData = this.matchingRepository.getSearchingData(id);

        List<Matching> matchingList = this.matchingRepository.listMatching(id,searchingData);
        for (Matching matching: matchingList) {
            matching.setImgProfile(restTemplate.getForObject(imageServicePath + matching.getId(), ArrayList.class));
            List<SuperlikeCheck> checkList = this.matchingRepository.checkSuperlike(matching.getId(), id);
            if (!checkList.isEmpty()){
                matching.setSuperlike_status(1);
            }else{
                matching.setSuperlike_status(0);
            }
        }
        return  matchingList;
    }

    @RequestMapping(value = "/matching/status", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createMatchingStatus(@RequestBody CreateMatching createMatching){
        List<CreateNotification> createNotifications;
        String notificationServicePath = notificationServiceUrl + notificationServicePort + "/notification/matching";
        String chatServicePath = chatServiceUrl + chatServicePort + "/chat";
        createNotifications = this.matchingRepository.makeStatus(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        if(!createNotifications.isEmpty()) {
            restTemplate.postForObject(notificationServicePath, createNotifications.get(0), String.class);
            restTemplate.postForObject(chatServicePath, createNotifications.get(0), String.class);
        }
        return new ResponseEntity<>("Create status complete.", HttpStatus.OK);
    }

    @RequestMapping(value = "/matching/unmatch", method = RequestMethod.PUT)
    public ResponseEntity<String> unmatchUser(@RequestBody CreateMatching createMatching){
        String chatServicePath = chatServiceUrl + chatServicePort + "/chat";
        this.matchingRepository.unmatchUpdate(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        HttpEntity<CreateMatching> httpEntity = new HttpEntity<>(createMatching);
        restTemplate.exchange(chatServicePath, HttpMethod.DELETE, httpEntity, String.class);
        return new ResponseEntity<>("Unmatch user complete.", HttpStatus.OK);
    }

}
