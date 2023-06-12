package ua.foxminded.bootstrap.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    @ManyToMany(mappedBy = "groups", cascade = CascadeType.PERSIST)
    private Set<Course> courses;

    public Group() {
    }

    public Group(String name) {
        this(null, name, new HashSet<>(), new HashSet<>());
    }

    public Group(Long id, String name, Set<Student> students, Set<Course> courses) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
