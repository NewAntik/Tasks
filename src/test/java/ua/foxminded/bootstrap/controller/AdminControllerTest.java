package ua.foxminded.bootstrap.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;


@WebMvcTest(controllers = {AdminController.class})
@Import(WebSecurityConfiguration.class)
class AdminControllerTest {

    @Test
    void test() {
        fail("Not yet implemented");
    }

}
