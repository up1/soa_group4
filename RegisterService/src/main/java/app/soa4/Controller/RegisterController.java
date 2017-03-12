package app.soa4.Controller;
import java.util.List;

import app.soa4.Modal.Account;
import app.soa4.Modal.Account;
import app.soa4.Modal.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private MatchingRepository matchingRepository;
    
    @RequestMapping("/regis")
    public List<Account> getAccount(@RequestParam(value = "username") String username ,  @RequestParam(value = "email") String email){
        return this.matchingRepository.checkAccount(username, email);
    }
}
