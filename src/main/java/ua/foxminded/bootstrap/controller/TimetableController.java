package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.controller.util.Messages;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.TimetableService;

@Controller
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableServ) {
        this.timetableService = timetableServ;
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
    public String saveDeleteSchedule(@RequestParam("scheduleId") Long scheduleId, Model model)
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
    public String getTimetableTable(Model model) throws SQLException {
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
