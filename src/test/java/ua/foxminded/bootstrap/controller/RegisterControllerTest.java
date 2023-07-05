package ua.foxminded.bootstrap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.UserService;

@WebMvcTest(controllers = {RegisterController.class})
@Import(WebSecurityConfiguration.class)
class RegisterControllerTest {

    @MockBean
    UserService userService;
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void testLogoutEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register/logout"));
    }

}
