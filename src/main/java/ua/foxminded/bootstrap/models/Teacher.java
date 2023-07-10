package ua.foxminded.bootstrap.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import ua.foxminded.bootstrap.models.utils.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.PERSIST)
    private Set<Course> specialization;

    public Teacher() {
        super();
    }

    public Teacher(Long id, String login, String passwordHash, String firstName, String lastName) {
        this(login, passwordHash, firstName, lastName, new HashSet<>());
        this.id = id;
        this.role = Role.TEACHER;

    }

    public Teacher(String login, String passwordHash, String firstName, String lastName) {
        this(login, passwordHash, firstName, lastName, new HashSet<>());
        this.role = Role.TEACHER;
    }

    public Teacher(String login, String passwordHash, String firstName, String lastName, Set<Course> specialization) {
        super(login, passwordHash, firstName, lastName);
        this.specialization = specialization;
        this.role = Role.TEACHER;
    }

    public Set<Course> getSpecialization() {
        return specialization;
    }
}
