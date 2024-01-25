package ru.skypro.telegrambotdogshelter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/shelter")
@AllArgsConstructor
public class ShelterController {
    private final ShelterService service;

    @GetMapping
    public List<ShelterDto> getAll() {
        return service.getAll();
    }


    @PostMapping
    public ResponseEntity<ShelterDto> create(@Valid @RequestBody ShelterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @GetMapping("/{id}")
    public ShelterDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @PutMapping("/{id}")
    public ShelterDto update(@PathVariable Long id, @Valid @RequestBody ShelterDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ShelterDto delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
