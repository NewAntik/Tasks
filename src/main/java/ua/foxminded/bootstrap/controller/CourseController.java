package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;

@Controller
public class CourseController {
    private static final String ERROR = "errorMessage";
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String SUCCESS = "Success!";
   
    private final CourseService courseService;
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @PostMapping("/reassign-teacher")
    public String reassignTeacherToCourse(@RequestParam("teacherId") Long teacherId, @RequestParam("courseId") Long courseId, Model model) throws SQLException {
        try {
            courseService.reassignTeacherToCourse(teacherId, courseId);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/reassign-teacher";
    }
    
    @PostMapping("/reassign-group")
    public String reassignGroupToCourse(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId, Model model) throws SQLException {
        try {
            courseService.reassignGroupToCourse(groupId, courseId);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/reassign-group";
    }
    
    @PostMapping("/assign-teacher")
    public String assignTeacherToCourse(@RequestParam("teacherId") Long teacherId, @RequestParam("courseId") Long courseId, Model model) throws SQLException {
        try {
            courseService.assignTeacherToCourse(teacherId, courseId);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/assign-teacher";
    }
    
    @PostMapping("/assign-group")
    public String assignGroupToCourse(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId, Model model) throws SQLException {
        try {
            courseService.assignGroupToCourse(groupId, courseId);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/assign-group";
    }
    
    @GetMapping("/assign-teacher-page")
    public String getAssignTeacherPage() throws SQLException {
        return "courses/assign-teacher";
    }
    
    @GetMapping("/assign-group-page")
    public String getAssignGroupPage() throws SQLException {
        return "courses/assign-group";
    }
    
    @GetMapping("/reassign-teacher-page")
    public String getReassignTeacherPage() throws SQLException {
        return "courses/reassign-teacher";
    }
    
    @GetMapping("/reassign-group-page")
    public String getReassignGroupPage() throws SQLException {
        return "courses/reassign-group";
    }
    
    @GetMapping("/courses")
    public String getCourseTable(Model model) throws SQLException {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        
        return "courses/list-all";
    }
    
    @GetMapping("/add-course")
    public String addNewCourse() throws SQLException {
        return "courses/add";
    }
    
    @GetMapping("/delete-course")
    public String deleteCourse() throws SQLException {
        return "courses/delete";
    }
    
    @GetMapping("/update-course")
    public String updateCourse() throws SQLException {
        return "courses/update";
    }
    
    @PostMapping("/update-course-by-id")
    public String updateCourse(@RequestParam("id") Long id, @RequestParam("newName") String newName, @RequestParam("newDescription") String newDescription, Model model) throws SQLException {
        try {
            courseService.updateCourse(id, newName, newDescription);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/delete";
    }
    
    @PostMapping("/delete-course-by-id")
    public String deleteCourse(@RequestParam("id") Long id, Model model) throws SQLException {
        try {
            courseService.deleteCourseById(id);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/delete";
    }
    
    @PostMapping("/save-course")
    public String saveNewCourse(@RequestParam("name") String name, @RequestParam("description") String description, Model model) throws SQLException {
        try {
            courseService.addNewCourse(name, description);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "courses/add";
    }
    
    
}
