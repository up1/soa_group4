package app.soa4.Controller;
import java.util.List;

import app.soa4.Modal.Account;
import app.soa4.Modal.Matching;
import app.soa4.Modal.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MatchingController {
    @Autowired
    private MatchingRepository matchingRepository;
    
    @RequestMapping("/matching")
    public List<Matching> getAccount(@RequestParam(value="id", defaultValue="1") int id){
        return this.matchingRepository.listMatching((long) id);
    }
}
