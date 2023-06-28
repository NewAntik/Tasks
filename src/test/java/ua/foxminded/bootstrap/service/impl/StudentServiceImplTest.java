package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;


import ua.foxminded.bootstrap.dao.StudentRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfiguration.class, StudentRepository.class, StudentServiceImpl.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class StudentServiceImplTest {
    
    @Autowired
    StudentServiceImpl studentServ;
    
    @Test
    void add_ShouldReturnStudentByStudentFirstName() {
        
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            studentServ.add("gasd", "1234", "Albert", "Romadowsky", 23L);     
        });
        assertNotNull(thrown.getMessage());
    }
}
