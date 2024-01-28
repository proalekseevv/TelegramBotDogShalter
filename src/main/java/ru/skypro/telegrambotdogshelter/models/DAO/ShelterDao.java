package ru.skypro.telegrambotdogshelter.models.DAO;

import ru.skypro.telegrambotdogshelter.models.Shelter;

import java.util.List;

public interface ShelterDao {

    List<Shelter> getAll();


    Shelter create(Shelter shelter);

    Shelter read(Long id);


    Shelter update(Long id, Shelter shelter);

    Shelter delete(Long id);

}
