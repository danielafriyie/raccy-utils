package org.raccy.faker;

public enum Gender {
    MALE("male", 1),
    FEMALE("female", 2);

    private final String sex;
    private final int value;

    Gender(String sex, int value) {
        this.sex = sex;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return sex;
    }
}
