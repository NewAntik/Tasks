package ua.foxminded.bootstrap.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("student")
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "group_ref")
    private Group group;

    public Student() {
        super();
    }

    public Student(String login, String passwordHash, String firstName, String lastName, Group group) {
        super(login, passwordHash, firstName, lastName);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
