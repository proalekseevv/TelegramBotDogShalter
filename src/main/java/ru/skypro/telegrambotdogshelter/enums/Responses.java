package ru.skypro.telegrambotdogshelter.enums;

public enum Responses {
    START("Добро пожаловать в нашу сеть приютов!");

    private final String responseText;

    Responses(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseText() {
        return responseText;
    }
}



