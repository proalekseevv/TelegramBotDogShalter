package ru.skypro.telegrambotdogshelter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;

import javax.persistence.*;
import java.util.Collection;

@Component
@EqualsAndHashCode(exclude = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users_contact_info", schema = "bot")
@Entity
public class UsersContactInformation {
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


