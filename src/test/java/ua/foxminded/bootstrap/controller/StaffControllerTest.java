package ua.foxminded.bootstrap.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {StaffController.class})
@Import(WebSecurityConfiguration.class)
class StaffControllerTest {

    @Autowired
    MockMvc mvc;
    
    @MockBean
    UserService userService;
    
    @Test
    void getStaffWelcome_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/welcome-staff"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void getStaffWelcome_ShouldDenyAccessWithWrongRole() throws Exception {
        mvc.perform(get("/welcome-staff"))
                .andExpect(status().isForbidden());
    }
}
