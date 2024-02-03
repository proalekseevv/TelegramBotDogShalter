package ru.skypro.telegrambotdogshelter.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.ShelterIsNotExistsException;
import ru.skypro.telegrambotdogshelter.mapper.ShelterInfoMapper;
import ru.skypro.telegrambotdogshelter.models.DAO.ShelterInfoDao;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.models.ShelterInfo;
import ru.skypro.telegrambotdogshelter.repository.ShelterInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterInfoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelterInfoServiceImpl implements ShelterInfoService {
    private final ShelterInfoDao shelterInfoDao;
    private final ShelterInfoRepository repository;
    private final ShelterInfoMapper shelterInfoMapper;


    private Logger logger = LoggerFactory.getLogger(ShelterInfoServiceImpl.class);




    @Override
    public List<ShelterInfoDto> getAllInfo() {
        List<ShelterInfo> shelterInfo = shelterInfoDao.getAllInfo();
        return  repository.findAll().stream().map(shelterInfoMapper::toShelterInfoDto).collect(Collectors.toList());
    }


    @Override
    public ShelterInfoDto read(Long id) {
        try {
            // Добавьте операторы логирования для отслеживания значений переменных
            logger.info("Trying to read shelter with ID: {}", id);

            return shelterInfoMapper.toShelterInfoDto(repository.findById(id).orElseThrow(ShelterIsNotExistsException::new));
        } catch (ShelterIsNotExistsException e) {
            // Добавьте операторы логирования для отслеживания исключения
            logger.error("Error while reading shelter with ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public ShelterInfoDto create(ShelterInfoDto dto) {
        try {
            ShelterInfo shelterInfo = shelterInfoMapper.toShelterInfo(dto);
            ShelterInfo savedShelterInfo = repository.save(shelterInfo);

            // Добавьте операторы логирования для отслеживания значений переменных
            logger.info("ShelterInfo created with ID: {}", savedShelterInfo.getId());

            return shelterInfoMapper.toShelterInfoDto(savedShelterInfo);
        } catch (Exception e) {
            // Добавьте операторы логирования для отслеживания исключения
            logger.error("Error while creating ShelterInfo", e);
            throw e; // возможно, здесь следует обработать исключение более подробно
        }
    }

    @Override
    public ShelterInfoDto update(Long id, ShelterInfoDto dto) {
        try {
            ShelterInfo shelterInfo = repository.findById(id).orElseThrow(ShelterIsNotExistsException::new);
            shelterInfo.setId(id);
            shelterInfo.setInfo(dto.getInfo());
            shelterInfo.setWorkSchedule(dto.getWorkSchedule());
            shelterInfo.setInfoPets(dto.getInfoPets());
            shelterInfo.setEmail(dto.getEmail());
            shelterInfo.setPhoneNumber(dto.getPhoneNumber());
            ShelterInfo updatedShelterInfo = repository.save(shelterInfo);

            // Добавьте операторы логирования для отслеживания значений переменных
            logger.info("ShelterInfo updated with ID: {}", updatedShelterInfo.getId());

            return shelterInfoMapper.toShelterInfoDto(updatedShelterInfo);
        } catch (Exception e) {
            // Добавьте операторы логирования для отслеживания исключения
            logger.error("Error while updating ShelterInfo with ID: {}", id, e);
            throw e; // возможно, здесь следует обработать исключение более подробно
        }
    }

    @Override
    public ShelterInfoDto delete(Long id) {
        ShelterInfo shelterInfo = repository.findById(id).orElseThrow(ShelterIsNotExistsException::new);
        repository.delete(shelterInfo);
        return shelterInfoMapper.toShelterInfoDto(shelterInfo);
    }



}
