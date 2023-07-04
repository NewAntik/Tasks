package ua.foxminded.bootstrap.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.dao.UserRepository;
import ua.foxminded.bootstrap.models.User;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;
import ua.foxminded.bootstrap.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest(classes = {UserServiceImpl.class, SecuritySharedConfiguration.class})
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;
    
    @Autowired
    UserService userServ;

    @Test
    void addUser_ShouldAddNewUser() {
        User user = new User("admin", "1234", Role.ADMIN, "Admin", "admin");
        when(userRepository.save(user)).thenReturn(user);
        
        Optional<User> userAfterSave = userServ.addUser("admin", "1234", "Admin", "admin", "admin");
        
        verify(userRepository).save(user);
        assertEquals(Role.ADMIN, userAfterSave.get().getRole());
    }

    @Test
    void addUser_ShouldThrewIllegalArgumentException() {
        when(userRepository.findByLogin("admin")).thenReturn(Optional.of(new User("admin", "1234", Role.ADMIN, "Admin", "admin")));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            userServ.addUser("admin", "1234", "Admin", "admin", "admin");
        });
        assertNotNull(thrown.getMessage());
    }

}
