package app.soa4.controller;

import app.soa4.model.Auth;
import app.soa4.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestAttribute("token") String token ,@RequestAttribute("information") User user ) throws JsonProcessingException{
        HashMap<String, Object> mappingResponse = new HashMap<String, Object>() {{
            put("token", token);
            put("user", user);
        }};
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(mappingResponse);
    }
}
