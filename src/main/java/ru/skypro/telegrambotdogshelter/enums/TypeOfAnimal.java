package ru.skypro.telegrambotdogshelter.enums;

public enum TypeOfAnimal {
    CAT("Кот"),
    DOG("Собака");
private String russianName;
    TypeOfAnimal(String russianName) {
        this.russianName = russianName;
    }
    public String getRussianName() {
        return russianName;
    }

    @Override
    public String toString() {
        return russianName;
    }
}
