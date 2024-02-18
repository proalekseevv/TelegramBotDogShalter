package ru.skypro.telegrambotdogshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;

import java.util.Collection;

@Repository
public interface AnimalRepository extends JpaRepository <Animal, Long>{

    Collection<Animal> findAllByShelterId(long shelterId);
}
