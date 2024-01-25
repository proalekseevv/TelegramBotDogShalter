package ru.skypro.telegrambotdogshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
