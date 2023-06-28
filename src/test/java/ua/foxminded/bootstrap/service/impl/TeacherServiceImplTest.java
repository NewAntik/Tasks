package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfiguration.class, TeacherRepository.class, TeacherServiceImpl.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TeacherServiceImplTest {
    
    @Autowired
    TeacherServiceImpl teacherServ;
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionSpecializationDoesntExist() {
        
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            teacherServ.findTeachersBySpecialization("Philosophy");     
        });
        assertNotNull(thrown.getMessage());
    }
}
