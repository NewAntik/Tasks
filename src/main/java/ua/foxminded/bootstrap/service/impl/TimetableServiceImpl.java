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
        checkDataExistins(roomId, groupId, teacherId, courseId);

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
        timetableRepository.deleteById(id);
    }
    
    private void checkDataExistins(Long roomId, Long groupId, Long teacherId, Long courseId) {
        roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room with this id " + roomId + " doesn't exist!"));
        groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group with this id " + groupId + " doesn't exist!"));
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with this id " + teacherId + " doesn't exist!"));
        courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with this id " + courseId + " doesn't exist!"));
    }
}
