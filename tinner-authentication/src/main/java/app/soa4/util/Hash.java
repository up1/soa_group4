package app.soa4.util;

import app.soa4.exception.HashRuntimeException;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 * @author ARMY
 */
public class Hash {

    public String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new HashRuntimeException(ex);
        }
    }

    private String bytesToHex(byte[] bytes) {
        ArrayList result = new ArrayList();
        for (byte b : bytes)
            result.add(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}
