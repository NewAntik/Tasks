package ua.foxminded.bootstrap.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

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

    }

    public Teacher(String login, String passwordHash, String firstName, String lastName) {
        this(login, passwordHash, firstName, lastName, new HashSet<>());
    }

    public Teacher(String login, String passwordHash, String firstName, String lastName, Set<Course> specialization) {
        super(login, passwordHash, firstName, lastName);
        this.specialization = specialization;
    }

    public Set<Course> getSpecialization() {
        return specialization;
    }
}
