package app.soa4.controller;

import app.soa4.model.*;
import app.soa4.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = {"/image/{imagetype}/{imageid}"}, method = GET)
    public Object getImage(@PathVariable("imagetype") String imageType,
                                        @PathVariable("imageid") long id){
        if ("profile".equals(imageType)){
            return this.imageRepository.getProfileImageById(id);
        } else if ("chat".equals(imageType)){
            return this.imageRepository.getChatImageById(id);
        } else {
            return new ResponseEntity<>("Error, wrong URL.", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/image/profile-image/{userid}"}, method = GET)
    public List<String> getProfileImageUrlByUserId(@PathVariable("userid") long userId){
        List<ProfileImage> profileImages = this.imageRepository.getProfileImageByUserId(userId);
        ArrayList<String> imageUrl = new ArrayList<>();
        for (ProfileImage eachProfileImage : profileImages) imageUrl.add(eachProfileImage.getImage_path());
        return imageUrl;
    }

    @RequestMapping(value = {"/image/{imagetype}/AzureUpload"}, method = POST)
    public ResponseEntity<String> addImage(@PathVariable("imagetype") String imageType,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam("imagename") String imageName,
                                           @RequestParam("uid1") long uid1,
                                           @RequestParam("uid2") long uid2) throws IOException{

        String containerName = checkImageType(imageType);
        if ("notMatch".equals(containerName)) {
            return new ResponseEntity<>("Error, wrong URL.", HttpStatus.BAD_REQUEST);
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());
        String[] part = imageName.split("\\.");

        AzureBlob azureBlob = new AzureBlob(containerName);
        String imageUrl = azureBlob.uploadToAzureBlob(
                file.getInputStream(),
                uid1 + "-" + uid2 + "_" + timeStamp + "." + part[part.length-1],
                (int)file.getSize());

        switch(imageType){
            case "profile" :
                imageRepository.addProfileImage(imageUrl, uid1);
                return new ResponseEntity<>("Image added", HttpStatus.OK);
            case "chat" :
                imageRepository.addChatImage(imageUrl, uid1, uid2);
                return new ResponseEntity<>("Image added", HttpStatus.OK);
            default:
                return new ResponseEntity<>("Error, image not added", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"/image/profile/{selector}"}, method = POST)
    public ResponseEntity<String> updateProfileImage(@RequestParam("identify") String id,
                                                     @RequestParam("file") MultipartFile file,
                                                     @RequestParam("imagename") String newImageName,
                                                     @PathVariable("selector") String selector){
        ProfileImage profileImage;
        if ("id".equals(selector)){
            profileImage = this.imageRepository.getProfileImageById(Integer.parseInt(id));
        } else if ("url".equals(selector)){
            profileImage = this.imageRepository.getProfileImageByUrl(id);
        } else {
            return new ResponseEntity<>("Error, image not update", HttpStatus.OK);
        }
        String[] part = profileImage.getImage_path().split("/");
        String imageName = part[part.length-1];

        AzureBlob azureBlob = new AzureBlob("profile-image");
        azureBlob.deleteBlob(imageName);

        String[] newNamePart = newImageName.split("\\.");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());

        String imageUrl = null;
        try {
            imageUrl = azureBlob.uploadToAzureBlob(
                    file.getInputStream(),
                    profileImage.getAccount_id() + "-" + "0" + "_" + timeStamp + "." + newNamePart[1],
                    (int)file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(imageUrl == null)){
            int checkSuccess = this.imageRepository.updateProfileImage(profileImage.getImage_id(), imageUrl);
            if (checkSuccess == 1){
                return new ResponseEntity<>("Image uploaded", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Error, image not update", HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>("Error, image not update", HttpStatus.OK);
        }
    }

    @RequestMapping(value = {"/image/{imagetype}/delete"}, method = DELETE, consumes = "application/json")
    public ResponseEntity<String> deleteImage(@PathVariable("imagetype") String imageType,
                                              @RequestBody ImageDelete imageDelete){
        String queryResult;
        if ("profile".equals(imageType)){
            queryResult = this.imageRepository.deleteProfileImage(imageDelete.getId());
        } else if ("chat".equals(imageType)){
            queryResult = this.imageRepository.deleteChatImage(imageDelete.getId());
        } else {
            return new ResponseEntity<>("Error, wrong URL.", HttpStatus.NOT_FOUND);
        }

        if (!"0".equals(queryResult)){
            String[] part = queryResult.split("/");
            String imageName = part[part.length-1];
            String containerName = checkImageType(imageType);
            if ("notMatch".equals(containerName)){
                return new ResponseEntity<>("Error, wrong URL.", HttpStatus.NOT_FOUND);
            }
            AzureBlob azureBlob = new AzureBlob(containerName);
            return new ResponseEntity<>(azureBlob.deleteBlob(imageName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
    }

    private String checkImageType(String imageType){
        if ("profile".equals(imageType)){
            return "profile-image";
        } else if ("chat".equals(imageType)){
            return "chat-image";
        } else {
            return "notMatch";
        }
    }
}