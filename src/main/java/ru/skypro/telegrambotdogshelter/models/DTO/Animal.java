package ru.skypro.telegrambotdogshelter.models.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import ru.skypro.telegrambotdogshelter.enums.TypeOfAnimal;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;

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
    private Long animalId;

    @Column(name = "name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "age", nullable = false)
@Type(type = "org.hibernate.type.TextType")
    private int age;

    @Column(name = "color", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String color;

    @Getter
    @Column(name = "animal")
    @Type(type = "org.hibernate.type.TextType")
    @Enumerated(EnumType.STRING)
    private TypeOfAnimal typeOfAnimal;

    public void setTypeOfAnimal(TypeOfAnimal typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

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
