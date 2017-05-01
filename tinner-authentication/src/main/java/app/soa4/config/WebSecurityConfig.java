package app.soa4.config;


import app.soa4.authentication.JWTAuthenticateFilter;
import app.soa4.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.cors().disable();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/info").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers("/metrics").permitAll()
                .antMatchers("/trace").permitAll()
                .antMatchers("/prometheus").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTLoginFilter("/auth", authenticationManager(),jdbcTemplate), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticateFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authenticate
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT account_username, account_password, enable FROM ACCOUNT WHERE account_username = ?")
                .authoritiesByUsernameQuery("SELECT account_username,role FROM ACCOUNT WHERE account_username = ?");
    }

}
