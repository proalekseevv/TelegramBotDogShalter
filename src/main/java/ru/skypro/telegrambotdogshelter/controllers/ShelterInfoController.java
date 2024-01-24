package ru.skypro.telegrambotdogshelter.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterInfoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/shelterInfo")
@AllArgsConstructor
public class ShelterInfoController {
    private final ShelterInfoService service;

    @GetMapping
    public List<ShelterInfoDto> getAllInfo() {
        return service.getAllInfo();
    }

    @GetMapping("/{id}")
    public ShelterInfoDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    public ResponseEntity<ShelterInfoDto> create(@Valid @RequestBody ShelterInfoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ShelterInfoDto update(@PathVariable Long id, @Valid @RequestBody ShelterInfoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ShelterInfoDto delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
