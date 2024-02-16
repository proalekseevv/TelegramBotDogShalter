package ru.skypro.telegrambotdogshelter.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.AnimalNotFoundException;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;
import ru.skypro.telegrambotdogshelter.repository.AnimalRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.AnimalService;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

private final AnimalRepository animalRepository;
    Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);
    public Collection<Animal> animals = new ArrayList<>();
    @Override
    public Animal createAnimal(Animal animal) {
        logger.info("Was invoked method for create animal");
        return animalRepository.save(animal);


    }

    @Override
    public Animal readAnimalById(long animal_id) {
        if (!animalRepository.existsById(animal_id))
            logger.error("Животное c id {} не найдено", animal_id);
        return animalRepository.findById(animal_id)
                .orElseThrow(() ->
                        new AnimalNotFoundException("Животное не найдено"));
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        logger.info("Was invoked method for update animal c id {}", animal.getId());
        readAnimalById(animal.getId());
        return animalRepository.save(animal);
    }

    @Override
    public Animal deleteAnimal(long animal_id) {
        Animal animal = readAnimalById(animal_id);
        logger.info("Was invoked method for remove animal c id {}", +animal_id);
        animalRepository.deleteById(animal_id);
        return animal;
    }

    @Override
    public List<Animal> getAll()
    {
        return new ArrayList<>(animalRepository.findAll());
    }
}
