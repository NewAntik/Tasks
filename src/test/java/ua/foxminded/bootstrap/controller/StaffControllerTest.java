package ua.foxminded.bootstrap.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {StaffController.class})
class StaffControllerTest {

    @Autowired
    MockMvc mvc;
    
    @Test
    void getStaffPage_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/welcome-staff"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void shouldDenyAccessWithWrongRole() throws Exception {
        mvc.perform(get("/welcome-staff"))
                .andExpect(status().isForbidden());
    }

}
