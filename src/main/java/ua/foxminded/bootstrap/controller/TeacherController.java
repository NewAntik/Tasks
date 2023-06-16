package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.TeacherService;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherServ;
    
    @GetMapping("/teacher")
    public String getTeacherTable(Model model) throws SQLException {
        List<Teacher> teachers = teacherServ.findAll();
        model.addAttribute("teachers", teachers);
        
        return "teachers/list-all";
    }
}
