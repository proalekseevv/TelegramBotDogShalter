package ru.skypro.telegrambotdogshelter.services.interfaces;

import com.pengrad.telegrambot.model.Update;
import ru.skypro.telegrambotdogshelter.models.Users;
public interface UsersService {

    Users createUserService(Users users);

    Users readUserServiceById(long id);

    // Сохранение контактных данных пользователя
    void saveUserInfo(Update update);
}
