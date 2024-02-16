package ru.skypro.telegrambotdogshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;
@Repository
public interface AnimalRepository extends JpaRepository <Animal, Long>{

}
