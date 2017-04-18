package app.soa4.filter;

import app.soa4.authentication.AccountCredentials;
import app.soa4.authentication.TokenAuthenticationService;
import app.soa4.model.User;
import app.soa4.repository.UserRepository;
import app.soa4.util.Hash;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DSJIN on 27/3/2560.
 */
@CrossOrigin(origins = "*")
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
    private TokenAuthenticationService tokenHelper;
    private Hash hash;
    private UserRepository userRepository;
    @Autowired
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager, JdbcTemplate jdbcTemplate) {
        super(new AntPathRequestMatcher(url,"POST"));
        setAuthenticationManager(authenticationManager);
        this.tokenHelper = new TokenAuthenticationService();
        this.hash = new Hash();
        this.userRepository = new UserRepository(jdbcTemplate);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), this.hash.getSha256(credentials.getPassword()));
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = this.userRepository.accountInformation(authResult.getName());
        tokenHelper.genToken(request,user);
        request.setAttribute("information",user);
        chain.doFilter(request,response);
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Access-Control-Allow-Origin", "*");
        HashMap<String, Object> messageResponse = new HashMap<String, Object>();
        messageResponse.put("status", failed.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        writer.println(mapper.writeValueAsString(messageResponse));
    }
}
