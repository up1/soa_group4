package app.soa4.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchingController {
    @RequestMapping("/matching")
    public String getmatching(){
        return "OK";
    }
}
