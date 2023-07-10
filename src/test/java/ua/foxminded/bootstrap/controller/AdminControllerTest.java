package ua.foxminded.bootstrap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.StudentService;
import ua.foxminded.bootstrap.service.TeacherService;
import ua.foxminded.bootstrap.service.UserService;

@WebMvcTest(controllers = {AdminController.class})
@Import(WebSecurityConfiguration.class)
class AdminControllerTest {
    
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;
    
    @MockBean
    StudentService studentService;
    
    @MockBean
    TeacherService teacherService;
    
    @Test
    void getAdminWelcome_ShouldRedirectToAdminWelcomePage() throws Exception {
        mvc.perform(get("/welcome-admin"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void shouldDenyAccessWithWrongRole() throws Exception {
        mvc.perform(get("/welcome-admin"))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void saveUser_shouldSaveNewUser() throws Exception {
        mvc.perform(post("/save-user")
                .param( "firstName", "Henry")
                .param("lastName", "Martin")
                .param("login", "login")
                .param("password", "1234")
                .param("role", "Staff"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void saveUser_shouldReturnErrorMessageIfUserExists() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String login = "login";
        String password = "1234";
        String role = "Staff";
        
        when(userService.addUser(login, password, firstName, lastName)).thenThrow(IllegalArgumentException.class);
        
        mvc.perform(post("/save-user")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("login", login)
                .param("password", password)
                .param("role", role))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Staff with this login already exists"));
    }
}
