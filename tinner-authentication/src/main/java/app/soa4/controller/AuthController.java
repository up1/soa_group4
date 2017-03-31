package app.soa4.controller;

import app.soa4.model.Auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Auth> auth(@RequestAttribute("token") String token) throws JsonProcessingException{
        Auth auth = new Auth();
        auth.setToken(token);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(auth,responseHeaders, HttpStatus.OK);
    }
}
