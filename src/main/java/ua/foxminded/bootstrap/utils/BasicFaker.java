package ua.foxminded.bootstrap.utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class BasicFaker {
    private static final String HYPHEN = "-";

    private static Faker faker = new Faker();

    private BasicFaker() throws Exception {
        throw new Exception("This BasicFaker cannot be created!");
    }
    
    public static List<String> getPasswordList() {
        List<String> passwords = new ArrayList<>();

        while (passwords.size() < 20) {
            passwords.add(faker.internet().password());
        }

        return passwords;
    }

    public static List<String> getLoginList() {
        List<String> logins = new ArrayList<>();

        while (logins.size() < 20) {
            logins.add(faker.name().username());
        }

        return logins;
    }

    public static String getNameForGroup() {
        int firstNumber = faker.number().randomDigitNotZero();
        int secondNumber = faker.number().randomDigitNotZero();
        String twoChar = RandomStringUtils.randomAlphabetic(2);

        return String.format("%s%s%d%d", twoChar, HYPHEN, firstNumber, secondNumber);
    }

    public static List<String> getFirstNameList() {
        List<String> names = new ArrayList<>();

        while (names.size() < 20) {
            names.add(faker.name().firstName());
        }

        return names;
    }

    public static List<String> getLastNameList() {
        List<String> names = new ArrayList<>();

        while (names.size() < 20) {
            names.add(faker.name().lastName());
        }

        return names;
    }
}
