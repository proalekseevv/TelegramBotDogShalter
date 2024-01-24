package ru.skypro.telegrambotdogshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;

public interface ShelterInfoRepository extends JpaRepository<ShelterInfo, Long> {
}
