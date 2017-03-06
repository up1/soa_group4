package app.soa4.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class RouteController {
    @RequestMapping("/")
    String home() {
        return "matching_service";
    }
}
