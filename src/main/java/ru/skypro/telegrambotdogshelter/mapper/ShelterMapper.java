package ru.skypro.telegrambotdogshelter.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.Shelter;

@Component
public class ShelterMapper {
    public ShelterDto toShelterDTO(Shelter shelter) {
        ShelterDto shelterDTO = new ShelterDto();
        shelterDTO.setId(shelter.getId());
        shelterDTO.setName(shelter.getName());
        return shelterDTO;
    }
  public  Shelter toShelter(ShelterDto shelterDto){
       Shelter shelter = new Shelter();
       shelter.setId(shelterDto.getId());
      shelter.setName(shelterDto.getName());
      return shelter;
  }
}
