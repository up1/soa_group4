package app.soa4.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by DSJIN on 6/3/2560.
 */
public class AuthenticatedUser implements Authentication {
    private String name;
    private Long id;
    private boolean authenticated = true;

    AuthenticatedUser(String name,Long id){
        this.name = name;
        this.id = id;
    }
    AuthenticatedUser(String name){
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Long getId(){
        return id;
    }
}
