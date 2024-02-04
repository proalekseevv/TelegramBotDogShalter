package ru.skypro.telegrambotdogshelter.services.interfaces;

import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
public interface UsersContactInfoService {

    UsersContactInformation createUserService(UsersContactInformation usersContactInformation);

    UsersContactInformation readUserServiceById(long id);
}
