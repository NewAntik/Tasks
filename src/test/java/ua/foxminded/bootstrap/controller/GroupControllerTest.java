package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;
import ua.foxminded.bootstrap.service.UserService;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = { GroupController.class })
@Import(WebSecurityConfiguration.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    UserService userService;

    @MockBean
    GroupService groupServ;
    
    @Test
    void addNewGroup_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/add-group"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    void deleteGroup_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/delete-group"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    void updateGroup_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/update-group"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateGroup_ShouldUpdateGroup() throws Exception {
        mvc.perform(post("/update-group-by-id")
           .param("id", "1")
           .param("newName", "New Name"))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists("successMessage"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveNewGroup_ShouldSaveNewGroup() throws Exception {
        mvc.perform(post("/save-group").param("name", "AA-100"))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists("successMessage"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteGroup_ShouldDeleteGroup() throws Exception {
        mvc.perform(post("/delete-group-by-id")
           .param("id", "1"))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists("successMessage"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getGroupTable_shouldShowListOfGroups() throws Exception {
        when(groupServ.findAll()).thenReturn(Arrays.asList(
                
                new Group("AA-01"),
                new Group("AA-02"),
                new Group("AA-03")
        ));
        mvc.perform(get("/group"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AA-01")))
                .andExpect(content().string(containsString("AA-02")))
                .andExpect(content().string(containsString("AA-03")));
    }
}
