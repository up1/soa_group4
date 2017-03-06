package app.Matching.Controller;
import app.Matching.Modal.Account;
import app.Matching.Modal.MatchingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchingController {
    @Autowired
    private MatchingRepository matchingRepository;
    
    @RequestMapping("/matching")
    public Account getAccount(@RequestParam(value="id", defaultValue="1") int id){
        return this.matchingRepository.findById((long) id);
    }
    
    @RequestMapping("/matching")
    public List<Account> getAccounts(@RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="items", defaultValue="10") int items){
        return this.matchingRepository.findItemsPerPage((long) page,(long) items);
    }
}
