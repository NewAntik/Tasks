package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.TeacherService;

@Controller
public class TeacherController {

    private final TeacherService teacherServ;
    
    public TeacherController(TeacherService teacherServ) {
        this.teacherServ = teacherServ;
    }

    @GetMapping("/teacher")
    public String getTeacherTable(Model model) throws SQLException {
        List<Teacher> teachers = teacherServ.findAll();
        model.addAttribute("teachers", teachers);
        
        return "teachers/list-all";
    }
}
