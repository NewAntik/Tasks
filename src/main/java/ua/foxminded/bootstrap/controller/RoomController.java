package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.service.RoomService;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class RoomController {

    private RoomService roomServ;

    public RoomController(RoomService roomService) {
        this.roomServ = roomService;
    }

    @GetMapping("/rooms")
    public String getRooomTable(Model model) throws SQLException {
        List<Room> rooms = roomServ.findAll();
        model.addAttribute("rooms", rooms);

        return "rooms/list-all";
    }

    private List<Boolean> getBooleanList(Integer num) {
        List<Boolean> bool1 = new ArrayList<>();
        while (bool1.size() < num) {
            bool1.add(ThreadLocalRandom.current().nextBoolean());
        }
        
        return bool1;
    }

    @GetMapping("/rooms-schedule")
    String getRoomSchedule(Model model) throws SQLException {
        List<Room> rooms = roomServ.findAll();
        model.addAttribute("occupations", getOccupation(rooms));
        model.addAttribute("rooms", rooms);

        return "rooms/schedule";
    }
    
    private Map<Integer, List<Boolean>> getOccupation(List<Room> rooms){
        List<Integer> lessonNum = IntStream.rangeClosed(1, 28).boxed().toList();
        Map<Integer, List<Boolean>> occupations = lessonNum.stream().collect(Collectors.toMap(i -> i, i -> getBooleanList(rooms.size())));
        
        return occupations;
    }
}
