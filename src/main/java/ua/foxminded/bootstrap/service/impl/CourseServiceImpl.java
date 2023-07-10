package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.CourseRepository;
import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRep) {
        this.courseRepository = courseRep;
    }
     
    @Override
    public List<Course> findAll() throws SQLException {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> saveAll(List<Course> courses) throws SQLException {
        return courseRepository.saveAll(courses);
    }
}
