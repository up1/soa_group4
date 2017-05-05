package app.soa4.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HashRuntimeException extends RuntimeException {
    public HashRuntimeException(String password) {
        super("Hash error " + password);
    }
}
