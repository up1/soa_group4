package app.soa4.exception;

/**
 * Created by ARMY on 5/2/2017.
 */
public class HashRuntimeException extends RuntimeException {
    public HashRuntimeException(Exception e) {
        super("Can't hash this." + e);
    }
}
