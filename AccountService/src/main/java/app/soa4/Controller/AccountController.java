package app.soa4.Controller;

import app.soa4.Modal.Account;
import app.soa4.Modal.AccountRepository;
import app.soa4.Modal.AccountToMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

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
                                @RequestParam(value = "location") String location,
                                @RequestParam(value = "des") String des,
                                   @RequestParam(value = "id") int id){
        return this.accountRepository.editProfile(email,password,name,lastname,age,sex,sextaste,location,des, id);
    }


}
