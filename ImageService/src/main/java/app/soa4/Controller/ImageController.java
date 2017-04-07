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

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    // Get image just communicate with MySQL DB
    @RequestMapping(value = {"/image/{imagetype}/{imageid}"}, method = GET)
    public Object getImage(@PathVariable("imagetype") String imageType,
                                        @PathVariable("imageid") long id){
        if (imageType.equals("profile")){
            return this.imageRepository.getProfileImageById(id);
        } else if (imageType.equals("chat")){
            return this.imageRepository.getChatImageById(id);
        } else {
            return new ResponseEntity<String>("Error, wrong URL.", HttpStatus.NOT_FOUND);
        }
    }

    // Add image have 2 step
    // 1. Try to upload Azure
    // 2. If success will get image URL on Azure, then add into MySQL DB
    @RequestMapping(value = {"/image/{imagetype}/AzureUpload"}, method = POST)
    public ResponseEntity<String> addImage(@PathVariable("imagetype") String imageType,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam("imagename") String imageName,
                                           @RequestParam("uid1") long uid1,
                                           @RequestParam("uid2") long uid2) throws IOException{ // If is profile image uid2 will be 0

        String containerName = checkImageType(imageType);
        if (containerName.equals("0")) {
            //if request path is not math any container then fail.
            return new ResponseEntity<String>("Error, wrong URL.", HttpStatus.BAD_REQUEST);
        }

        //create timestamp for define image name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());
        String[] part = imageName.split("\\.");

        AzureBlob azureBlob = new AzureBlob(containerName);
        String imageUrl = azureBlob.uploadToAzureBlob(
                file.getInputStream(),
                uid1 + "," + uid2 + "-" + timeStamp + "." + part[part.length-1], //image format is : uid1,uid2_timestamp.filetype""
                (int)file.getSize());

        //add image that already uploaded to Azure into MySQL DB
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

    // Update profile image will replace wih new file name, but other remain as ever
    // 1. Get current image from MySQL DB
    // 2. Delete current image on Azure
    // 3. Upload new image to Azure
    // 4. Update new image url into MySQL DB
    @RequestMapping(value = {"/image/profile"}, method = POST)
    public ResponseEntity<String> updateProfileImage(@RequestParam("id") long id,
                                                     @RequestParam("file") MultipartFile file,
                                                     @RequestParam("imagename") String newImageName){
        ProfileImage profileImage = this.imageRepository.getProfileImageById(id);
        String[] part = profileImage.getImage_path().split("/");
        String imageName = part[part.length-1];

        AzureBlob azureBlob = new AzureBlob("profile-image");
        azureBlob.deleteBlob(imageName);

        String[] newNamePart = newImageName.split("\\.");

        //create timestamp for define image name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());

        String imageUrl = null;
        try {
            imageUrl = azureBlob.uploadToAzureBlob(
                    file.getInputStream(),
                    profileImage.getAccount_id() + "," + "0" + "-" + timeStamp + "." + newNamePart[1], //image format is : uid1,uid2_timestamp.filetype""
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

    // When delete image will effect in two place MySQl DB and Azure
    // 1. Try to delete in MySQL DB. If success will get image url on Azure
    // 2. Delete image on Azure
    @RequestMapping(value = {"/image/{imagetype}/delete"}, method = DELETE, consumes = "application/json")
    public ResponseEntity<String> deleteImage(@PathVariable("imagetype") String imageType,
                                              @RequestBody ImageDelete imageDelete){
        String queryResult;
        if (imageType.equals("profile")){
            queryResult = this.imageRepository.deleteProfileImage(imageDelete.getId());
        } else if (imageType.equals("chat")){
            queryResult = this.imageRepository.deleteChatImage(imageDelete.getId());
        } else {
            return new ResponseEntity<String>("Error, wrong URL.", HttpStatus.NOT_FOUND);
        }

        if (!queryResult.equals("0")){
            String[] part = queryResult.split("/");
            String imageName = part[part.length-1];

            String containerName = checkImageType(imageType);
            if (containerName.equals("0")){
                //if request path is not math any container then fail.
                return new ResponseEntity<String>("Error, wrong URL.", HttpStatus.NOT_FOUND);
            }
            AzureBlob azureBlob = new AzureBlob(containerName);
            return new ResponseEntity<String>(azureBlob.deleteBlob(imageName), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Image not found", HttpStatus.NOT_FOUND);
        }
    }

    //define "container" where image will be upload from Path.
    private String checkImageType(String imageType){
        if (imageType.equals("profile")){
            return "profile-image";
        } else if (imageType.equals("chat")){
            return "chat-image";
        } else {
            return "0";
        }
    }
}