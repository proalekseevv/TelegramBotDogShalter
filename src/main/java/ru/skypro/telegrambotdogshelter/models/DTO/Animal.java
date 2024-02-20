package ru.skypro.telegrambotdogshelter.models.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.skypro.telegrambotdogshelter.enums.TypeOfAnimal;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "animal", schema = "bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor


public class Animal {
    @Id
    @Column(name = "animal_id", nullable = false)
    private long animalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "color", nullable = false)
    private String color;

    @Getter
    @Column(name = "animal")
    @Enumerated(EnumType.STRING)
    private TypeOfAnimal typeOfAnimal;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;




    public void setTypeOfAnimal(TypeOfAnimal typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }



    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalId, animal.animalId) && Objects.equals(name, animal.name) && Objects.equals(age, animal.age) && Objects.equals(color, animal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, name, age, color);
    }


    public Long getId() {return animalId;
    }



}
