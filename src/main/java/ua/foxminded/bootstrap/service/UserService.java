package ua.foxminded.bootstrap.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.bootstrap.models.User;

public interface UserService extends UserDetailsService {

    @Transactional(readOnly = true)
    Optional<User> findByLogin(String login);
    @Transactional(readOnly = true)
    Optional<User> findById(Long id);
    @Transactional
    Optional<User> addUser(String login, String password, String roleName, String firstName, String lastName);
}
