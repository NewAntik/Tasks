package ua.foxminded.bootstrap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.CourseService;
import ua.foxminded.bootstrap.service.UserService;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {CourseController.class})
@Import(WebSecurityConfiguration.class)
class CourseControllerTest {

    @MockBean
    UserService userService;
    
    @MockBean
    CourseService courseServ;
    
    @Autowired
    MockMvc mvc;
    
    @Test
    @WithMockUser(roles = "STAFF")
    void reassignTeacherToCourse_ShouldReassignTeacherToCourse() throws Exception {
        mvc.perform(post("/reassign-teacher")
                .param( "teacherId", "1")
                .param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "STAFF")
    void assignTeacherToCourse_ShouldAssignTeacherToCourse() throws Exception {
        mvc.perform(post("/assign-teacher")
                .param( "teacherId", "1")
                .param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "STAFF")
    void reassignGroupToCourse_ShouldReassignGroupToCourse() throws Exception {
        mvc.perform(post("/reassign-group")
                .param( "groupId", "1")
                .param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "STAFF")
    void assignGroupToCourse_ShouldAssignGroupToCourse() throws Exception {
        mvc.perform(post("/assign-group")
                .param( "groupId", "1")
                .param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCourse_ShouldUpdateCourse() throws Exception {
        mvc.perform(post("/update-course-by-id")
                .param( "id", "1")
                .param("newName", "New Name")
                .param("newDescription", "New Description"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void saveNewCourse_ShouldSaveNewCourse() throws Exception {
        mvc.perform(post("/save-course")
                .param( "name", "Biology")
                .param("description", "Biology Description"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteCourse_ShouldDeleteCourse() throws Exception {
        mvc.perform(post("/delete-course-by-id")
                .param( "id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }

    @Test
    void addNewCourse_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/add-course"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    void deleteCourse_ShouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/delete-course"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "http://localhost/register/login"));
    }
    
    @Test
    void shouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/courses"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/register/login"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getCourseTable_shouldShowListOfCourses() throws Exception {
        when(courseServ.findAll()).thenReturn(Arrays.asList(
                new Course(100L, "Math", "Math Description"),
                new Course(103L, "Music", "Music Description"),
                new Course(105L, "Physics", "Physics Description")
        ));
        mvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Physics")))
                .andExpect(content().string(containsString("Physics Description")))
                .andExpect(content().string(containsString("Music")))
                .andExpect(content().string(containsString("Music Description")))
                .andExpect(content().string(containsString("Math")))
                .andExpect(content().string(containsString("Math Description")));
    }
}
