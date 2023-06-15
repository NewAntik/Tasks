package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.CourseService;
import ua.foxminded.bootstrap.service.GroupService;
import ua.foxminded.bootstrap.service.RoomService;
import ua.foxminded.bootstrap.service.StudentService;
import ua.foxminded.bootstrap.service.TeacherService;
import ua.foxminded.bootstrap.service.TimetableService;

@Controller
public class IndexController {
    
    @Autowired
    private StudentService studentServ;
    
    @Autowired
    private TeacherService teacherServ;
    
    @Autowired
    private CourseService courseServ;
    
    @Autowired
    private GroupService groupServ;
    
    @Autowired
    private RoomService roomServ;
    
    @Autowired
    private TimetableService timetableServ;
    
    
    @GetMapping("/index")
    public String getWelcomePage() {
        return "index";
    }
    
    @GetMapping("/student")
    public String getStudentTable(Model model) throws SQLException {
        List<Student> students = studentServ.findAll();
        model.addAttribute("students", students);
        
        return "students";
    }
    
    @GetMapping("/teacher")
    public String getTeacherTable(Model model) throws SQLException {
        List<Teacher> teachers = teacherServ.findAll();
        model.addAttribute("teachers", teachers);
        
        return "teachers";
    }
    
    @GetMapping("/course")
    public String getCourseTable(Model model) throws SQLException {
        List<Course> courses = courseServ.findAll();
        model.addAttribute("courses", courses);
        
        return "courses";
    }
    
    @GetMapping("/group")
    public String getGroupTable(Model model) throws SQLException {
        List<Group> groups = groupServ.findAll();
        model.addAttribute("groups", groups);
        
        return "groups";
    }
    
    @GetMapping("/room")
    public String getRooomTable(Model model) throws SQLException {
        List<Room> rooms = roomServ.findAll();
        model.addAttribute("rooms", rooms);
        
        return "rooms";
    }
    
    @GetMapping("/timetable")
    public String getTimetableTable(Model model) {
        List<Timetable> timetables = timetableServ.findAll();
        model.addAttribute("timetables", timetables);
        
        return "timetables";
    }
}
