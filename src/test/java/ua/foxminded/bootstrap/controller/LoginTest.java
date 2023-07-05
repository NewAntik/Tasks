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
import ua.foxminded.bootstrap.models.MyUserDetails;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.User;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.UserService;

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
        when(userService.loadUserByUsername("admin")).thenReturn(new MyUserDetails(user));

        mvc.perform(formLogin("/register/login").user("username", user.getLogin()).password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/welcome-admin"));
    }
    
    @Test
    void shouldLoginStudentWithCorrectUsernameAndPassword() throws Exception {
        Student user = new Student("student", passwordEncoder.encode("1234"), Role.STUDENT, "Student", "Student", new Group("AA-01"));
        when(userService.loadUserByUsername("student")).thenReturn(new MyUserDetails(user));

        mvc.perform(formLogin("/register/login").user("username", user.getLogin()).password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/welcome-student"));
    }

    @Test
    void shouldNotLoginUserWithBadPassword() throws Exception {
        mvc.perform(formLogin("/register/login").user("username", "test").password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/register/login?error"));
    }

    @Test
    void shouldNotLoginUserWithBadLogin() throws Exception {
        mvc.perform(formLogin("/register/login").user("username", "test").password("1234"))
           .andExpect(status().is3xxRedirection())
           .andExpect(header().string("Location", "/register/login?error"));
    }
}
