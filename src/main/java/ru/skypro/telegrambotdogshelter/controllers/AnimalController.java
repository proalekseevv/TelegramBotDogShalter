package ru.skypro.telegrambotdogshelter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;
import ru.skypro.telegrambotdogshelter.services.interfaces.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animal")
@AllArgsConstructor

public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal){
        return animalService.createAnimal(animal);
    }
    @GetMapping("/{animal_id}")
    public Animal readAnimal(@PathVariable long animalId)
    {
        return animalService.readAnimalById(animalId);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAll();
    }

    @PutMapping()
    public Animal updateAnimal
            (@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/{animal_id}")
    public Animal deleteAnimal(@PathVariable long animalId)
    {
        return animalService.deleteAnimal(animalId);
    }
}
