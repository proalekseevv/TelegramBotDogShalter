package ru.skypro.telegrambotdogshelter.models.DAO;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.mapper.ShelterInfoMapper;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;
import ru.skypro.telegrambotdogshelter.repository.ShelterInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterInfoDaoImpl implements ShelterInfoDao {

    private final ShelterInfoRepository repository;
    private final ShelterInfoMapper shelterInfoMapper;


    @Override
    public List<ShelterInfo> getAllInfo() {
        return repository.findAll();
    }


    @Override
    public ShelterInfo read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ShelterInfo create(ShelterInfo shelterInfo) {
        return repository.save(shelterInfo);
    }

    @Override
    public ShelterInfo update(Long id, ShelterInfo shelterInfo) {
        ShelterInfo existingShelter = repository.findById(id).orElse(null);
        if (existingShelter != null) {
            existingShelter.setInfo(shelterInfo.getInfo());
            existingShelter.setInfoPets(shelterInfo.getInfoPets());
            existingShelter.setPhoneNumber(shelterInfo.getPhoneNumber());
            existingShelter.setEmail(shelterInfo.getEmail());
            // Другие обновления полей
            return repository.save(existingShelter);
        }

        return null;
    }


    @Override
    public ShelterInfo delete(Long id) {
        ShelterInfo shelterToDelete = repository.findById(id).orElse(null);
        if (shelterToDelete != null) {
            repository.delete(shelterToDelete);
            return shelterToDelete;
        }
        return null;
    }

}
