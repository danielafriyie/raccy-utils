package org.raccy.faker;

import org.raccy.faker.datasets.FemaleNames;
import org.raccy.faker.datasets.MaleNames;
import org.raccy.faker.datasets.Surnames;
import org.raccy.utils.Random;

public class Faker {
    private static final String[] monthList = new String[]{
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    private Faker() {

    }

    public record Month(String name, String index) {
    }

    public record DateOfBirth(String day, Month month, String year) {
    }

    public record Data(String firstname, String lastname, Gender gender, String password, DateOfBirth dateOfBirth) {
    }

    private static String generatePassword() {
        String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaL = alphaU.toLowerCase();
        String digits = "1234567890";
        String symbols = "!@#$%^&*()_+=-";

        String p = Random.choices(alphaU, 3, true) + Random.choices(alphaL, 3, true) + Random.choices(digits, 3, true) + Random.choices(symbols, 3, true);
        return Random.shuffle(p);
    }

    private static Data get(Gender gender) {
        String firstname;
        if (gender == Gender.MALE) {
            firstname = MaleNames.names[Random.randInt(0, MaleNames.length)];
        } else {
            firstname = FemaleNames.names[Random.randInt(0, FemaleNames.length)];
        }

        String surname = Surnames.names[Random.randInt(0, Surnames.length)];
        String day = String.valueOf(Random.randInt(1, 27));
        String year = String.valueOf(Random.randInt(1970, 2002));
        int month = Random.randInt(1, 12);

        return new Data(
                firstname,
                surname,
                gender,
                generatePassword(),
                new DateOfBirth(day, new Month(monthList[month - 1], String.valueOf(month)), year)
        );
    }

    public static Data getMale() {
        return get(Gender.MALE);
    }

    public static Data getFemale() {
        return get(Gender.FEMALE);
    }

    public static Data random() {
        int n = Random.choice(new Integer[]{1, 2});
        if (n == 1) {
            return getMale();
        } else {
            return getFemale();
        }
    }
}
