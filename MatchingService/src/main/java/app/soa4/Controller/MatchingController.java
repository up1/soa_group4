package app.soa4.controller;
import java.util.ArrayList;
import java.util.List;

import app.soa4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class MatchingController {
    @Autowired
    private MatchingRepository matchingRepository;

    private RestTemplate restTemplate = new RestTemplate();
    private String imageServiceUrl = "http://161.246.143.204:9004/image/profile-image/";
    private String noficationServiceUrl = "http://128.199.211.151:9005/notification/matching";
    
    @RequestMapping("/matching")
    public List<Matching> getAccount(@RequestParam(value="id", defaultValue="1") int id){

        Searching searchingData = this.matchingRepository.getSearchingData(id);

        List<Matching> matchingList = this.matchingRepository.listMatching(id,
                searchingData.getLat(),
                searchingData.getLon(),
                searchingData.getSex(),
                searchingData.getSexual_taste(),
                searchingData.getMin_age(),
                searchingData.getMax_age(),
                searchingData.getDistance());
        for (Matching matching: matchingList) {
            matching.setImgProfile(restTemplate.getForObject(imageServiceUrl+matching.getId(), ArrayList.class));
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
    public ResponseEntity<?> createMatchingStatus(@RequestBody CreateMatching createMatching){
        List<CreateNotification> createNotifications;
        createNotifications = this.matchingRepository.makeStatus(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        if(!createNotifications.isEmpty()) {
            restTemplate.postForObject(noficationServiceUrl, createNotifications.get(0), String.class);
        };
        return new ResponseEntity<>("Create status complete.", HttpStatus.OK);
    }

    @RequestMapping(value = "/matching/unmatch", method = RequestMethod.PUT)
    public ResponseEntity<?> unmatchUser(@RequestBody CreateMatching createMatching){
        this.matchingRepository.unmatchUpdate(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        return new ResponseEntity<>("Unmatch user complete.", HttpStatus.OK);
    }

}
