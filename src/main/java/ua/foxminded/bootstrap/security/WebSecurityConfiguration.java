package ua.foxminded.bootstrap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    
    private static final String[] WHITE_LIST_URLS = {
            "/register**",
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**"
    };
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> 
             authorize.requestMatchers(WHITE_LIST_URLS).permitAll()
                      .requestMatchers("/admin/**").hasRole("ADMIN")
                      .requestMatchers("/students/**").hasRole("STUDENT")
                      .requestMatchers("/teachers/**").hasRole("TEACHER"))
        .formLogin(formLogin ->
             formLogin.usernameParameter("username")
                      .passwordParameter("password")
                      .loginPage ("/register/login"))
        .logout(logout ->
             logout.logoutUrl("/register/logout") 
                   .logoutSuccessUrl("/register/welcome"));

        return http.build();
    }
}
