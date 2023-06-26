package ua.foxminded.bootstrap.service;

import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.models.MyUserDetails;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.Teacher;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    private final StudentService studentServ;

    private final TeacherService teacherServ;
    
    public MyUserDetailsService(StudentService studentService, TeacherService teacherService) {
        this.studentServ = studentService;
        this.teacherServ = teacherService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Student student = studentServ.findByname(username);
            if(student == null) {
                return new MyUserDetails(student);
            }
            
            Teacher teacher = teacherServ.findByName(username);
            if(teacher == null) {
                
                return new MyUserDetails(teacher);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
