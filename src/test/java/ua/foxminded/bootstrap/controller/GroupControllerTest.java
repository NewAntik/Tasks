package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { GroupController.class })
class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GroupService groupServ;

    @Test
    void getGroupTable_shouldShowListOfGroups() throws Exception {
        when(groupServ.findAll()).thenReturn(Arrays.asList(
                
                new Group("AA-01"),
                new Group("AA-02"),
                new Group("AA-03")
        ));
        mvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AA-01")))
                .andExpect(content().string(containsString("AA-02")))
                .andExpect(content().string(containsString("AA-03")));
    }
}
