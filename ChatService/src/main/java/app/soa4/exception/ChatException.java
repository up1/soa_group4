package app.soa4.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChatException extends RuntimeException {
    public ChatException(Exception exception) {
        super(exception);
    }
}
