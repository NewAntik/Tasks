package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.CourseRepository;
import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.RoomRepository;
import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.dao.TimetableRepository;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.TimetableService;

@Service
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final RoomRepository roomRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TimetableServiceImpl(TimetableRepository timetableRepository, RoomRepository roomRepository,
            GroupRepository groupRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.timetableRepository = timetableRepository;
        this.roomRepository = roomRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Timetable add(Long roomId, Long groupId, Long teacherId, Long courseId, LocalDate date, Long lessonNum)
            throws SQLException {
        checkDataExist(roomId, groupId, teacherId, courseId);
        checkReletions(roomId, groupId, teacherId, courseId, date, lessonNum);

        return timetableRepository.save(new Timetable(roomRepository.findById(roomId).get(), groupRepository.findById(groupId).get(),
                teacherRepository.findById(teacherId).get(), courseRepository.findById(courseId).get(), date, lessonNum));
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public List<Timetable> saveAll(List<Timetable> timetables) throws SQLException {
        return timetableRepository.saveAll(timetables);
    }

    @Override
    public void delete(Long id) throws SQLException {
        checkById(id);
        timetableRepository.deleteById(id);        
    }
    

    @Override
    public void update(Long scheduleId, Long roomId, Long groupId, Long teacherId, Long courseId, LocalDate date, Long lessonNum) throws SQLException {
        Timetable tableFromDb = checkById(scheduleId);
        checkDataExist(roomId, groupId, teacherId, courseId);
        checkReletions(roomId, groupId, teacherId, courseId, date, lessonNum);
        
        tableFromDb.setRoom(roomRepository.findById(roomId).get());
        tableFromDb.setGroup(groupRepository.findById(groupId).get());
        tableFromDb.setTeacher(teacherRepository.findById(teacherId).get());
        tableFromDb.setCourse(courseRepository.findById(courseId).get());
        tableFromDb.setDate(date);
        tableFromDb.setLessonNum(lessonNum);

        timetableRepository.save(tableFromDb);
    }
    
    private void checkReletions(Long roomId, Long groupId, Long teacherId, Long courseId, LocalDate date, Long lessonNum) {
        timetableRepository.findByCourseLessonData(courseId, lessonNum, date).ifPresent(timetable -> {throw new IllegalArgumentException("Reletion course with this lessons and date already exist!");});
        timetableRepository.findByTeacherLessonData(teacherId, lessonNum, date).ifPresent(timetable -> {throw new IllegalArgumentException("Reletion teacher with this lessons and date already exist!");});
        timetableRepository.findByRoomLessonData(roomId, lessonNum, date).ifPresent(timetable -> {throw new IllegalArgumentException("Reletion room with this lessons and date already exist!");});
        timetableRepository.findByGroupLessonData(groupId, lessonNum, date).ifPresent(timetable -> {throw new IllegalArgumentException("Reletion group with this lessons and date already exist!");});

    }

    private Timetable checkById(Long scheduleId) throws SQLException {
        return timetableRepository.findById(scheduleId)
        .orElseThrow(() -> new IllegalArgumentException("Schedule with this id " + scheduleId + " doesn't exist!"));
    }
    
    private void checkDataExist(Long roomId, Long groupId, Long teacherId, Long courseId) {
        roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room with this id " + roomId + " doesn't exist!"));
        groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group with this id " + groupId + " doesn't exist!"));
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with this id " + teacherId + " doesn't exist!"));
        courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with this id " + courseId + " doesn't exist!"));
    }

    @Override
    public List<Timetable> findByStudentId(Long studentId) {
        List<Timetable> timetables = timetableRepository.findByStudentId(studentId);
        if(timetables.isEmpty()) {
            throw new IllegalArgumentException("Any timetable with this student id: " + studentId + " doesn't exist!");
        } else {
            return timetables;
        }
    }

    @Override
    public List<Timetable> findByTeacherId(Long teacherId) {
        List<Timetable> timetables = timetableRepository.findByTeacherId(teacherId);
        if(timetables.isEmpty()) {
            throw new IllegalArgumentException("Any timetable with this teacher id: " + teacherId + " doesn't exist!");
        } else {
            return timetables;
        }
    }
}
