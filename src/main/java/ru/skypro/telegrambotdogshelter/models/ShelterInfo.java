package ru.skypro.telegrambotdogshelter.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "info", schema = "bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ShelterInfo {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "info", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String info;

    @Column(name = "number", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @Column(name = "info_pets", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String infoPets;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterInfo that = (ShelterInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(info, that.info) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(infoPets, that.infoPets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, phoneNumber, email, infoPets);
    }
}
