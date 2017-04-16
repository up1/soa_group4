package app.soa4.Controller;

import app.soa4.Modal.Account;
import app.soa4.Modal.AccountRepository;
import app.soa4.Modal.AccountToMatching;
import app.soa4.Modal.CreateAccount;
import app.soa4.Modal.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @RequestMapping(value = "/AccountProfile", method = RequestMethod.GET)
    public Account getAccount(@RequestParam(value="id", defaultValue="2") int id){
        return this.accountRepository.accountProfile((long) id);
    }

    @RequestMapping(value = "/AccountToMatching", method = RequestMethod.GET)
    public AccountToMatching sentToMatching(@RequestParam(value="id", defaultValue="1") int id){
        System.err.print(id);
        return this.accountRepository.accountToMatching((long) id);
    }

    @RequestMapping(value = "/EditAccount", method = RequestMethod.PUT)
    public String updateProfile(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "lastname") String lastname,
                                @RequestParam(value = "age") int age,
                                @RequestParam(value = "sex") String sex,
                                @RequestParam(value = "sextaste") String sextaste,
                                @RequestParam(value = "lat") float lat,
                                @RequestParam(value = "lon") float lon,
                                @RequestParam(value = "location") String location,
                                @RequestParam(value = "des") String des,
                                @RequestParam(value = "id") int id,
                                @RequestParam(value = "search_sex") String search_sex,
                                @RequestParam(value = "search_sextaste") String search_sextaste,
                                @RequestParam(value = "min_age") int min_age,
                                @RequestParam(value = "max_age") int max_age,
                                @RequestParam(value = "distance") float distance){
        return this.accountRepository.editProfile(email,password,name,lastname,age,sex,sextaste,lat,lon,location,des,id,search_sex,search_sextaste,min_age,max_age,distance);
    }

    @RequestMapping(value = "/regis", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerAccount(@RequestBody CreateAccount createAccount){
        if(this.registerRepository.checkAccount(createAccount.getUsername(), createAccount.getPassword(), createAccount.getEmail())){
            this.registerRepository.createAccount(createAccount.getUsername(), createAccount.getPassword(), createAccount.getEmail());
            return new ResponseEntity<>("Create account complete.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You can't create account", HttpStatus.OK);
        }
    }

}
