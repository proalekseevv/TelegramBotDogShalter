package ru.skypro.telegrambotdogshelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TelegramBotDogShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotDogShelterApplication.class, args);
    }

}