package ua.foxminded.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {

    @GetMapping("/welcome-staff")
    public String getStaffWelcome(){
        return "staff/welcome";
    } 
}
