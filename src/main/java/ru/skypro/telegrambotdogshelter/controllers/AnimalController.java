package ru.skypro.telegrambotdogshelter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;
import ru.skypro.telegrambotdogshelter.services.interfaces.AnimalService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/animal")
@AllArgsConstructor

public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@Valid @RequestBody Animal animal ){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.createAnimal(animal));
    }
    @PostMapping("/{animal_id}")
    public Animal readAnimalById(@PathVariable long animalId)
    {
        return animalService.readAnimalById(animalId);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{shelter_id}")
    public Collection<Animal> getAnimalsInShelter(@RequestParam long shelterId){
        return animalService.getAnimalsByShelterId(shelterId);
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
