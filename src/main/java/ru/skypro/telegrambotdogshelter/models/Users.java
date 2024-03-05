package ru.skypro.telegrambotdogshelter.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@EqualsAndHashCode(exclude = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users_contact_info", schema = "bot")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;



    @Column(name = "phone")
    private Long phoneNumber;






}


