package app.soa4.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import app.soa4.model.User;


public class TokenAuthenticationService {
    private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private String SECRET = "mbogoviN";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public void genToken(HttpServletRequest request, User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id",user.getId());
        // generate a token .
        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        request.setAttribute("token", TOKEN_PREFIX + " " + JWT);
    }
    public void genToken(HttpServletResponse response, User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id",user.getId());
        // generate a token .
        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }
    public Authentication getToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);

        if(token != null) {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX + " ", ""));
            // Parse token
            Claims body = claims.getBody();

            String username = body.getSubject();
            long id = (long) body.get("id");

            if(username != null) {
                return new AuthenticatedUser(username,id);
            }
        }

        return null;
    }

}
