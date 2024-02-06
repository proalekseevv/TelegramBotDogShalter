package ru.skypro.telegrambotdogshelter.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;

@Component
public class ShelterInfoMapper {

    public ShelterInfoDto toShelterInfoDto(ShelterInfo shelterInfo) {
        ShelterInfoDto shelterInfoDto = new ShelterInfoDto();
        shelterInfoDto.setId(shelterInfo.getId());
        shelterInfoDto.setInfo(shelterInfo.getInfo());
        shelterInfoDto.setPhoneNumber(shelterInfo.getPhoneNumber());
        shelterInfoDto.setEmail(shelterInfo.getEmail());
        shelterInfoDto.setInfoPets(shelterInfo.getInfoPets());
        shelterInfoDto.setWorkSchedule(shelterInfo.getWorkSchedule());
        return shelterInfoDto;
    }



    public  ShelterInfo toShelterInfo(ShelterInfoDto shelterInfoDto){
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(shelterInfoDto.getId());
        shelterInfo.setInfo(shelterInfoDto.getInfo());
        shelterInfo.setPhoneNumber(shelterInfoDto.getPhoneNumber());
        shelterInfo.setEmail(shelterInfoDto.getEmail());
        shelterInfo.setInfoPets(shelterInfoDto.getInfoPets());
        shelterInfo.setWorkSchedule(shelterInfoDto.getWorkSchedule());
        return shelterInfo;
    }
}
