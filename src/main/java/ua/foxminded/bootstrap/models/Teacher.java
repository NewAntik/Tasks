package ua.foxminded.bootstrap.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User  {

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.PERSIST)
    private Set<Course> specialization;

    public Teacher() {
        super();
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
