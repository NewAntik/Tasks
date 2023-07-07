package ua.foxminded.bootstrap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class SecuritySharedConfiguration {

  
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @PostConstruct
//    void init() {
//    PasswordEncoder e = new BCryptPasswordEncoder();
//    System.out.println("PASSWORD -------->>>"+e.encode("1234"));
//    }
}
