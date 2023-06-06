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

    @Column(name = "date")
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

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getLessonNum() {
        return lessonNum;
    }

    public Course getCourses() {
        return course;
    }

    public Teacher getTeachers() {
        return teacher;
    }

    public Room getRooms() {
        return room;
    }

    public Group getGroups() {
        return group;
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
