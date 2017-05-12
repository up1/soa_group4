package app.soa4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(){
        super ("you are unauthorized for this content.");
    }
}