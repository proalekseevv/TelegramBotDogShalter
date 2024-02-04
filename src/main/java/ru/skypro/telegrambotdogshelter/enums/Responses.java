package ru.skypro.telegrambotdogshelter.enums;

public enum Responses {
    START("Добро пожаловать в нашу сеть приютов!"),
    INFO_SHAGGY_SOUL("Мы самый лучший приют! SHAGGY_SOUL"),
    INFO_WET_NOSE("Мы самый лучший приют!  WET_NOSE"),

    CALL_VOLUNTEER("Ожидайте, волонтер подключается к чату");

    private final String responseText;

    Responses(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseText() {
        return responseText;
    }
}



