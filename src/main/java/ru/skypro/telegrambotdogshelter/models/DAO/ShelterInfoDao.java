package ru.skypro.telegrambotdogshelter.models.DAO;

import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;

import java.util.List;

public interface ShelterInfoDao  {
    List<ShelterInfo> getAllInfo();

    ShelterInfo read(Long id);

    ShelterInfo create(ShelterInfo shelterInfo);

    ShelterInfo update(Long id, ShelterInfo shelterInfo);

    ShelterInfo delete(Long id);
}
