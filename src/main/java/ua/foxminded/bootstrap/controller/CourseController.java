package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;

@Controller
public class CourseController {
   
    private final CourseService courseServ;
    
    public CourseController(CourseService courseService) {
        this.courseServ = courseService;
    }
    
    
    @GetMapping("/course")
    public String getCourseTable(Model model) throws SQLException {
        List<Course> courses = courseServ.findAll();
        model.addAttribute("courses", courses);
        
        return "courses/list-all";
    }
}
