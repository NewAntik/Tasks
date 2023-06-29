package ua.foxminded.bootstrap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.bootstrap.dao.UserRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.User;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {LoginController.class},
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {UserServiceImpl.class}))
@Import(WebSecurityConfiguration.class)
class LoginTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void shouldLoginUserWithCorrectUsernameAndPassword() throws Exception {
        User user = new User(
                "test",
                passwordEncoder.encode("1234"),
                Role.ADMIN,
                "Test",
                "Testerson"
        );

        when(userRepository.findByLogin("test"))
                .thenReturn(Optional.of(user));


        mvc.perform(formLogin("/register/login")
                        .user("username", user.getLogin())
                        .password("1234")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/welcome"));
    }

    @Test
    void shouldNotLoginUserWithBadPassword() throws Exception {
        User user = new User(
                "test",
                passwordEncoder.encode("secret"),
                Role.ADMIN,
                "Test",
                "Testerson"
        );

        when(userRepository.findByLogin("test"))
                .thenReturn(Optional.of(user));


        mvc.perform(formLogin("/register/login")
                        .user("username", user.getLogin())
                        .password("1234")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/register/login?error"));
    }

    @Test
    void shouldNotLoginUserWithBadLogin() throws Exception {

        when(userRepository.findByLogin(anyString()))
                .thenReturn(Optional.empty());


        mvc.perform(formLogin("/register/login")
                        .user("username", "test")
                        .password("1234")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/register/login?error"));
    }


    @Test
    void shouldLoginStudentWithCorrectUsernameAndPassword() throws Exception {
        Student user = new Student(
                "test",
                passwordEncoder.encode("1234"),
                Role.STUDENT,
                "Test",
                "Testerson",
                new Group("AA-01")
        );

        when(userRepository.findByLogin("test"))
                .thenReturn(Optional.of(user));


        mvc.perform(formLogin("/register/login")
                        .user("username", user.getLogin())
                        .password("1234")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/welcome"));
    }


}
