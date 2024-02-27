package ru.skypro.telegrambotdogshelter.models;

import lombok.*;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;

import javax.persistence.*;

@Entity
@Table(name = "owners", schema = "bot")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = "animal")

public class Owners {
    @Id
    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private int phone;

    @Column(name = "animal_name")
    private String animalName;

    @Column(name = "animal_type")
    private String animalType;

    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;


}
