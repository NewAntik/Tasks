package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.controller.util.Messages;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.StudentService;
import ua.foxminded.bootstrap.service.TeacherService;
import ua.foxminded.bootstrap.service.TimetableService;

@Controller
public class TimetableController {

    private final TimetableService timetableService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public TimetableController(TimetableService timetableServ, StudentService studentService, TeacherService teacherService) {
        this.timetableService = timetableServ;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }
    
    @GetMapping("/student-schedule/{studentId}")
    public String getStudentSchedules(@PathVariable Long studentId, Model model) {
        try {
            Student student = studentService.findById(studentId);
            List<Timetable> schedules = timetableService.findByStudentId(studentId);
            model.addAttribute("studentFirstName", student.getFirstName());
            model.addAttribute("schedules", schedules);
            
            return "timetables/student-relation";
        } catch (IllegalArgumentException e) {
            model.addAttribute(Messages.ERROR.getValue(), e.getMessage());
        }
        
        return "students/list-all";
    } 
    
    @GetMapping("/teacher-schedule/{teacherId}")
    public String getTeacherSchedules(@PathVariable Long teacherId, Model model) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Timetable> schedules = timetableService.findByTeacherId(teacherId);
            model.addAttribute("teacherFirstName", teacher.getFirstName());
            model.addAttribute("schedules", schedules);
            
            return "timetables/teacher-relation";
        } catch (IllegalArgumentException e) {
            model.addAttribute(Messages.ERROR.getValue(), e.getMessage());
        }
        
        return "teachers/list-all";
    }      
    
    @PostMapping("/update-exist-schedule")
    public String updateSchedule(@RequestParam("scheduleId") Long scheduleId, @RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId,
            @RequestParam("teacherId") Long teacherId, @RequestParam("roomId") Long roomId,
            @RequestParam("lessonNum") Long lessonNum, @RequestParam("date") LocalDate date, Model model)
            throws SQLException {
        try {
            timetableService.update(scheduleId , roomId, groupId, teacherId, courseId, date, lessonNum);
            model.addAttribute(Messages.SUCCESS_MESSAGE.getValue(), Messages.SUCCESS.getValue());
        } catch (IllegalArgumentException e) {
            model.addAttribute(Messages.ERROR.getValue(), e.getMessage());
        }

        return "timetables/update";
    }

    @PostMapping("/create-new-schedule")
    public String saveNewSchedule(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId,
            @RequestParam("teacherId") Long teacherId, @RequestParam("roomId") Long roomId,
            @RequestParam("lessonNum") Long lessonNum, @RequestParam("date") LocalDate date, Model model)
            throws SQLException {
        try {
            timetableService.add(roomId, groupId, teacherId, courseId, date, lessonNum);
            model.addAttribute(Messages.SUCCESS_MESSAGE.getValue(), Messages.SUCCESS.getValue());
        } catch (IllegalArgumentException e) {
            model.addAttribute(Messages.ERROR.getValue(), e.getMessage());
        }

        return "timetables/create";
    }
    
    @PostMapping("/delete-exist-schedule")
    public String deleteSchedule(@RequestParam("scheduleId") Long scheduleId, Model model)
            throws SQLException {
        try {
            timetableService.delete(scheduleId);
            model.addAttribute(Messages.SUCCESS_MESSAGE.getValue(), Messages.SUCCESS.getValue());
        } catch (IllegalArgumentException e) {
            model.addAttribute(Messages.ERROR.getValue(), e.getMessage());
        }

        return "timetables/delete";
    }
    

    @GetMapping("/timetables")
    public String getTimetableTable(Model model) {
        List<Timetable> timetables = timetableService.findAll();
        model.addAttribute("timetables", timetables);

        return "timetables/list-all";
    }

    @GetMapping("/create-schedule")
    public String getCreateSchedulePage() throws SQLException {
        return "timetables/create";
    }

    @GetMapping("/delete-schedule")
    public String getDeleteSchedulePage() throws SQLException {
        return "timetables/delete";
    }

    @GetMapping("/update-schedule")
    public String getUpdateSchedulePage() throws SQLException {
        return "timetables/update";
    }

}
