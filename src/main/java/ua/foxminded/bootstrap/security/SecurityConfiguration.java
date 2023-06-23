package ua.foxminded.bootstrap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> 
         authorize.requestMatchers("/welcome").permitAll()
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .requestMatchers("/students/**").hasRole("STUDENT")
             .requestMatchers("/teachers/**").hasRole("TEACHER"))
        .formLogin(formLogin ->
         formLogin.usernameParameter("username")
             .passwordParameter("password")
             .loginPage ("/authentication/login"))
        .logout(logout ->
         logout.logoutUrl("/authentication/logout") 
               .logoutSuccessUrl("/authentication/login"));

        return http.build();
    }
}
