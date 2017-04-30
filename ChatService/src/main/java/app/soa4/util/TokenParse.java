package app.soa4.util;

import app.soa4.model.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import javax.servlet.http.HttpServletRequest;

public class TokenParse {
    private String SECRET = "mbogoviN";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public TokenUser parseToken(String token){

        if(token != null) {
            if (!token.isEmpty()){
                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX + " ", ""));
                // Parse token
                Claims body = claims.getBody();
                String username = body.getSubject();
                long id = Integer.toUnsignedLong((int)body.get("id"));

                if(username != null) {
                    return new TokenUser(username,id);
                }
            }
        }

        return null;
    }
}
