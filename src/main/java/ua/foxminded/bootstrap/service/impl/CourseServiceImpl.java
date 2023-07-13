package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.CourseRepository;
import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(GroupRepository groupRepository, TeacherRepository teacherRepository,
            CourseRepository courseRep) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
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
        if (courseRepository.findByName(name) == null) {
            return Optional.of(courseRepository.save(new Course(name, description)));
        } else {
            throw new IllegalArgumentException("Course with this name \"" + name + "\" already exists!");
        }
    }

    @Override
    public void deleteCourseById(Long id) throws SQLException {
        courseRepository.delete(getCourseById(id));
    }

    @Override
    public void updateCourse(Long id, String newName, String newDescription) throws SQLException {
        courseRepository.save(new Course(getCourseById(id).getId(), newName, newDescription));
    }
    
    @Override
    public void assignTeacherToCourse(Long teacherId, Long courseId) {
        Teacher teacher = getTeacherById(teacherId);
        Course course = getCourseById(courseId);
        checkReletionTeacherCourse(teacherId, courseId);
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void assignGroupToCourse(Long groupId, Long courseId) {
        Group group = getGroupById(groupId);
        Course course = getCourseById(courseId);
        checkReletionGroupCourse(groupId, courseId);
        course.setGroup(group);
        courseRepository.save(course);
    }

    @Override
    public void reassignTeacherToCourse(Long teacherId, Long courseId) {
        Teacher teacher = getTeacherById(teacherId);
        Course course = getCourseById(courseId);
        checkAbsenceReletionTeacherCourse(teacherId, courseId);
        course.deleteTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void reassignGroupToCourse(Long groupId, Long courseId) {
        Group group = getGroupById(groupId);
        Course course = getCourseById(courseId);
        checkAbsenceReletionGroupCourse(groupId, courseId);
        course.deleteGroup(group);
        courseRepository.save(course);
    }
    
    private void checkAbsenceReletionTeacherCourse(Long teacherId, Long courseId) {
        if(courseRepository.findRelationTeacherCourse(teacherId, courseId).isEmpty()) {
             throw new IllegalArgumentException("This reletion teacher to course doesn't exist!");
        }
    }
    
    private void checkAbsenceReletionGroupCourse(Long groupId, Long courseId) {
        if(courseRepository.findRelationGroupCourse(groupId, courseId).isEmpty()) {
            throw new IllegalArgumentException("This reletion group to course doesn't exist!");
        }
    }

    private void checkReletionTeacherCourse(Long teacherId, Long courseId) {
        if(courseRepository.findRelationTeacherCourse(teacherId, courseId).isPresent()) {
             throw new IllegalArgumentException("This reletion teacher to course already exist!");
        }
    }

    private void checkReletionGroupCourse(Long groupId, Long courseId) {
        if(courseRepository.findRelationGroupCourse(groupId, courseId).isPresent()) {
            throw new IllegalArgumentException("This reletion group to course already exist!");
        }
    }

    private Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new IllegalArgumentException("Course with this id \"" + courseId + "\" doesn't exist!"));
    }

    private Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new IllegalArgumentException("Teacher with this id \"" + teacherId + "\" doesn't exist!"));
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new IllegalArgumentException("Group with this id \"" + groupId + "\" doesn't exist!"));
    }
}
