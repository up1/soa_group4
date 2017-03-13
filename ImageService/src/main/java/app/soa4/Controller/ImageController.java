package app.soa4.Controller;

import app.soa4.Modal.Image;
import app.soa4.Modal.ImageCreate;
import app.soa4.Modal.ImageDelete;
import app.soa4.Modal.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = {"/image/profile/{imageid}", "/image/chat/{imageid}"}, method = RequestMethod.GET)
    public Image getImage(@PathVariable("imageid") long id){
        return this.imageRepository.getImageById(id);
    }

    @RequestMapping(value = {"/image/profile", "/image/chat"}, method = RequestMethod.POST, consumes = "application/json")
    public String postImage(@RequestBody ImageCreate imageCreate){
        return this.imageRepository.addImage(imageCreate.getType(),imageCreate.getName(),imageCreate.getPath(),imageCreate.getUid());
    }

    @RequestMapping(value = "/image/delete", method = RequestMethod.DELETE, consumes = "application/json")
    public String deleteImage(@RequestBody ImageDelete imageDelete){
        return this.imageRepository.deleteImage(imageDelete.getId());
    }
}