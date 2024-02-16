package ru.skypro.telegrambotdogshelter.services.interfaces;


import ru.skypro.telegrambotdogshelter.models.DTO.Animal;

import java.util.List;

public interface AnimalService {

    Animal createAnimal(Animal animal);

    Animal readAnimalById(long animal_id);

    Animal updateAnimal(Animal animal);

    Animal deleteAnimal(long animal_id);

    List<Animal> getAll();
}
