package ru.skypro.telegrambotdogshelter.services.interfaces;

import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;

import java.util.List;

public interface ShelterInfoService {
    List<ShelterInfoDto> getAllInfo();

    ShelterInfoDto read(Long id);

    ShelterInfoDto create(ShelterInfoDto dto);
    ShelterInfoDto update(Long id, ShelterInfoDto dto);
    ShelterInfoDto delete(Long id);



}
