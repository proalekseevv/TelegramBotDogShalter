package ru.skypro.telegrambotdogshelter.models.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.mapper.ShelterMapper;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.repository.ShelterRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ShelterDaoImpl implements ShelterDao {

    private final ShelterRepository repository;
    private final ShelterMapper shelterMapper;


    @Override
    public List<Shelter> getAll() {
        return repository.findAll();
    }


    @Override
    public Shelter create(Shelter shelter) {
        return repository.save(shelter);
    }

    @Override
    public Shelter read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Shelter update(Long id, Shelter shelter) {
        Shelter existingShelter = repository.findById(id).orElse(null);
        if (existingShelter != null) {
            existingShelter.setName(shelter.getName());
            // Другие обновления полей
            return repository.save(existingShelter);
        }
        return null;
    }

    @Override
    public Shelter delete(Long id) {
        Shelter shelterToDelete = repository.findById(id).orElse(null);
        if (shelterToDelete != null) {
            repository.delete(shelterToDelete);
            return shelterToDelete;
        }
        return null;
    }


}
