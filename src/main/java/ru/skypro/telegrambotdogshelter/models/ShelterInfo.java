package ru.skypro.telegrambotdogshelter.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "info", schema = "bot")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = "shelter")
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

    @Column(name = "work_schedule")
    @Type(type = "org.hibernate.type.TextType")
    private String workSchedule;

    @Column(name = "contact_for_pass")
    @Type(type = "org.hibernate.type.TextType")
    private String contactForPass;

    @Column(name = "recommendation_tb")
    @Type(type = "org.hibernate.type.TextType")
    private String recommendationTB;

    @OneToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn (name = "shelter_id")
        private Shelter shelter;

//    public String getWorkSchedule() {
//        return workSchedule;
//    }

//    public void setWorkSchedule(String workSchedule) {
//        this.info = info;
//    }


}
