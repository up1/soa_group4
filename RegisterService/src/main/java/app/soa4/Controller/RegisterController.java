package app.soa4.Controller;
import java.util.List;

import app.soa4.Modal.Account;
import app.soa4.Modal.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private RegisterRepository registerRepository;
    
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestParam(value = "username") String username , @RequestParam(value = "password") String password , @RequestParam(value = "email") String email){
        if(this.registerRepository.checkAccount(username, password, email)){
            this.registerRepository.createAccount(username, password, email);
            return new ResponseEntity<>("Create account complete.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You can't create account", HttpStatus.OK);
        }
    }
}
