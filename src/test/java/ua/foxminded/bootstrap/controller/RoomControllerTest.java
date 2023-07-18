package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.service.RoomService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { RoomController.class })
class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    RoomService roomServ;

    @Test
    @WithMockUser(roles = "STAFF")
    void getRoomSchedule_shouldShowFreeAndBusyRooms() throws Exception {
        when(roomServ.findAll()).thenReturn(Arrays.asList(
                new Room("Lecture hall #1"),
                new Room("Lecture hall #2"),
                new Room("Lecture hall #3")
         ));
        
        mvc.perform(get("/rooms-schedule"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lecture hall #1")))
                .andExpect(content().string(containsString("Lecture hall #2")))
                .andExpect(content().string(containsString("Lecture hall #3")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getRooomTable_shouldShowListOfRooms() throws Exception {
        when(roomServ.findAll()).thenReturn(Arrays.asList(
               new Room("Lecture hall #1")
        ));
        mvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lecture hall #1")));
    }
}
