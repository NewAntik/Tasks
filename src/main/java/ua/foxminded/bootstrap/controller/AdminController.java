package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.service.StudentService;
import ua.foxminded.bootstrap.service.TeacherService;
import ua.foxminded.bootstrap.service.UserService;

@Controller
public class AdminController {
    private static final String ERROR = "errorMessage";
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String SUCCESS = "Success!";
    
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    public AdminController(UserService userService, StudentService studentService, TeacherService teacherService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/welcome-admin")
    public String getAdminWelcome() {
        return "admin/welcome";
    }

    @GetMapping("/create-user")
    public String createNewUser() {
        return "/admin/create-user";
    }

    @PostMapping("/save-user")
    public String saveNewUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("login") String login, @RequestParam("password") String password,
            @RequestParam("role") String role, Long groupId, Model model) throws SQLException {

        if (role.equals(Role.STUDENT.getRole())) {
            try {
                studentService.addNewStudent(login, password, firstName, lastName, groupId);
                model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
            } catch (IllegalArgumentException | NullPointerException e) {
                model.addAttribute(ERROR, e.getMessage());
            }
        } else if (role.equals(Role.TEACHER.getRole())) {
            try {
                teacherService.addNewTeacher(login, password, firstName, lastName);
                model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
            } catch (IllegalArgumentException e) {
                model.addAttribute(ERROR, "Teacher with this login already exists");
            }
        } else if(role.equals(Role.STAFF.getRole())) {
            try {
                userService.addUser(login, password, firstName, lastName);
                model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
            } catch (IllegalArgumentException e) {
                model.addAttribute(ERROR, "Staff with this login already exists");
            }
        }
        
        return "admin/create-user";
    }
}
