package ua.foxminded.bootstrap.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.bootstrap.dao.UserRepository;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;
import ua.foxminded.bootstrap.service.UserService;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecuritySharedConfiguration.class, UserRepository.class, UserService.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserServiceImplTest {

    @Autowired
    UserService userServ;

    @Test
    @Sql(scripts = {"/sql/clear_tables.sql"})
    void addUser_ShouldAddNewUser() {
        userServ.addUser("admin", "1234", "Admin", "admin", "admin");
        assertEquals("admin", userServ.findById(1L).get().getLogin());
    }

    @Test
    void addUser_ShouldThrewIllegalArgumentException() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            userServ.addUser("admin", "1234", "Admin", "admin", "admin");
        });
        assertNotNull(thrown.getMessage());
    }

}
