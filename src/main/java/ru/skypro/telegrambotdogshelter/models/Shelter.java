package ru.skypro.telegrambotdogshelter.models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shelters", schema = "bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Shelter {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shelter shelter = (Shelter) o;
        return id != null && Objects.equals(id, shelter.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
