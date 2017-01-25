package demo;

import java.util.List;
import javax.xml.ws.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user")
    public User getUser(@RequestParam(value="id", defaultValue="1") int id) {
        return this.userRepository.findById((long) id);
    }
    
    @RequestMapping("/users")
    public List<User> getUsers(@RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="items", defaultValue="10") int items){
        return this.userRepository.findItemsPerPage((long) page,(long) items);
    }
}
