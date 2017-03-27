package app.soa4.Controller;

import app.soa4.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = {"/image/profile/{imageid}"}, method = RequestMethod.GET)
    public ProfileImage getProfileImage(@PathVariable("imageid") long id){
        return this.imageRepository.getProfileImageById(id);
    }

    @RequestMapping(value = {"/image/chat/{imageid}"}, method = RequestMethod.GET)
    public ChatImage getChatImage(@PathVariable("imageid") long id){
        return this.imageRepository.getChatImageById(id);
    }

    @RequestMapping(value = {"/image/profile"}, method = RequestMethod.POST, consumes = "application/json")
    public String postProfileImage(@RequestBody ProfileImageCreate profileImageCreate){
        return this.imageRepository.addProfileImage(profileImageCreate.getPath(), profileImageCreate.getUid());
    }

    @RequestMapping(value = {"/image/chat"}, method = RequestMethod.POST, consumes = "application/json")
    public String postChatImage(@RequestBody ChatImageCreate chatImageCreate){
        return this.imageRepository.addChatImage(chatImageCreate.getPath(), chatImageCreate.getUid_1(), chatImageCreate.getUid_2());
    }

    @RequestMapping(value = {"/image/profile/delete"}, method = RequestMethod.DELETE, consumes = "application/json")
    public String deleteProfileImage(@RequestBody ImageDelete imageDelete){
        return this.imageRepository.deleteProfileImage(imageDelete.getId());
    }
//
//    @RequestMapping(value = {"/image/profile/delete"}, method = RequestMethod.DELETE, consumes = "application/json")
//    public String deleteChatImage(@RequestBody ImageDelete imageDelete){
//        return this.imageRepository.deleteChatImage(imageDelete.getId());
//    }
}