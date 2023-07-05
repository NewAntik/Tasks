package ua.foxminded.bootstrap.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.bootstrap.models.User;

public interface UserService extends UserDetailsService {

    @Transactional
    Optional<User> findByLogin(String login);
    @Transactional
    Optional<User> findById(Long id);
    @Transactional
    Optional<User> addUser(String login, String password, String roleName, String firstName, String lastName);

    @Transactional
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
