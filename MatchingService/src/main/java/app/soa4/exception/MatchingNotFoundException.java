package app.soa4.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchingNotFoundException extends RuntimeException {
    public MatchingNotFoundException(int id) {
        super("Could not find user " + id);
    }


}
