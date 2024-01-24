package ru.skypro.telegrambotdogshelter.services.interfaces;

import ru.skypro.telegrambotdogshelter.exceptions.ShelterIsNotExistsException;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;

import java.util.List;

public interface ShelterInfoService {
    List<ShelterInfoDto> getAllInfo();

    ShelterInfoDto read(Long id);

    ShelterInfoDto create(ShelterInfoDto dto);
    ShelterInfoDto update(Long id, ShelterInfoDto dto);
    ShelterInfoDto delete(Long id);



}
