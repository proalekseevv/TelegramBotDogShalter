package ru.skypro.telegrambotdogshelter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.ShelterIsNotExistsException;
import ru.skypro.telegrambotdogshelter.mapper.ShelterMapper;
import ru.skypro.telegrambotdogshelter.models.DAO.ShelterDao;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.repository.ShelterRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository repository;
    private final ShelterMapper shelterMapper;
    private final ShelterDao shelterDao;

    @Override
    public List<ShelterDto> getAll() {
        List<Shelter> shelters = shelterDao.getAll();
        return repository.findAll().stream().map(shelterMapper::toShelterDTO).collect(Collectors.toList());
    }

    @Override
    public ShelterDto create(ShelterDto dto) {
        Shelter shelter = shelterMapper.toShelter(dto);
        return shelterMapper.toShelterDTO(repository.save(shelter));
    }

    @Override
    public ShelterDto read(Long id) {
        return shelterMapper.toShelterDTO(repository.findById(id).orElseThrow(ShelterIsNotExistsException::new));
    }

    @Override
    public ShelterDto update(Long id, ShelterDto dto) {
        Shelter shelter = repository.findById(id).orElseThrow(ShelterIsNotExistsException::new);
        shelter.setId(id);
        shelter.setName(dto.getName());
        return shelterMapper.toShelterDTO(repository.save(shelter));
    }

    @Override
    public ShelterDto delete(Long id) {
        Shelter shelter = repository.findById(id).orElseThrow(ShelterIsNotExistsException::new);
        repository.delete(shelter);
        return shelterMapper.toShelterDTO(shelter);
    }
}
