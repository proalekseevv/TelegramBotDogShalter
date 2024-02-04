package ru.skypro.telegrambotdogshelter.models.DTO;


public class ShelterDto {
    private  Long id;

    private String name;



    public ShelterDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShelterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
