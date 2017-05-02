package app.soa4.exception;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }

    public AccountNotFoundException(String username, String email) {
        super("Already have " + username + " account or " + email + " email.");
    }
}
