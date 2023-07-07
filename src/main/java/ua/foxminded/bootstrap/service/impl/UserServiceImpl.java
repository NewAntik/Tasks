package ua.foxminded.bootstrap.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.bootstrap.dao.UserRepository;
import ua.foxminded.bootstrap.models.MyUserDetails;
import ua.foxminded.bootstrap.models.User;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> new MyUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }
    

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> addUser(String login, String password, String firstName, String lastName) {         
        if(userRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("User with this login already exist!");
        } else {
            return Optional.of(userRepository.save(new User(login, passwordEncoder.encode(password), Role.STAFF, firstName, lastName)));
        }
    }
    

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
