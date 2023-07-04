package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.TimetableService;

@Controller
public class TimetableController {
    
    private final TimetableService timetableServ;
    
    public TimetableController(TimetableService timetableServ) {
        this.timetableServ = timetableServ;
    }

    @GetMapping("/timetables")
    public String getTimetableTable(Model model) throws SQLException {
        List<Timetable> timetables = timetableServ.findAll();
        model.addAttribute("timetables", timetables);
        
        return "timetables/list-all";
    }
}
