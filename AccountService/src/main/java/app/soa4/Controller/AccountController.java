package app.soa4.controller;

import app.soa4.model.*;
import app.soa4.repository.AccountRepository;
import app.soa4.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegisterRepository registerRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private String imageServiceUrl = "http://139.59.117.28:9004/image/profile-image/";

    @RequestMapping(value = "/AccountProfile/{userId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable long userId){
        Account account = this.accountRepository.accountProfile(userId);
        account.setAccount_imgsprofile(restTemplate.getForObject(imageServiceUrl+userId, ArrayList.class));
        return account;

    }


    @RequestMapping(value = "/EditAccount", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProfile(@RequestBody Editdata editdata){
        return new ResponseEntity(this.accountRepository.editProfile(editdata), HttpStatus.OK);
    }

    @RequestMapping(value = "/EditPassword", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePassword(@RequestBody EditPassword editpassword){
        return new ResponseEntity(this.accountRepository.editPassword(
                editpassword.getAccount_password(),
                editpassword.getAccount_id())
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/regis", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> registerAccount(@RequestBody CreateAccount createAccount){
        if(this.registerRepository.checkAccount(createAccount.getUsername(), createAccount.getPassword(), createAccount.getEmail())){
            this.registerRepository.createAccount(createAccount.getUsername(), createAccount.getPassword(), createAccount.getEmail());
            return new ResponseEntity<>("Create account complete.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You can't create account", HttpStatus.OK);
        }
    }



}