package app.soa4.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DSJIN on 8/4/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Hello(){
        return "Hello : " + System.currentTimeMillis();
    }
}
