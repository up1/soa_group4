package app.soa4.Modal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(Long id) {
        super("Could not find image " + id);
    }


}
