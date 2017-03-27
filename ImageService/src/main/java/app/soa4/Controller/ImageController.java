package app.soa4.Controller;

import app.soa4.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> deleteProfileImage(@RequestBody ImageDelete imageDelete){
        int queryResult;                                                                //result return from .jdbcTemplate.update()
        queryResult = this.imageRepository.deleteProfileImage(imageDelete.getId());     //0 is fail, 1 is OK
        if (queryResult == 1){
            return new ResponseEntity<String>("Image deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Image not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/image/chat/delete"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<String> deleteChatImage(@RequestBody ImageDelete imageDelete){
        int queryResult;                                                                //result return from .jdbcTemplate.update()
        queryResult = this.imageRepository.deleteChatImage(imageDelete.getId());        //0 is fail, 1 is OK
        if (queryResult == 1){
            return new ResponseEntity<String>("Image deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Image not found", HttpStatus.NOT_FOUND);
        }
    }
}