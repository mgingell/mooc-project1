package ca.gingell.mooc;

import org.h2.tools.RunScript;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;


@SpringBootApplication
public class Main {

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfig(DataSource dataSource) {
        return new WebSecurityConfigurerAdapter() {
       	/*  Uncomment this to fix some of the issues with authorization */
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                    .antMatchers("/h2-console/**").hasRole("ADMIN")//allow h2 console access to admins only
                    .anyRequest().authenticated()//all other urls can be access by any authenticated role
                    .and().formLogin()//enable form login instead of basic login
                    .and().csrf().ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
                    .and().headers().frameOptions().sameOrigin();//allow use of frame to same origin urls
            }
        	
            @Override
            protected void configure(AuthenticationManagerBuilder builder) throws Exception {
                builder.jdbcAuthentication()
                       .passwordEncoder(new BCryptPasswordEncoder()) // leave plaintext 8)
                       .dataSource(dataSource)
                       .usersByUsernameQuery(getUserQuery());
            }
            
            private String getUserQuery() {
           	   return "SELECT username as username, password as password, enabled as enabled FROM USERS WHERE username = ?";
            }
            
        };
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}