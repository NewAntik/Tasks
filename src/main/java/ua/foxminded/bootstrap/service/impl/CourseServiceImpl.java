package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Course> addNewCourse(String name, String description) throws SQLException {
        if(courseRepository.findByName(name) == null) {
            return Optional.of(courseRepository.save(new Course(name, description)));
        } else {
            throw new IllegalArgumentException("Course with this name \"" + name + "\" already exists!");
        }
    }

    @Override
    public void deleteCourseById(Long id) throws SQLException {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseRepository.delete(course.get());
        } else {
            throw new IllegalArgumentException("Course with this id \"" + id + "\" doesn't exist!");
        }
    }

    @Override
    public void updateCourse(Long id, String newName, String newDescription) throws SQLException {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseRepository.save(new Course(id, newName, newDescription));
        } else {
            throw new IllegalArgumentException("Course with this id \"" + id + "\" doesn't exist!");
        }
    }
}
