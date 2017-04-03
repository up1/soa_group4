package app.soa4.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Boeing on 4/3/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping()
    public ResponseEntity<Void> postLocation(
            @RequestBody Map<String, Object> payload
    ) {
        Location location = new Location();
        location.setName((String) payload.get("name"));
        location.setLatitude(Float.valueOf(payload.get("Location_lat").toString()));
        location.setLongitude(Float.valueOf(payload.get("Location_long").toString()));
        location.setUsername((String) payload.get("Username"));

        this.locationRepository.save(location);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public Location getLocation(
            @PathVariable("username") String username
    ) {
        return locationRepository.findByUsername(username);
    }
}
