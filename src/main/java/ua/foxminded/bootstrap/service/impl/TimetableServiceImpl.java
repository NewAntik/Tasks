package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.time.Instant;
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

    private final TimetableRepository timetableRep;
    private final RoomRepository roomRep;
    private final GroupRepository groupRep;
    private final TeacherRepository teacherRep;
    private final CourseRepository courseRep;

    public TimetableServiceImpl(TimetableRepository timetableRepository, RoomRepository roomRepository,
            GroupRepository groupRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.timetableRep = timetableRepository;
        this.roomRep = roomRepository;
        this.groupRep = groupRepository;
        this.teacherRep = teacherRepository;
        this.courseRep = courseRepository;
    }

    @Override
    public Timetable add(Long roomId, Long groupId, Long teacherId, Long courseId, Instant date, Long lessonNum)
            throws SQLException {
        checkDataExistins(roomId, groupId, teacherId, courseId);

        return timetableRep.save(new Timetable(roomRep.findById(roomId).get(), groupRep.findById(groupId).get(),
                teacherRep.findById(teacherId).get(), courseRep.findById(courseId).get(), date, lessonNum));
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRep.findAll();
    }

    @Override
    public List<Timetable> saveAll(List<Timetable> timetables) throws SQLException {
        return timetableRep.saveAll(timetables);
    }

    @Override
    public void delete(Long id) throws SQLException {
        timetableRep.deleteById(id);
    }
    
    private void checkDataExistins(Long roomId, Long groupId, Long teacherId, Long courseId) {
        roomRep.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room with this id " + roomId + " doesn't exist!"));
        groupRep.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group with this id " + groupId + " doesn't exist!"));
        teacherRep.findById(teacherId).orElseThrow(
                () -> new IllegalArgumentException("Teacher with this id " + teacherId + " doesn't exist!"));
        courseRep.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with this id " + courseId + " doesn't exist!"));
    }
}
