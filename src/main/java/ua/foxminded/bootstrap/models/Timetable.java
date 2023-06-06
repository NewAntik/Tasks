package ua.foxminded.bootstrap.models;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "timetables")
public class Timetable implements HasId<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "lesson_num")
    private Long lessonNum;
    
    @Column(name = "date")
    private Instant date;
    
    @OneToMany(mappedBy = "timetable")
    @JoinColumn(name = "course_ref")
    private Course course;
    
    @OneToMany(mappedBy = "timetable")
    @JoinColumn(name = "teacher_ref")
    private Teacher teacher;
    
    @OneToMany(mappedBy = "timetable")
    @JoinColumn(name = "room_ref")
    private Room room;
    
    @OneToMany(mappedBy = "timetable")
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

    public Instant getDate() {
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
