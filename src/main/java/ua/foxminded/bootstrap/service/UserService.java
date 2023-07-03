package ua.foxminded.bootstrap.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import ua.foxminded.bootstrap.models.User;

public interface UserService extends UserDetailsService {

    Optional<User> findById(Long id);
    void addUser(String login, String password, String roleName, String firstName, String lastName);
}
