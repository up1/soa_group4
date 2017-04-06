package app.soa4.Controller;

import app.soa4.Modal.*;
import app.soa4.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = {"/image/{imagetype}/AzureUpload"}, method = RequestMethod.POST)
    public ResponseEntity<String> addImage(@PathVariable("imagetype") String imageType,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("imagename") String imageName,
                           @RequestParam("uid1") long uid1,
                           @RequestParam("uid2") long uid2) throws IOException{

        String containerName = null;
        if (imageType.equals("profile")){
            containerName = "profile-image";
        } else if (imageType.equals("chat")){
            containerName = "chat-image";
        } else {
            return new ResponseEntity<String>("Error, image not added", HttpStatus.BAD_REQUEST);
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());
        String[] part = imageName.split("\\.");

        AzureBlob azureBlob = new AzureBlob(containerName);
        String imageUrl = azureBlob.uploadToAzureBlob(
                file.getInputStream(),
                uid1 + "," + uid2 + "_" + timeStamp + "." + part[part.length-1],
                (int)file.getSize());

        switch(imageType){
            case "profile" :
                imageRepository.addProfileImage(imageUrl, uid1);
                return new ResponseEntity<String>("Image added", HttpStatus.OK);
            case "chat" :
                imageRepository.addChatImage(imageUrl, uid1, uid2);
                return new ResponseEntity<String>("Image added", HttpStatus.OK);
            default:
                return new ResponseEntity<String>("Error, image not added", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"/image/profile/{imageid}"}, method = RequestMethod.GET)
    public ProfileImage getProfileImage(@PathVariable("imageid") long id){
        return this.imageRepository.getProfileImageById(id);
    }

    @RequestMapping(value = {"/image/chat/{imageid}"}, method = RequestMethod.GET)
    public ChatImage getChatImage(@PathVariable("imageid") long id){
       return this.imageRepository.getChatImageById(id);
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