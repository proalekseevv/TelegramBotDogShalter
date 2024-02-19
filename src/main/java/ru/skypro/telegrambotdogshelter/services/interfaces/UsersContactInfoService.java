package ru.skypro.telegrambotdogshelter.services.interfaces;

import com.pengrad.telegrambot.model.Update;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
public interface UsersContactInfoService {

    UsersContactInformation createUserService(UsersContactInformation usersContactInformation);

    UsersContactInformation readUserServiceById(long id);

    // Сохранение контактных данных пользователя
    void saveUserInfo(Update update);
}
