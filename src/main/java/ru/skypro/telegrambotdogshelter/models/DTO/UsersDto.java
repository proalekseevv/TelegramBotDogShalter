package ru.skypro.telegrambotdogshelter.models.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private long id;

    private long chatId;

    private String name;

    private String surname;

    private int age;

    private long phoneNumber;

    private String email;


    public UsersDto(long chatId, String name, String surname, int age, long phoneNumber, String email) {
        this.chatId = chatId;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}


