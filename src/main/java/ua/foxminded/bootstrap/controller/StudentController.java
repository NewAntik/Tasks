package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentServ;
    
    @GetMapping("/student")
    public String getStudentTable(Model model) throws SQLException {
        List<Student> students = studentServ.findAll();
        model.addAttribute("students", students);
        
        return "students/list-all";
    }
}
