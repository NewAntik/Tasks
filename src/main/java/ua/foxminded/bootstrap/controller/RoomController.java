package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.service.RoomService;

@Controller
public class RoomController {
    
    @Autowired
    private RoomService roomServ;
    
    @GetMapping("/room")
    public String getRooomTable(Model model) throws SQLException {
        List<Room> rooms = roomServ.findAll();
        model.addAttribute("rooms", rooms);
        
        return "rooms/list-all";
    }
}
