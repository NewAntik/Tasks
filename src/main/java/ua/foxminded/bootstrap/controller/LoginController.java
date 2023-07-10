package ua.foxminded.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("register/login")
    public String getLoginPage() {
        return "register/login";
    }
}
