package ua.foxminded.bootstrap.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "timetables")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lesson_num")
    private Long lessonNum;

    @Column(name = "date_ref")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "course_ref")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_ref")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "room_ref")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "group_ref")
    private Group group;

    public Timetable() {

    }

    public Timetable(Long lessonNum) {
        this.lessonNum = lessonNum;
    }
    
    public Timetable(Long id, Room room, Group group, Teacher teacher, Course course, LocalDate date, Long lessonNum) {
        this.id = id;
        this.room = room;
        this.group = group;
        this.teacher = teacher;
        this.course = course;
        this.date = date;
        this.lessonNum = lessonNum;
    }

    public Timetable(Room room, Group group, Teacher teacher, Course course, LocalDate date, Long lessonNum) {
        this.room = room;
        this.group = group;
        this.teacher = teacher;
        this.course = course;
        this.date = date;
        this.lessonNum = lessonNum;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getLessonNum() {
        return lessonNum;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Room getRoom() {
        return room;
    }

    public Group getGroup() {
        return group;
    }

    public void setLessonNum(Long lessonNum) {
        this.lessonNum = lessonNum;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Timetable [id=" + id + ", lessonNum=" + lessonNum + ", date=" + date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessonNum == null) ? 0 : lessonNum.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Timetable other = (Timetable) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessonNum == null) {
            if (other.lessonNum != null)
                return false;
        } else if (!lessonNum.equals(other.lessonNum))
            return false;
        return true;
    }
}
