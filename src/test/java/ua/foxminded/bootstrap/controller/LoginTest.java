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
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.User;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.UserService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {LoginController.class},includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecuritySharedConfiguration.class}))
@Import(WebSecurityConfiguration.class)
class LoginTest {
    
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void shouldLoginUserWithCorrectUsernameAndPassword() throws Exception {
        User user = new User("admin", passwordEncoder.encode("1234"), Role.ADMIN, "Main", "Main");
        when(userService.findByLogin("admin")).thenReturn(Optional.of(user));

        mvc.perform(formLogin("/register/login").user("username", user.getLogin()).password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/welcome"));
    }
    
    @Test
    void shouldLoginStudentWithCorrectUsernameAndPassword() throws Exception {
        Student user = new Student("student", passwordEncoder.encode("1234"), Role.STUDENT, "Student", "Student", new Group("AA-01"));
        when(userService.findByLogin("student")).thenReturn(Optional.of(user));

        mvc.perform(formLogin("/register/login").user("username", user.getLogin()).password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/welcome"));
    }

    @Test
    void shouldNotLoginUserWithBadPassword() throws Exception {
        User user = new User("test", passwordEncoder.encode("secret"), Role.ADMIN, "Test", "Testerson");
        when(userService.findByLogin("test")).thenReturn(Optional.of(user));

        mvc.perform(formLogin("/register/login").user("username", user.getLogin()).password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/register/login?error"));
    }

    @Test
    void shouldNotLoginUserWithBadLogin() throws Exception {
        when(userService.findByLogin(anyString())).thenReturn(Optional.empty());

        mvc.perform(formLogin("/register/login").user("username", "test").password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/register/login?error"));
    }
}
