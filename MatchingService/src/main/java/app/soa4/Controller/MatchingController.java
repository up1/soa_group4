package app.soa4.Controller;
import java.util.List;

import app.soa4.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MatchingController {
    @Autowired
    private MatchingRepository matchingRepository;
    
    @RequestMapping("/matching")
    public List<Matching> getAccount(@RequestParam(value="id", defaultValue="1") int id){

        Searching searchingData = this.matchingRepository.getSearchingData(id);

        return this.matchingRepository.listMatching(
                id,
                searchingData.getLat(),
                searchingData.getLon(),
                searchingData.getAge(),
                searchingData.getSex(),
                searchingData.getSexual_taste(),
                searchingData.getMin_age(),
                searchingData.getMax_age(),
                searchingData.getDistance());
    }

    @RequestMapping(value = "/matching/status", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createMatchingStatus(@RequestBody CreateMatching createMatching){
        this.matchingRepository.makeStatus(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        return new ResponseEntity<>("Create status complete.", HttpStatus.OK);
    }

    @RequestMapping(value = "/matching/unmatch", method = RequestMethod.PUT)
    public ResponseEntity<?> unmatchUser(@RequestBody CreateMatching createMatching){
        this.matchingRepository.unmatchUpdate(createMatching.getAccount_do(), createMatching.getAccount_done(), createMatching.getStatus());
        return new ResponseEntity<>("Unmatch user complete.", HttpStatus.OK);
    }
}
