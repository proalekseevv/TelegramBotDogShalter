package ru.skypro.telegrambotdogshelter.services.interfaces;

import com.pengrad.telegrambot.model.Update;

public interface UsersContactInfoService {

    // Сохранение контактных данных пользователя
    void saveUserInfo (Update update);
}
