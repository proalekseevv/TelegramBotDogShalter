package ru.skypro.telegrambotdogshelter.models.DAO;

import ru.skypro.telegrambotdogshelter.models.Users;
import ru.skypro.telegrambotdogshelter.repository.UsersRepository;

public class UsersDaoImpl implements UsersDao {

    UsersRepository repository;


    @Override
    public Users create(Users users) {
        return repository.save(users);
    }
}
