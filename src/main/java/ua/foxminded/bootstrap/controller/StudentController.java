package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Controller
public class StudentController {

    private StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping("/students")
    public String getStudentTable(Model model) throws SQLException {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        
        return "students/list-all";
    }
    
    @GetMapping("/welcome-student")
    public String getStudentWelcome(){
        return "students/welcome";
    }
}
