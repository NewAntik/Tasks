package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.TeacherService;

@Controller
public class TeacherController {
    private static final String ERROR = "errorMessage";

    private final TeacherService teacherServ;
    
    public TeacherController(TeacherService teacherServ) {
        this.teacherServ = teacherServ;
    }

    @GetMapping("/teachers")
    public String getTeacherTable(Model model) throws SQLException {
        List<Teacher> teachers = teacherServ.findAll();
        model.addAttribute("teachers", teachers);
        
        return "teachers/list-all";
    }
    
    @GetMapping("/welcome-teacher")
    public String getTeacherWelcome() throws SQLException {
        return "teachers/welcome";
    }
    
    @GetMapping("/specializations/{teacherId}")
    public String getTeacherTable(@PathVariable Long teacherId, Model model) throws SQLException {
        Teacher teacher = teacherServ.findById(teacherId);
        try {
            if(teacher.getSpecialization().isEmpty()) {
                throw new IllegalArgumentException("This teacher doesn't have any specialization!");
            } else {
                model.addAttribute("teacherFirstName", teacher.getFirstName());
                model.addAttribute("specializations", teacher.getSpecialization());
                
                return "teachers/its-courses";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }

        return "teachers/list-all";
    }
}

