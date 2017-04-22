package app.soa4.Controller;

import app.soa4.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegisterRepository registerRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private String imageServiceUrl = "http://localhost:9004/image/profile-image/";

    @RequestMapping(value = "/AccountProfile/{userId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable long userId){
        Account account = this.accountRepository.accountProfile(userId);
        account.setAccount_imgsprofile(restTemplate.getForObject(imageServiceUrl+userId, ArrayList.class));
        return account;

    }

    @RequestMapping(value = "/AccountToMatching", method = RequestMethod.GET)
    public AccountToMatching sentToMatching(@RequestParam(value="id", defaultValue="1") int id){
        System.err.print(id);
        return this.accountRepository.accountToMatching((long) id);
    }

    @RequestMapping(value = "/EditAccount", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProfile(@RequestBody Editdata editdata){
        return new ResponseEntity(this.accountRepository.editProfile(
                editdata.getAccount_email(),
                editdata.getAccount_password(),
                editdata.getAccount_name(),
                editdata.getAccount_lastname(),
                editdata.getAccount_birthday(),
                editdata.getAccount_sex(),
                editdata.getAccount_sexual_taste(),
                editdata.getAccount_latitude(),
                editdata.getAccount_longtitude(),
                editdata.getAccount_location(),
                editdata.getAccount_descriptions(),
                editdata.getAccount_id(),
                editdata.getSearch_sex(),
                editdata.getSearch_sexual_taste(),
                editdata.getSearch_min_age(),
                editdata.getSearch_max_age(),
                editdata.getSearch_distance()), HttpStatus.OK);
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