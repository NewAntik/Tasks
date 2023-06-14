package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.utils.BasicFaker;
import ua.foxminded.bootstrap.utils.Loader;

@Service
public class DataGeneratorService {
    
    private final Random random;
    
    public DataGeneratorService() {
        this.random = new Random();
    }

    public  List<Group> getGroupList() {
        ArrayList<Group> groups = new ArrayList<>();

        while (groups.size() < 10) {
            String randomName = BasicFaker.getNameForGroup();
            Group newGroup = new Group(randomName);
            groups.add(newGroup);
        }

        return groups;
    }

    public List<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        List<String> firstNames = BasicFaker.getFirstNameList();
        List<String> lastNames = BasicFaker.getLastNameList();
        List<String> logins = BasicFaker.getLoginList();
        List<String> passwords = BasicFaker.getPasswordList();

        while (students.size() < 200) {
            String firstName = getRandomValue(firstNames);
            String lastName = getRandomValue(lastNames);
            String login = getRandomValue(logins);
            String password = getRandomValue(passwords);

            students.add(new Student(login, password, firstName, lastName, null));
        }

        return students;
    }

    public List<Course> getCourseList() throws SQLException {
        List<Course> courses = new ArrayList<>();

        String courseNames = Loader.getFileLikeString("/courseNames.txt");
        String description = Loader.getFileLikeString("/descriptionForCourse.txt");
        String[] names = courseNames.split("\n");

        for (String name : names) {
            Course newCourse = new Course(name, description);
            courses.add(newCourse);
        }

        return courses;
    }
    
    public List<Student> assingStudentToGroup(List<Group> groups, List<Student> students) {
        Collections.shuffle(students);

        return students.stream().map(student -> new Student(student.getLogin(), student.getPasswordHash(), student.getFirstName(), student.getLastName(),
                getRandomGroup(groups))).collect(Collectors.toList());
    }
    
    private Group getRandomGroup(List<Group> groups) {
        return groups.get(random.nextInt(groups.size()));
    }

    private String getRandomValue(List<String> list) {
        int index = random.nextInt(list.size());

        return list.get(index);
    }
}
