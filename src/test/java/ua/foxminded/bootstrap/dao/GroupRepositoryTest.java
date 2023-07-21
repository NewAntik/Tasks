package ua.foxminded.bootstrap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {GroupRepository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;
    
    @Test
    void checkRelationStudentGroup_ShouldFindGroupWithSpecifiedStudent() {
        Optional<Group> group = groupRepository.checkRelationStudentGroup(2L, 1L);
        assertTrue( group.get().getStudents().contains(new Student(2L, "student1", "1234", "Jon", "Cover", group.get())));
        assertEquals("AA-01", group.get().getName());
    }
    
    @Test
    void findByStudentNum_ShouldFindListOfGroupWithSpecifiedAmountOfStudentOrLess() {
        List<Group> groups = groupRepository.findByStudentNum(1L);
        assertEquals(2L, groups.get(0).getId());
        assertEquals("AA-02", groups.get(0).getName());
        assertEquals(5, groups.size());
    }
    
    @Test
    void findByName_ShouldFindGroupByName() {
        Group aa01 = groupRepository.findByName("AA-01");
        assertEquals(1L, aa01.getId());
        assertEquals("AA-01", aa01.getName());
    }
}
