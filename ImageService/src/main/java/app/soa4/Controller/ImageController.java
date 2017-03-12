package app.soa4.Controller;
import java.util.List;
import java.util.Map;

import app.soa4.Modal.Image;
import app.soa4.Modal.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/image/profile/{imageid}", method = RequestMethod.GET)
    public Image getProfileImage(@PathVariable("imageid") int id){
        return this.imageRepository.getImageById((long) id);
    }

    @RequestMapping(value = "/image/profile/", method = RequestMethod.POST)
    public String postProfileImage(@RequestParam(value = "type") String type,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "path") String path,
                                  @RequestParam(value = "uid") int uid){
        return this.imageRepository.addImage(type,name,path,uid);
    }

    @RequestMapping(value = "/image/chat/{imageid}", method = RequestMethod.GET)
    public Image getChatImage(@PathVariable("imageid") int id){
        return this.imageRepository.getImageById((long) id);
    }

    @RequestMapping(value = "/image/chat/", method = RequestMethod.POST)
    public String postChatImage(@RequestParam(value = "type") String type,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "path") String path,
                         @RequestParam(value = "uid") int uid){
        return this.imageRepository.addImage(type,name,path,uid);
    }

}
