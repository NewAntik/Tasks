package ua.foxminded.bootstrap.models;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teachers_courses", joinColumns = @JoinColumn(name = "course_ref"), inverseJoinColumns = @JoinColumn(name = "teacher_ref"))
    private Set<Teacher> teachers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "courses_groups", joinColumns = @JoinColumn(name = "course_ref"), inverseJoinColumns = @JoinColumn(name = "group_ref"))
    private Set<Group> groups;

    public Course() {

    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Course(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teachers = new HashSet<>();
        this.groups = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void setGroup(Group group) {
        this.groups.add(group);
    }

    public void deleteTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public void deleteGroup(Group group) {
        this.groups.remove(group);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Course other = (Course) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
