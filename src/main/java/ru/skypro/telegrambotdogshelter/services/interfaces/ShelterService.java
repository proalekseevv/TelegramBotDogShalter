package ru.skypro.telegrambotdogshelter.services.interfaces;


import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;

import java.util.List;

public interface ShelterService {
    List<ShelterDto> getAll();

    ShelterDto create(ShelterDto dto);

    ShelterDto read(Long id);

    ShelterDto update(Long id, ShelterDto dto);

    ShelterDto delete(Long id);

//    ShelterDto getById(long id);
}
